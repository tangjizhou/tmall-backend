package net.mshome.twisted.tmall;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import net.mshome.twisted.tmall.entity.BaseEntity;

import java.util.Scanner;

/**
 * @author tangjizhou
 * @date 2019-08-15
 * @description 代码生成器
 */
public class MybatisPlusGenerator {

    public static void main(String[] args) {
        AutoGenerator autoGenerator = new AutoGenerator();

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setEntityBuilderModel(true);
        strategyConfig.setControllerMappingHyphenStyle(true);
        strategyConfig.setSuperEntityClass(BaseEntity.class);

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setAuthor("tangjizhouchn@foxmail.com");
        globalConfig.setFileOverride(true);
        globalConfig.setBaseResultMap(false);
        globalConfig.setOutputDir("./src/main/java");
        globalConfig.setOpen(false);

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/tmall?useSSL=false&characterEncoding=utf8");
        dataSourceConfig.setUsername("tmall");
        dataSourceConfig.setPassword("tmallpass");

        TemplateConfig templateConfig = new TemplateConfig();

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("net.mshome.twisted.tmall");

        autoGenerator.setGlobalConfig(globalConfig);
        autoGenerator.setDataSource(dataSourceConfig);
        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.setPackageInfo(packageConfig);
        autoGenerator.setTemplate(templateConfig);
        autoGenerator.setCfg(new InjectionConfig() {
            @Override
            public void initMap() {

            }
        });

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String table = scanner.nextLine();
            strategyConfig.setInclude(table);

            autoGenerator.execute();
        }


    }


}