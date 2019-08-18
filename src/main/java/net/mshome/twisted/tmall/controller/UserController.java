package net.mshome.twisted.tmall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import net.mshome.twisted.tmall.common.Result;
import net.mshome.twisted.tmall.dto.UserAddDTO;
import net.mshome.twisted.tmall.entity.User;
import net.mshome.twisted.tmall.enumeration.UserStateEnum;
import net.mshome.twisted.tmall.exception.TmallException;
import net.mshome.twisted.tmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户，前端控制器
 * </p>
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2019-08-15
 */
@Api(value = "用户接口")
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public Result<String> register(@RequestBody @Validated UserAddDTO userAddDTO) {
        userService.register(userAddDTO);
        return Result.<String>builder().message("注册成功").build();
    }


}

