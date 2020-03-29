package net.mshome.twisted.tmall.aop.configuration;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2020/3/28
 */
@Configuration
public class ActivitiConfiguration implements ProcessEngineConfigurationConfigurer {


    @Override
    public void configure(SpringProcessEngineConfiguration processEngineConfiguration) {
        // processEngineConfiguration.setCustomSessionFactories();

    }


}
