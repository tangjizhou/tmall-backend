package net.mshome.twisted.tmall.controller;


import io.swagger.annotations.ApiOperation;
import net.mshome.twisted.tmall.dto.CheckInDTO;
import net.mshome.twisted.tmall.service.ICheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tangjizhouchn@foxmail.com
 * @since 2020-06-04
 */
@RestController
@RequestMapping("/check-in")
public class CheckInController {

    @Autowired
    private ICheckInService checkInService;

    @ApiOperation("商品入库")
    @PostMapping("/")
    public void checkIn(CheckInDTO checkInDTO) {
        checkInService.checkIn(checkInDTO);
    }

    @ApiOperation("更新商品")
    @PutMapping("/")
    public void edit(CheckInDTO checkInDTO) {
        checkInService.update(checkInDTO);
    }

}

