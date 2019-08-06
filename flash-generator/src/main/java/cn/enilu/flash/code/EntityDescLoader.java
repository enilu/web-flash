package cn.enilu.flash.code;

import org.hibernate.annotations.Table;
import org.nutz.ioc.Ioc;
import org.nutz.json.Json;
import org.nutz.lang.Files;
import org.nutz.lang.Mirror;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 根据java model定义生成，service，controller， <br>
 * </p> Copyright by easecredit.com<br>
 * 作者: zhangtao <br>
 * 创建日期: 16-7-10<br>
 */
public class EntityDescLoader extends AbstractLoader {
    
    private static final Log log = Logs.get();
    public static  final Pattern COLUMN_DEFINITION_PATTERN  =  Pattern.compile("([A-Za-z]+)(?:\\(\\d+\\))?\\s*(?:(?:COMMENT|[Cc]omment)\\s+'(.*?)')?");

    @Override
    public Map<String, TableDescriptor> loadTables(Ioc ioc, String basePackageName,
                                                   String baseUri,
                                                   String servPackageName,
                                                   String repositoryPackageName,
                                                   String modPackageName) throws Exception {
        String packageName = basePackageName+"."+modPackageName;

        String filePath = packageName.replaceAll("\\.","\\/");
        URL url = AbstractLoader.class.getClassLoader().getResource(filePath);
        String path;
        if (url != null) {
            path = url.getPath();
        }
        else {
            path = "out/" + basePackageName.replace('.', '/');
        }
        File f = Files.createDirIfNoExists(path);
        log.debug("output dir = " + f.getAbsolutePath());
        String abstractPath = URLDecoder.decode(path, "utf8");
        File[] files = Files.lsFile(abstractPath, null);
        Map<String, TableDescriptor> tables = new HashMap<String, TableDescriptor>(10);

        for(File file:files){
            String fileName = file.getName().split("\\.")[0];
            String className = packageName+"."+fileName;
            Class<?> modelClass = Class.forName(className);
            if(className.contains(".Model")){
                continue;
            }

            Mirror<?> mirror = Mirror.me(modelClass);
            Table tableAnno =   mirror.getAnnotation(Table.class);
            if(tableAnno==null){
                continue;
            }
            String tableName = tableAnno.appliesTo();
            String entityName = modelClass.getSimpleName();
            TableDescriptor table = new TableDescriptor(tableName,entityName,basePackageName,baseUri,servPackageName,repositoryPackageName,modPackageName);
            if(tableAnno.comment()!=null) {
                table.setLabel(tableAnno.comment());
            }else{
                table.setLabel(tableName);
            }

            tables.put(tableName, table);
            tables.put(entityName,table);
            Field[] fields = mirror.getFields();
            for(Field field:fields){
                ColumnDescriptor column = new ColumnDescriptor();
                String fieldName = field.getName();
                if("createTime".equals(fieldName) || "createBy".equals(fieldName) || "modifyTime".equals(fieldName) || "modifyBy".equals(fieldName)){
                    continue;
                }
                column.setFieldName(fieldName);
                Annotation[] annotations = field.getAnnotations();
                for(Annotation annotation :annotations){
                    if(annotation instanceof  Column){
                        Column columnAnno = (Column)annotation;
                        String columnDefinition= columnAnno.columnDefinition();
                        if(columnDefinition!=null&&!"".equals(columnDefinition.trim())){
                            System.out.println(columnDefinition.trim());
                            Matcher matcher = COLUMN_DEFINITION_PATTERN.matcher(columnDefinition.trim());
                            if(matcher.find()){
                                String type = matcher.group(1);
                                String comment = matcher.group(2);
                                if(comment!=null){
                                    column.setLabel(comment);
                                    column.setComment(comment);
                                }
                                column.setColumnType(type.toLowerCase());
                                column.dataType =type;

                            }
                        }
                        if(columnAnno.name()!=null){
                            column.columnName = columnAnno.name();
                        }else{
                            column.columnName = StrKit.toUnderlineCase(fieldName);
                        }

                    }
                    if(annotation instanceof Id){
                        column.primary=true;
                        table.setPkType(column.getSimpleJavaTypeName());
                        column.columnName =fieldName;
                    }


                }
                if(Strings.isEmpty(column.getLabel())){
                    column.setLabel(fieldName);
                }
                table.addColumn(column);
            }
            System.out.println(Json.toJson(table));

        }
        return tables;
    }
}
