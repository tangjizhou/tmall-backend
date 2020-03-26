package net.mshome.twisted.tmall.process;

import net.mshome.twisted.tmall.process.exception.ProcessConfigureException;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.Expression;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

/**
 * 执行自定义逻辑的统一入口
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2020/3/26
 */
@Service
@Transactional
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProcessExecutionListener implements ExecutionListener, ApplicationContextAware {

    private static final long serialVersionUID = -5515141996381435746L;

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
    public void notify(DelegateExecution delegateExecution) {
        Optional.ofNullable(this.service).orElseThrow(() -> new ProcessConfigureException("参数[service]不可为空"));
        String serviceName = this.service.getExpressionText();
        ProcessExecutionService executionService = applicationContext.getBean(serviceName, ProcessExecutionService.class);
        String param = Objects.nonNull(this.param) ? this.param.getExpressionText() : null;
        executionService.execute(delegateExecution, param);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
