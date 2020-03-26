package net.mshome.twisted.tmall.process;

import net.mshome.twisted.tmall.process.exception.ProcessConfigureException;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

/**
 * 流程任务级别服务
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2020/3/26
 */
@Component
@Transactional
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TaskExecutionListener implements TaskListener, ApplicationContextAware {

    private static final long serialVersionUID = -2124516767305918295L;

    /**
     * 自定义服务
     */
    private Expression service;
    /**
     * 自定义参数
     */
    @Nullable
    private Expression param;

    private ApplicationContext applicationContext;


    @Override
    public void notify(DelegateTask delegateTask) {
        Optional.ofNullable(this.service).orElseThrow(() -> new ProcessConfigureException("参数[service]不可为空"));
        String serviceName = this.service.getExpressionText();
        TaskExecutionService taskService = applicationContext.getBean(serviceName, TaskExecutionService.class);
        String param = Objects.nonNull(this.param) ? this.param.getExpressionText() : null;
        taskService.execute(delegateTask, param);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
