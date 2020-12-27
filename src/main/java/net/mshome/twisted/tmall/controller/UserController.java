package net.mshome.twisted.tmall.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.mshome.twisted.tmall.dto.UserAddDTO;
import net.mshome.twisted.tmall.dto.UserQueryDTO;
import net.mshome.twisted.tmall.service.IUserService;
import net.mshome.twisted.tmall.vo.UserQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/list")
    public Page<UserQueryVO> queryByExample(UserQueryDTO queryDTO) {
        return userService.pageByExample(queryDTO);
    }

}

