package cn.enilu.flash;

//import cn.enilu.flash.code.Generator;
import cn.enilu.flash.utils.Lists;

import java.util.List;

/**
 * 手动生成代码<br>
 * 虽然本系统提供了代码生成插件方便根据java实体生成相关业务代码，但是部分idea版本存在兼容性问题（且也没有提供eclipse的插件），因此可以使用本工具类来手动生成代码
 *
 * @Author enilu
 * @Date 2021/5/17 11:28
 * @Version 1.0
 */
public class CodeGenerator {
    public static void main(String[] args) throws Exception {
        //要生成的代码的java 实体的完整路径
        String className = "cn.enilu.flash.bean.entity.test.Girl";
        generator(className);
   }

    private static void generator(String className) throws Exception {

        String basepath = System.getProperty("user.dir");
        String[] arr = className.split("\\.");
        String entityName = arr[arr.length-1];
        String userPath = arr[arr.length-2];
        String basePackage = className.split(".bean.entity.")[0];
        List<String> param = Lists.newArrayList();
        param.add("-basePath");
        param.add(basepath);
        param.add("-i");
        param.add(entityName);
        param.add("-u");
        param.add("/"+userPath);
        param.add("-p");
        param.add(basePackage);
        param.add("-v");
        param.add("all");
        param.add("-mod");
        param.add(basePackage+"."+userPath);
        param.add("-ctr");
        param.add("api.controller."+userPath);
        param.add("-sev");
        param.add("service."+userPath);
        param.add("-repo");
        param.add("dao."+userPath);
        param.add("controller");
        param.add("service");
        param.add("view");
        param.add("repository");
        String[] args = param.toArray(new String[param.size()]);
//        Generator.main(args);


    }
}
