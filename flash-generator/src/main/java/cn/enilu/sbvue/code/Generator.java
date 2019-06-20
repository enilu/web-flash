package cn.enilu.sbvue.code;

import org.apache.commons.cli.*;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;
import org.nutz.json.Json;
import org.nutz.lang.Files;
import org.nutz.lang.Lang;
import org.nutz.lang.Mirror;
import org.nutz.lang.Streams;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 入口类<br>
 * 作者: enilu <br>
 * 创建日期: 16-7-5<br>
 */
public class Generator {
    private static final Log log = Logs.get();
    //private final Map<String, TableDescriptor> tables;
    private final TableDescriptor table;

    public Generator(Map<String, TableDescriptor> tables, TableDescriptor table) {
        //this.tables = tables;
        this.table = table;
    }

    public void generate(String packageName, String templatePath, File file, boolean force)
            throws IOException {
        if (file.exists() && !force) {
            log.debug("file " + file + " exists, skipped");
            return;
        }

        String code = generateCode(packageName, templatePath);
        file.getParentFile().mkdirs();
        Files.write(file, code.getBytes(Charset.forName("utf8")));

    }

    public String generateCode(String packageName, String templatePath) throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("table", table);
        context.put("packageName", packageName);
        StringWriter writer = new StringWriter();

        String template = new String(Streams.readBytes(ClassLoader.getSystemResourceAsStream(templatePath)),
                                     Charset.forName("utf8"));
        VelocityEngine engine = new VelocityEngine();
        engine.setProperty("runtime.references.strict", false);
        engine.init();
        engine.evaluate(context, writer, "generator", template);
        return writer.toString();

    }

    // -p export -c /generator.xml entity
    public static void main(String[] args) throws Exception {

        String configPath = "code/code.json";

        Pattern includePattern = Pattern.compile(".*");
        Pattern excludePattern = null;
        String module = "test";
        String basePackageName = "cn.enilu.guns";
        String controllerPackageName = "api.controller."+module;
        String servicePackageName = "service."+module;
        String repositoryPackageName = "dao."+module;
        String modelPackageName = "bean.entity."+module;
        String _loader = "entity";

        String outputDir = "src/main/java";
        boolean force = false;
        String baseUri = "/";
        String types[] = {"all"};
        String pages[] = {"index", "add", "edit", "detail"};
        Options options = new Options();
//        options.addOption("c", "config", true, "spring datasource config file(classpath)");
        options.addOption("i", "include", true, "include table pattern");
        options.addOption("x", "exclude", true, "exclude table pattern");
        options.addOption("module", "module", true, "current module name");
        options.addOption("p", "package", true, "base package name,default:cn.enilu.guns");
//        options.addOption("ctr",
//                          "package",
//                          true,
//                          "controller base package name,default:controllers/${package}");
//        options.addOption("mod",
//                          "package",
//                          true,
//                          "model base package name,default:models/${package}");
//        options.addOption("repo",
//                "package",
//                true,
//                "repository base package name,default:repository/${package}");
//        options.addOption("sev",
//                          "package",
//                          true,
//                          "service base package name,default:services/${package}");
//        options.addOption("v",
//                          "views",
//                          true,
//                          "for generator pages,default:all pages,eg: -v index_detail will generate index.html and detail.html");
//        options.addOption("o", "output", true, "output directory, default is " + outputDir);
        options.addOption("u", "base-uri", true, "base uri prefix, default is /");
        options.addOption("f", "force", false, "force generate file even if file exists");
//        options.addOption("loader", "loader", true, "entity or table");
        options.addOption("h", "help", false, "show help message");
        CommandLineParser parser = new GnuParser();
        try {
            CommandLine commandLine = parser.parse(options, args);

            if (commandLine.hasOption("i")) {
                includePattern = Pattern.compile(commandLine.getOptionValue("i"),
                                                 Pattern.CASE_INSENSITIVE);
            }
            if (commandLine.hasOption("x")) {
                excludePattern = Pattern.compile(commandLine.getOptionValue("x"),
                                                 Pattern.CASE_INSENSITIVE);
            }
            if(commandLine.hasOption("module")){
                module =  commandLine.getOptionValue("module");
                controllerPackageName = "api.controller."+module;
                servicePackageName = "service."+module;
                repositoryPackageName = "dao."+module;
                modelPackageName = "bean.entity."+module;
            }
//            if (commandLine.hasOption("c")) {
//                configPath = commandLine.getOptionValue("c");
//            }
            if (commandLine.hasOption("p")) {
                basePackageName = commandLine.getOptionValue("p");
            }
//
//            if (commandLine.hasOption("ctr")) {
//                controllerPackageName = commandLine.getOptionValue("ctr");
//            }
//            if (commandLine.hasOption("sev")) {
//                servicePackageName = commandLine.getOptionValue("sev");
//            }
//            if (commandLine.hasOption("mod")) {
//                modelPackageName = commandLine.getOptionValue("mod");
//            }
//
//            if (commandLine.hasOption("repo")) {
//                repositoryPackageName = commandLine.getOptionValue("repo");
//            }
//
//            if (commandLine.hasOption("o")) {
//                outputDir = commandLine.getOptionValue("o");
//            }
//            if (commandLine.hasOption("u")) {
//                baseUri = commandLine.getOptionValue("u");
//            }
//            if (commandLine.hasOption("v")) {
//                String pagestr = commandLine.getOptionValue("v");
//                pages = pagestr.split("_");
//            }
            force = commandLine.hasOption("f");
            if (commandLine.hasOption("h")) {
                usage(options);
            }
            String[] extraArgs = commandLine.getArgs();
            if (extraArgs.length > 0) {
                types = extraArgs;
            }
            if (commandLine.hasOption("loader"))
                _loader = commandLine.getOptionValue("loader");
        }
        catch (Exception e) {
            usage(options);
        }
        Ioc ioc = new NutIoc(new JsonLoader(configPath));
//        PropertiesProxy conf = ioc.get(PropertiesProxy.class, "conf");
        CodeConfig codeConfig = ioc.get(CodeConfig.class);
//        File f = new File("customer/db.properties");
//        if (f.exists()) {
//            log.debug("load >> " + f.getAbsolutePath());
//            conf.load(new FileReader(f));
//        } else {
//            log.debug("not db.properties found in this dir, using defalut configure");
//        }

        log.debug("=================================================");
        log.debug("=================================================");
        log.debug("=================================================");
//        for (String key : conf.keys()) {
//            log.debugf("%s=%s", key, conf.get(key));
//        }
        log.debug("=================================================");
        log.debug("=================================================");

        Loader loader = (Loader) Mirror.me(Lang.loadClassQuite("cn.enilu.sbvue.code." + Strings.upperFirst(_loader) + "DescLoader")).born();
        Map<String, TableDescriptor> tables = loader.loadTables(ioc,
                                                                basePackageName,
                                                                baseUri,
                                                                servicePackageName,
                                                                repositoryPackageName,
                                                                modelPackageName);

        System.out.println(Json.toJson(tables));
        for (Map.Entry<String, TableDescriptor> entry : tables.entrySet()) {
            String tableName = entry.getKey();
            if (excludePattern != null) {
                if (excludePattern.matcher(tableName).find()) {
                    log.debug("skip " + tableName);
                    continue;
                }
            }
            if (includePattern != null) {
                if (!includePattern.matcher(tableName).find()) {
                    log.debug("skip " + tableName);
                    continue;
                }
            }

            TableDescriptor table = entry.getValue();
            log.debug("generate " + tableName + " ...");
            Generator generator = new Generator(tables, table);
            Map<String, String> typeMap = new HashMap<String, String>();
            typeMap.put("model", modelPackageName);
            typeMap.put("service", servicePackageName);
            typeMap.put("controller", controllerPackageName);
            typeMap.put("repository",repositoryPackageName);

            for (String type : new String[]{"model","repository", "service", "controller", "view"}) {
                if (!isTypeMatch(types, type)) {
                    continue;
                }
                if (type.equals("view")) {
                    generateViews(codeConfig,force, table, generator, pages);
                } else {
                    if (loader instanceof EntityDescLoader && type.equals("model")) {
                        continue;
                    }
                    String packageName = basePackageName + "." + typeMap.get(type);
                    String templatePath = "code/" + type + ".vm";

                    String packagePath = packageName.replace('.', '/');
                    String className = table.getEntityClassName();
                    if (!"model".equals(type)) {
                        className = Utils.UPPER_CAMEL(className) + Strings.upperFirst(type);
                    }
                    File file = new File(codeConfig.getModel(type)+File.separator+outputDir, packagePath + "/" + className + ".java");
                    log.debug("generate " + file.getName());
                    generator.generate(packageName, templatePath, file, force);
                }
            }
        }
        ioc.depose();
        log.debug("done!");
    }

    private static boolean isTypeMatch(String[] types, String type) {
        for (String t : types) {
            if (t.equalsIgnoreCase(type) || "all".equalsIgnoreCase(t)) {
                return true;
            }
        }
        return false;
    }

    private static void generateViews(CodeConfig codeConfig,boolean force,
                                      TableDescriptor table,
                                      Generator generator,
                                      String[] pages)
            throws IOException {
        //生成vue版本相关文件
        File apiFile = new File(codeConfig.getViewModel()+"/src/api/"+table.getViewBasePath()+".js");
        generator.generate(null,  "code/view/api.js.vm", apiFile, force);

        File vueFile = new File(codeConfig.getViewModel()+"/src/views/"+table.getViewBasePath()+File.separator+"index.vue");
        generator.generate(null,  "code/view/index.vue.vm", vueFile, force);

        File jsFile = new File(codeConfig.getViewModel()+"/src/views/"+table.getViewBasePath()+File.separator+table.getName()+".js");
        generator.generate(null,  "code/view/index.js.vm", jsFile, force);

//        for (String view : pages) {
//            String templatePath = "code/view/" + view + ".html.vm";
//            File file = new File(codeConfig.getViewModel()+"src/main/webapp/WEB-INF/views/"
//                                 + table.getViewBasePath()
//                                 + "/"
//                                 + view
//                                 + ".html");
//            log.debug("generate html:" + file.getName());
//            generator.generate(null, templatePath, file, force);
//        }
    }

    private static void usage(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Generator [options] [all|entity|service|controller|view]", options);
        System.exit(1);
    }

}
