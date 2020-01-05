package net.mshome.twisted.tmall.controller;


import net.mshome.twisted.tmall.dto.UserAddDTO;
import net.mshome.twisted.tmall.enumeration.DataState;
import net.mshome.twisted.tmall.service.IUserService;
import net.mshome.twisted.tmall.vo.UserQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                                     @RequestParam DataState dataState) {
        return userService.listAll(username, realName, dataState);
    }

}

