package com.whim.common.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Administrator-CCENOTE
 * @since: 2023/12/14 18:27
 * @description: excel解析过程监听器
 */
@Getter
@Slf4j
public class ExcelListener<T> extends AnalysisEventListener<T> {

    private final List<T> rows = new ArrayList<>();

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        rows.add(t);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("解析完成！读取{}行", rows.size());
    }

}
