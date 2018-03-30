package com.baomidou.springmvc.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.baomidou.springmvc.generator.ContVal.*;

/**
 * <p>
 * 代码生成器演示
 * </p>
 */
public class MpGenerator {

    private GlobalConfig gc; //全局配置
    private AutoGenerator mpg; //mp的generator

    public MpGenerator() {
        gc = new GlobalConfig();
        mpg = new AutoGenerator();
    }

    /**
     * 执行代码 生成 文件
     */
    @Test
    public void testGenerate() {
        setGenerator(); //配置生成
        configDataSource();   //配置数据源
        configStrategy();      // 策略配置
        configPackage();      // 包配置
        configInjection();  // 配置....
        configTemplate(); //配置生成的文件哪些不生成
        mpg.execute();  // 执行生成
    }

    private void setGenerator() {
        // 选择 freemarker 引擎，默认 Veloctiy
        // mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        // gc.setKotlin(true); //是否生成 kotlin 代码 (默认为false)


        // 全局配置
        gc.setFileOverride(true); //设置文件覆盖 (默认为false)
        gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false (默认为true)
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList

        //设置文件的输出目录  (默认"D://")
        gc.setOutputDir(FILE_OUT_DIR);
        gc.setAuthor("Forever丶诺"); //设置作者

        /**
         * 设置生成文件的后缀
         */
        String suffix = "%s";
        // gc.setControllerName(suffix+"Action");        //设置controller 的后缀  默认后缀是XXXController
        gc.setMapperName(suffix + "Dao"); // 自定义文件命名，注意 %s 会自动填充表实体属性！ 设置Mapper的类名  默认XXXMapp
        // gc.setServiceImplName("%sServiceDiy"); //设置service实现类的后缀  //默认 XXXServiceImpl
        gc.setServiceName(suffix + "Service");//默认 I+XXX+Service
        //String xmlName =suffix+ "-sqlmap" ;
        String xmlName = null ;
        gc.setXmlName(xmlName); //默认Mapper



        mpg.setGlobalConfig(gc);
    }

    private void configInjection() {
        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };

        // 自定义 xxList.jsp 生成
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();

  /*      focList.add(new FileOutConfig("/template/list.jsp.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return "D://my_" + tableInfo.getEntityName() + ".jsp";
            }
        });*/

        // 调整 xml 生成目录演示
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return "/develop/code/xml/" + tableInfo.getEntityName() + ".xml";
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
    }

    /**
     * 配置生成文件的策略
     */
    private void configStrategy() {
        StrategyConfig strategy = new StrategyConfig();

        strategy.setEntityLombokModel(true);//
        strategy.setEntityBuilderModel(true);//【实体】是否为构建者模型（默认 false）
        strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略

        //修改去除的表前缀(比如数据库中的表前缀 是 Tb_User 只需要生成User 这儿设置表前缀就行 )
        String[] tablePrefix = {"sys_"};
        strategy.setTablePrefix(tablePrefix);

        //需要生成的表(全表名)
        String[] includeTabName = {"person"};
        strategy.setInclude(includeTabName); // 需要生成的表

        // 排除生成的表
        // strategy.setExclude(new String[]{"test"});

        /**
         * 设置生成类 继承的父类
         */
        //strategy.setSuperEntityClass("com.baomidou.demo.test.TestEntity");
        //strategy.setSuperEntityColumns(new String[] { "school", "age" });
        // strategy.setSuperControllerClass("com.baomidou.demo.TestController");
        // strategy.setSuperServiceClass("com.baomidou.demo.TestService");
        // strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
        // strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");

        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);

        mpg.setStrategy(strategy);
    }

    private void configDataSource() {
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();

        // dsc.setDbType(DbType.MYSQL);
/*        dsc.setTypeConvert(new MySqlTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                System.out.println("转换类型：" + fieldType);
                // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
                return super.processTypeConvert(fieldType);
            }
        });*/

        //设置链接数据库的四大属性
        dsc.setDriverName(JDBC_DRIVER_NAME);
        dsc.setUsername(JDBC_USER_NAME);
        dsc.setPassword(JDBC_PASSWORD);
        dsc.setUrl(JDBC_URL);
        mpg.setDataSource(dsc);
    }

    /**
     * 配置包名
     * 修改对应的变量就行
     * 查看默认值  查看 PackageConfig 对象
     *
     * @param :
     * @Author: Forever丶诺
     * @Date: 17:04 2018/2/9
     */
    private void configPackage() {

        //父级包名
        String parentVal = "com.baomidou.springmvc";
        //模块名(备注如果设置了模块名,controller类上的@RequestMapping("/模块名/类名"))
        String moduleNameVal = null;
        //实体bean的包名
        String entityNameVal = "model";
        //控制层的包名
        String controllerNameVal = "controller";
        //dao层的包名
        String mapperNameVal = "dao";
        //xml文件生成的包名
        String mapperXmlVal = "mapperXml";
        //service接口的包名
        String serviceVal = "service";
        //service实现类的包名
        String serviceImpVal = "service.impl";

        mpg.setPackageInfo(new PackageConfig()
                .setParent(parentVal)
                .setModuleName(moduleNameVal)
                .setEntity(entityNameVal)
                .setMapper(mapperNameVal)
                .setXml(mapperXmlVal)
                .setController(controllerNameVal)
                .setService(serviceVal).setServiceImpl(serviceImpVal)
        );
    }

    /**
     * 配置模板的生成文件
     *
     * @param :
     * @Author: Forever丶诺
     * @Date: 17:20 2018/2/9
     */
    private void configTemplate() {
        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        // TemplateConfig tc = new TemplateConfig();
        // tc.setController("...");
        //tc.setEntity("");
        // tc.setMapper("...");
        // tc.setXml("...");
        // tc.setService("...");
        // tc.setServiceImpl("...");
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        // mpg.setTemplate(tc);
    }

}