package net.mshome.twisted.tmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.mshome.twisted.tmall.entity.SqlLog;
import net.mshome.twisted.tmall.mapper.SqlLogMapper;
import net.mshome.twisted.tmall.service.ISqlLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tangjizhou
 * @since 2019-08-26
 */
@Service
public class SqlLogServiceImpl extends ServiceImpl<SqlLogMapper, SqlLog> implements ISqlLogService {

}
