package com.whim.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.whim.common.web.Result;
import com.whim.model.dto.admin.permission.AddPermissionDTO;
import com.whim.model.dto.admin.permission.DeletePermissionDTO;
import com.whim.model.dto.admin.permission.UpdatePermissionDTO;
import com.whim.service.ISysPermissionService;
import com.whim.common.core.base.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限菜单表(SysPermission)表控制层
 *
 * @author jince
 * @since 2024-01-03 10:58:22
 */
@RestController
@RequestMapping("/admin/sysPermission")
@RequiredArgsConstructor
@Tag(name = "权限管理")
public class SysPermissionController extends BaseController {
    private final ISysPermissionService sysPermissionService;

    /**
     * 添加权限
     */
    @Operation(summary = "权限添加")
    @PostMapping("/add")
    @SaCheckPermission("sys:permission:add")
    public Result<String> addPermission(@Validated @RequestBody AddPermissionDTO addPermissionDTO) {
        return sysPermissionService.addPermission(addPermissionDTO) ? Result.success("添加成功") : Result.error("添加失败");
    }

    /**
     * 修改权限
     */
    @Operation(summary = "权限修改")
    @PostMapping("/update")
    @SaCheckPermission("sys:permission:update")
    public Result<String> updatePermission(@Validated @RequestBody UpdatePermissionDTO updatePermissionDTO) {
        return sysPermissionService.updatePermission(updatePermissionDTO) ? Result.success("修改成功") : Result.error("修改失败");
    }

    /**
     * 删除权限
     */
    @Operation(summary = "权限删除")
    @PostMapping("/delete")
    @SaCheckPermission("sys:permission:delete")
    public Result<String> deletePermission(@Validated @RequestBody DeletePermissionDTO deletePermissionDTO) {
        return sysPermissionService.deletePermission(deletePermissionDTO) ? Result.success("删除成功") : Result.error("删除失败");
    }
}

