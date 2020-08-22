package net.mshome.twisted.tmall.aop.configuration;

import net.mshome.twisted.tmall.service.process.ProcessExecutionListener;
import net.mshome.twisted.tmall.service.process.TaskExecutionListener;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * Activiti流程配置
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2020/3/28
 */
@Configuration
public class ActivitiConfiguration implements ProcessEngineConfigurationConfigurer, ApplicationContextAware {


    @Override
    public void configure(SpringProcessEngineConfiguration processEngineConfiguration) {
        // processEngineConfiguration.setCustomSessionFactories();

    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ProcessExecutionListener.setApplicationContext(applicationContext);
        TaskExecutionListener.setApplicationContext(applicationContext);
    }

}
