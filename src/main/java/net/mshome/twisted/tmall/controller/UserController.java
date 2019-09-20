package net.mshome.twisted.tmall.controller;


import net.mshome.twisted.tmall.dto.UserAddDTO;
import net.mshome.twisted.tmall.dto.UserLoginDTO;
import net.mshome.twisted.tmall.entity.User;
import net.mshome.twisted.tmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @PostMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response,
                      @RequestBody UserLoginDTO userLoginDTO) throws Exception {

        String redirect = request.getParameter("redirect");
        if (redirect != null) {
            response.sendRedirect(redirect);
        }
    }


    @PostMapping("/register")
    public void register(@RequestBody @Validated UserAddDTO userAddDTO) {
        userService.register(userAddDTO);
    }

    @GetMapping("/listAll")
    public List<User> listAll() {
        return userService.list();
    }

}

