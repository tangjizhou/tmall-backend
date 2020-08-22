package net.mshome.twisted.tmall.aop.handler;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 以逗号隔开的转换为List
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019-08-24
 */
public class StringToListHandler implements TypeHandler<List<String>> {

    @Override
    public void setParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType)
            throws SQLException {
        parameter = Optional.ofNullable(parameter).orElse(List.of());
        ps.setString(i, String.join(",", parameter));
    }

    @Override
    public List<String> getResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        value = Optional.ofNullable(value).orElse(StringPool.EMPTY);
        return Arrays.asList(value.split(StringPool.COMMA));
    }

    @Override
    public List<String> getResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        value = Optional.ofNullable(value).orElse(StringPool.EMPTY);
        return Arrays.asList(value.split(StringPool.COMMA));
    }

    @Override
    public List<String> getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        value = Optional.ofNullable(value).orElse(StringPool.EMPTY);
        return Arrays.asList(value.split(StringPool.COMMA));
    }

}
