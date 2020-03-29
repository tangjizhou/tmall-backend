package net.mshome.twisted.tmall.process.checkin.execution;

import net.mshome.twisted.tmall.entity.Product;
import net.mshome.twisted.tmall.enumeration.DataState;
import net.mshome.twisted.tmall.process.ProcessExecutionService;
import net.mshome.twisted.tmall.process.checkin.VarDefinition;
import net.mshome.twisted.tmall.process.exception.ProcessExecuteException;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 流程启动检查服务
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2020/3/27
 */
@Service
@Transactional
@Qualifier("CheckIn_ProcessStartCheckService")
public class ProcessStartCheckService implements ProcessExecutionService {

    @Override
    public void execute(DelegateExecution delegateExecution, final String param) {
        Product product = (Product) delegateExecution.getVariable(VarDefinition.CHECK_IN_PRODUCT);
        if (product.getDataState() == DataState.INVALID) {
            throw new ProcessExecuteException("当前产品已经失效");
        }
    }

}
