package net.mshome.twisted.tmall.service.process.checkin.task;

import net.mshome.twisted.message.client.MessageClient;
import net.mshome.twisted.message.model.SimpleEmailContext;
import net.mshome.twisted.tmall.entity.Product;
import net.mshome.twisted.tmall.service.process.TaskExecutionService;
import net.mshome.twisted.tmall.service.process.checkin.VarDefinition;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 任务生成，通知审批人服务
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2020/3/27
 */
@Service
@Transactional
public class TaskCreateNoticeService implements TaskExecutionService {

    @Autowired
    private MessageClient messageClient;

    @Override
    public void execute(DelegateTask delegateTask, final String param) {
        DelegateExecution execution = delegateTask.getExecution();
        Product product = (Product) execution.getVariable(VarDefinition.CHECK_IN_PRODUCT);

        CompletableFuture.runAsync(() -> {
            SimpleEmailContext emailContext = SimpleEmailContext.builder().to(List.of("tangjizhouchn@foxmail.com"))
                    .subject("您有新的待办事项").content(product.toString()).build();
            messageClient.send(emailContext);
        });
    }

}
