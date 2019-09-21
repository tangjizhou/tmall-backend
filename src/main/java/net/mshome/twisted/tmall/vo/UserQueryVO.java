package net.mshome.twisted.tmall.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.mshome.twisted.tmall.enumeration.UserState;

import java.time.LocalDateTime;

/**
 * TODO
 *
 * @author tangjizhouchn@foxmail.com
 * @date 2019/9/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQueryVO {

    private String username;
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private UserState state;
    private String realName;
    private String address;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updateTime;


}
