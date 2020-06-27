package com.example;

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

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://192.168.56.103:33306/bank1";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    private static final String MODULE = "zdemo/consu8001";                   //当前project中module的名称
    private static final String PARENT_PACKAGE = "com.example";  //所有controller，service，entity在这个包下面

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

        mpg.setStrategy(strategy);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);                                                    //不生成xml
        mpg.setTemplate(templateConfig);


        // 全局配置
        String projectPath = System.getProperty("user.dir");
        GlobalConfig gc = new GlobalConfig()
                .setOutputDir(projectPath + "/" + MODULE + "/src/main/java")
                .setAuthor("zxw")
                .setOpen(false)
//                .setSwagger2(true)                                      //swagger
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
                .setEntity("entity")
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


        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }
}
