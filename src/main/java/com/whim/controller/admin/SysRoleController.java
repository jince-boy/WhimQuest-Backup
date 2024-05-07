package com.whim.controller.admin;

import com.whim.service.ISysRoleService;
import com.whim.common.core.base.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色表(SysRole)表控制层
 *
 * @author jince
 * @since 2024-01-03 10:34:59
 */
@RestController
@RequestMapping("/sysRole")
@RequiredArgsConstructor
public class SysRoleController extends BaseController {
    private final ISysRoleService sysRoleService;

}

