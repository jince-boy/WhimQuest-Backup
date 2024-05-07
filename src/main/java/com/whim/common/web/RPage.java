package com.whim.common.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Jince
 * @since: 2023.11.30 下午 06:03
 * @description: Response分页
 */
@Data
@AllArgsConstructor
@Schema(description = "分页对象")
public class RPage<T> {
    @Schema(description = "当前页")
    private Long currentPage;
    @Schema(description = "分页数据")
    private List<T> data;
    @Schema(description = "最后一页")
    private Long lastPage;
    @Schema(description = "每页条数")
    private Long perPage;
    @Schema(description = "总数")
    private Long total;
}
