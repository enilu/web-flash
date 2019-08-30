package cn.enilu.flash.code;


import org.atteo.evo.inflector.English;
import org.nutz.lang.Strings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 实体（表）基本信息描述类<br>
 * 作者: zhangtao <br>
 * 创建日期: 16-7-10<br>
 */
public class TableDescriptor {
    private final String basePackageName;
    private final String baseUri;
    private final List<ColumnDescriptor> columns = new ArrayList<ColumnDescriptor>();
    //表名称
    public final String name;
    //实体类名称
    private String entityName;
    //实体类名称（首字母小写）
    private String entityNameLowerFirstChar;
    //主键类型
    private String pkType;
    //注释
    private String comment;
    private String label = "项";
    //service包名
    private String serPackageName;
    //models包名
    private String modPackageName;
    //repository包名
    private String repoPackageName;
    //最后一层包的报名
    private String lastPackageName;


    public TableDescriptor(String name, String entityName, String basePackageName, String baseUri, String serPackageName,
                           String repoPackageName,
                           String modPackageName) {
        this.name = name;
        this.entityName = entityName;
        this.basePackageName = basePackageName;
        this.serPackageName = serPackageName;
        this.repoPackageName = repoPackageName;
        this.modPackageName = modPackageName;
        this.lastPackageName =      modPackageName.split("\\.")[modPackageName.split("\\.").length-1];
        if (!baseUri.endsWith("/")) {
            baseUri = baseUri + "/";
        }
        this.baseUri = baseUri;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public String getName() {
        return name;
    }

    public String getPlural() {
        return English.plural(name);
    }

    public String getPkType() {
        return pkType;
    }

    public void setPkType(String pkType) {
        this.pkType = pkType;
    }

    public String getBasePackageName() {
        return basePackageName;
    }

    public List<ColumnDescriptor> getColumns() {
        return columns;
    }

    public void addColumn(ColumnDescriptor column) {
        columns.add(column);
    }

    public String getClassName() {
        return getEntityClassName();
    }

    public String getUriPrefix() {
        if (getName().contains("_")) {
            return baseUri + getName().substring(getName().indexOf("_") + 1).replace("_", "/");
        }
        return baseUri + getName();
    }

    public String getViewBasePath() {
        if (getName().contains("_")) {
            return baseUri.replaceFirst("/", "") + getName().substring(getName().indexOf("_") + 1).replace("_", "/");
        }
        return baseUri.replaceFirst("/", "") + getName().replace("_", "/");
    }

    public String getModPackageName() {
        return modPackageName;
    }

    public void setModPackageName(String modPackageName) {
        this.modPackageName = modPackageName;
    }

    public String getSerPackageName() {
        return serPackageName;
    }

    public void setSerPackageName(String serPackageName) {
        this.serPackageName = serPackageName;
    }

    public String getRepoPackageName() {
        return repoPackageName;
    }

    public void setRepoPackageName(String repoPackageName) {
        this.repoPackageName = repoPackageName;
    }

    public String getEntityClassName() {
        if (Strings.isBlank(entityName)) {
            return Utils.upperCamel(name);
        }
        return entityName;
    }
    public String getEntityNameLowerFirstChar(){
        String ret = getEntityClassName();
        return ret.substring(0,1).toLowerCase()+ret.substring(1);
    }
    public String getEntityFullClassName() {
        return basePackageName + "." + getModPackageName() + "." + getEntityClassName();
    }

    public String getServiceFullClassName() {
        return basePackageName + "." + getSerPackageName() + "." + getServiceClassName();
    }
    public String getRepositoryFullClassName(){
        return basePackageName + "." + getRepoPackageName() + "." + getRepositoryClassName();
    }

    public String getLastPackageName() {
        return lastPackageName;
    }

    public void setLastPackageName(String lastPackageName) {
        this.lastPackageName = lastPackageName;
    }

    public String getEntityInstanceName() {
        return Utils.lowerCamel(name);
    }

    public String getEntityInstancesName() {
        return getEntityInstanceName() + "s";
    }

    public String getServiceInstanceName() {
        return Strings.lowerFirst(getServiceClassName());
    }

    public String getServiceClassName() {
        return Utils.upperCamel(getEntityClassName()) + "Service";
    }
    public String getRepositoryClassName(){
        return Utils.upperCamel(getEntityClassName()) + "Repository";
    }

    public String getControllerClassName() {
        return Utils.upperCamel(getEntityClassName()) + "Controller";
    }

    public void addPrimaryKeyColumn(String columnName) {
        for (ColumnDescriptor column : columns) {
            if (column.columnName.equals(columnName)) {
                column.primary = true;
                break;
            }
        }
    }

    public ColumnDescriptor getPrimaryColumn() {
        for (ColumnDescriptor column : columns) {
            if (column.primary) {
                return column;
            }
        }
        return null;
    }

    public String getPrimaryType() {
        ColumnDescriptor columnDescriptor = getPrimaryColumn();
        if (columnDescriptor == null) {
            return null;
        }

        return columnDescriptor.getSimpleJavaTypeName();
    }

    public String getTableAnnotation() {
        return "@Table";
    }

    public List<String> getImports() {
        Set<String> klasses = new LinkedHashSet<String>();

        for (ColumnDescriptor column : columns) {
            String klass = column.getJavaType();
            if (klass.startsWith("java.lang") || klass.indexOf('.') == -1) {
                continue;
            }
            klasses.add(column.getJavaType());
        }

        List<String> imports = new ArrayList<String>();
        imports.addAll(klasses);
        imports.add(null);

        imports.add(Serializable.class.getName());
        imports.add(null);

        klasses.clear();
        if (klasses.size() > 0) {
            imports.addAll(klasses);
            imports.add(null);
        }

        return imports;
    }

    public List<ColumnDescriptor> getEnumColumns() {
        List<ColumnDescriptor> result = new ArrayList<ColumnDescriptor>();
        for (ColumnDescriptor column : columns) {
            if (column.isEnum()) {
                result.add(column);
            }
        }
        return result;
    }

    //todo
    public String getQueryColumns(String op) {
        List<String> result = new ArrayList<String>();
        for (ColumnDescriptor column : columns) {
            if (op.equals(column.getQueryOperator())) {
                result.add("\"" + column.columnName + "\"");
            }
        }

        if (result.isEmpty()) {
            return null;
        }
        return "";
    }

    public List<ColumnDescriptor> getSearchableColumns() {
        List<ColumnDescriptor> result = new ArrayList<ColumnDescriptor>();

        for (ColumnDescriptor column : columns) {
            if (!Strings.isBlank(column.getQueryOperator())) {
                result.add(column);
            }
        }

        return result;
    }

    public List<ColumnDescriptor> getLabeledColumns() {
        List<ColumnDescriptor> result = new ArrayList<ColumnDescriptor>();

        for (ColumnDescriptor column : columns) {
            if (column.getComment() != null && !column.primary) {
                result.add(column);
            }
        }
        return result;
    }

    public String[] getLabeledColumnNames() {
        List<ColumnDescriptor> columnDescriptors = getLabeledColumns();
        List<String> result = new ArrayList<String>();
        for (ColumnDescriptor descriptor : columnDescriptors) {
            result.add(descriptor.getFieldName());
        }
        return result.toArray(new String[0]);

    }

    public String getLabeledColumnNamesString() {
        StringBuilder buf = new StringBuilder();
        for (String name : getLabeledColumnNames()) {
            buf.append("\"").append(name).append("\"").append(", ");
        }
        if (buf.length() > 2) {
            buf.setLength(buf.length() - 2);
        }
        return buf.toString();
    }

    public List<ColumnDescriptor> getIndexColumns() {
        List<ColumnDescriptor> result = new ArrayList<ColumnDescriptor>();

        for (ColumnDescriptor column : columns) {
            if (!Strings.isBlank(column.getLabel())
                    && !"id".equalsIgnoreCase(column.columnName)) {
                result.add(column);
            }
        }

        return result;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}