package net.mshome.twisted.tmall.controller;


import net.mshome.twisted.tmall.common.Result;
import net.mshome.twisted.tmall.dto.UserAddDTO;
import net.mshome.twisted.tmall.dto.UserLoginDTO;
import net.mshome.twisted.tmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2019-08-26
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public Result<String> login(@RequestBody @Validated UserLoginDTO userLoginDTO) {
        return Result.<String>builder().build();

    }


    @PostMapping("/register")
    public Result<String> register(@RequestBody @Validated UserAddDTO userAddDTO) {
        userService.register(userAddDTO);
        return Result.<String>builder().message("注册成功").build();
    }


}

