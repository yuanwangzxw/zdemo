package com.example.demo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Scanner;

public class MpGenerator {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://125.64.5.82:23306/bizeispacttest?useUnicode=true&useSSL=false&characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASSWORD = "TMc28ssM081%195*";
    private static final String MODULE = "zdemo";                   //当前project中module的名称
    private static final String PARENT_PACKAGE = "com.example.demo";  //所有controller，service，entity在这个包下面

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("表名错误:" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 策略配置
        //这需要实现填充器metaObjectHandler,然后直接默认填充LocalDateTime.now()或uuid
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(new TableFill("gmt_create", FieldFill.INSERT));
        tableFills.add(new TableFill("gmt_update", FieldFill.INSERT_UPDATE));
        StrategyConfig strategy = new StrategyConfig()
                .setTableFillList(tableFills)
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setRestControllerStyle(true)
                .setInclude(scanner("表名，多个英文逗号分割").split(","))
                .setControllerMappingHyphenStyle(true)
                .setVersionFieldName("version")
                .setLogicDeleteFieldName("is_del")
                .setEntityLombokModel(true)
                .setEntityBuilderModel(true)                   //access(chain=true)生成的set返回对象而不是void，用于链式
                .setTablePrefix("USER_");                       //删除表前缀
//        strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
//        strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
//        strategy.setSuperEntityColumns("guid");

        mpg.setStrategy(strategy);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);                                                    //不生成xml
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();
        mpg.setTemplate(templateConfig);


        // 全局配置
        String projectPath = System.getProperty("user.dir");
        GlobalConfig gc = new GlobalConfig()
                .setOutputDir(projectPath + "/" + MODULE + "/src/main/java")
                .setAuthor("zxw")
                .setOpen(false)
                .setSwagger2(true)                                      //swagger
                .setEntityName("%sDO")
                .setMapperName("%sMapper")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sController")
                .setFileOverride(true);
        mpg.setGlobalConfig(gc);

        // 包配置
        PackageConfig pc = new PackageConfig()
                .setParent(PARENT_PACKAGE)
                .setEntity("entity.dos")
                .setMapper("mapper")
                .setService("service")
                .setServiceImpl("service.impl")
                .setController("controller");
//        pc.setModuleName(MODULE);
        mpg.setPackageInfo(pc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig()
                .setUrl(URL)
                .setSchemaName("public")
                .setDriverName(DRIVER)
                .setUsername(USER)
                .setPassword(PASSWORD);
        mpg.setDataSource(dsc);

        // 自定义配置
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                // to do nothing
//            }
//        };

        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
//         String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });

//        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);

        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }
}
