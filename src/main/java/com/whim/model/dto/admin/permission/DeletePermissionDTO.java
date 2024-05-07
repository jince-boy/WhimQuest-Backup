package com.whim.model.dto.admin.permission;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * @author: Administrator-CCENOTE
 * @since: 2024-01-12 16:38
 * @description: 删除权限对象
 */
@Data
@Schema(name = "删除权限对象", description = "删除权限对象")
public class DeletePermissionDTO {
    @NotNull(message = "权限名称不能为空")
    @Schema(description = "id数组")
    private List<Long> ids;
}
