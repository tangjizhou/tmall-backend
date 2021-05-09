package net.mshome.twisted.tmall;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import net.mshome.twisted.tmall.entity.BaseEntity;

import java.util.Scanner;

/**
 * 代码生成器
 *
 * @author tangjizhou
 * @since 2019-08-15
 */
public class MybatisPlusGenerator {

    public static void main(String[] args) {
        AutoGenerator autoGenerator = new AutoGenerator();

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setChainModel(true);
        strategyConfig.setControllerMappingHyphenStyle(true);
        strategyConfig.setSuperEntityClass(BaseEntity.class);
        autoGenerator.setStrategy(strategyConfig);

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setAuthor("tangjizhou");
        globalConfig.setFileOverride(true);
        globalConfig.setBaseResultMap(false);
        globalConfig.setOutputDir("./src/main/java");
        globalConfig.setOpen(false);
        autoGenerator.setGlobalConfig(globalConfig);

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUrl("jdbc:mysql://192.168.2.102:3306/tmall?useSSL=false&characterEncoding=utf8");
        dataSourceConfig.setUsername("tmall");
        dataSourceConfig.setPassword("tmallpass");
        autoGenerator.setDataSource(dataSourceConfig);

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("net.mshome.twisted.tmall");
        autoGenerator.setPackageInfo(packageConfig);

        autoGenerator.setCfg(new InjectionConfig() {
            @Override
            public void initMap() {

            }
        });

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入表名，多个名称以逗号隔开");
        String tables = scanner.nextLine();
        strategyConfig.setInclude(tables.split(","));
        autoGenerator.execute();
    }


}
