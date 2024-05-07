package com.whim.controller.admin;
import com.whim.common.web.Result;
import com.whim.service.ISysUserService;
import com.whim.common.core.base.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统用户表(SysUser)表控制层
 *
 * @author Jince
 * @since 2023-12-16 23:52:04
 */
@RestController
@RequestMapping("/admin/sysUser")
@RequiredArgsConstructor
public class SysUserController extends BaseController {
    private final ISysUserService sysUserService;


}

