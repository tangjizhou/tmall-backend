package net.mshome.twisted.tmall.controller;


import net.mshome.twisted.tmall.dto.UserAddDTO;
import net.mshome.twisted.tmall.dto.UserLoginDTO;
import net.mshome.twisted.tmall.enumeration.UserState;
import net.mshome.twisted.tmall.service.IUserService;
import net.mshome.twisted.tmall.vo.UserStateVO;
import net.mshome.twisted.tmall.vo.UserQueryVO;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.authc.credential.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
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




    @PostMapping("/register")
    public void register(@RequestBody @Validated UserAddDTO userAddDTO) {
        userService.register(userAddDTO);
    }

    @GetMapping("/listAll")
    public List<UserQueryVO> listAll(@RequestParam String username, @RequestParam String realName,
                                     @RequestParam UserState userState) {
        return userService.listAll(username, realName, userState);
    }

    @GetMapping("/listUserSates")
    public List<UserStateVO> listUserSate() {
        return Arrays.stream(UserState.values()).map(userState ->
                UserStateVO.builder().label(userState.name()).description(userState.getDescription())
                        .value(userState.getValue()).build())
                .collect(Collectors.toList());
    }

}

