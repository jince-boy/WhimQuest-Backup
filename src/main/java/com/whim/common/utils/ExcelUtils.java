package com.whim.common.utils;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.converters.longconverter.LongStringConverter;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.SimpleColumnWidthStyleStrategy;
import com.whim.common.exception.ExcelException;
import com.whim.common.listener.ExcelListener;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author: Administrator-CCENOTE
 * @since: 2023/12/14 15:43
 * @description: Excel工具类，目前只是封装了简单的工具类，导入和导出，如果需要更高级一点的内容可以自定义再此工具类中
 */
@Slf4j
public class ExcelUtils {
    public static <T> List<T> read(String filePath, final Class<?> clazz) {
        File f = new File(filePath);
        try (FileInputStream fis = new FileInputStream(f)) {
            return read(fis, clazz);
        } catch (FileNotFoundException e) {
            log.error("文件{}不存在", filePath, e);
        } catch (IOException e) {
            log.error("文件读取出错", e);
        }
        return null;
    }

    public static <T> List<T> read(InputStream inputStream, final Class<?> clazz) {
        if (inputStream == null) {
            throw new ExcelException("解析出错了，文件流是null");
        }
        // 有个很重要的点 DataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        ExcelListener<T> listener = new ExcelListener<>();
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(inputStream, clazz, listener).sheet().doRead();
        return listener.getRows();
    }

    public static void write(String outFile, List<?> list) {
        Class<?> clazz = list.get(0).getClass();
        // 创建样式策略对象
        HorizontalCellStyleStrategy styleStrategy = new HorizontalCellStyleStrategy(getHeaderCellStyles(), getContentCellStyles());
        // sheetName为sheet的名字，默认写第一个sheet
        EasyExcel.write(outFile, clazz)
                .registerWriteHandler(styleStrategy)
                .registerConverter(new LongStringConverter())
                .registerWriteHandler(new SimpleColumnWidthStyleStrategy(30)).sheet()
                .doWrite(list);
    }

    public static void write(String outFile, List<?> list, String sheetName) {
        Class<?> clazz = list.get(0).getClass();
        // 创建样式策略对象
        HorizontalCellStyleStrategy styleStrategy = new HorizontalCellStyleStrategy(getHeaderCellStyles(), getContentCellStyles());
        // sheetName为sheet的名字，默认写第一个sheet
        EasyExcel.write(outFile, clazz)
                .registerWriteHandler(styleStrategy)
                .registerConverter(new LongStringConverter())
                .registerWriteHandler(new SimpleColumnWidthStyleStrategy(30)).sheet(sheetName)
                .doWrite(list);
    }

    public static void write(OutputStream outputStream, List<?> list, String sheetName) {
        Class<?> clazz = list.get(0).getClass();
        // 新版本会自动关闭流，不需要自己操作
        // 创建样式策略对象
        HorizontalCellStyleStrategy styleStrategy = new HorizontalCellStyleStrategy(getHeaderCellStyles(), getContentCellStyles());
        // sheetName为sheet的名字，默认写第一个sheet
        EasyExcel.write(outputStream, clazz)
                .registerWriteHandler(styleStrategy)
                .registerConverter(new LongStringConverter())
                .registerWriteHandler(new SimpleColumnWidthStyleStrategy(30)).sheet(sheetName)
                .doWrite(list);
    }

    /**
     * 文件下载（失败了会返回一个有部分数据的Excel），用于直接把excel返回到浏览器下载
     */
    public static void download(HttpServletResponse response, List<?> list, String sheetName) throws IOException {
        Class<?> clazz = list.get(0).getClass();
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode(sheetName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        // 创建样式策略对象
        HorizontalCellStyleStrategy styleStrategy = new HorizontalCellStyleStrategy(getHeaderCellStyles(), getContentCellStyles());
        // 输出表格
        EasyExcel.write(response.getOutputStream(), clazz)
                .registerWriteHandler(styleStrategy)
                .registerConverter(new LongStringConverter())
                .registerWriteHandler(new SimpleColumnWidthStyleStrategy(30)).sheet(sheetName)
                .doWrite(list);
    }

    /**
     * 设置表头样式
     */
    private static WriteCellStyle getHeaderCellStyles() {
        // 创建头部样式
        WriteCellStyle headCellStyle = new WriteCellStyle();
        headCellStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
        headCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        headCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        headCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        WriteFont headFont = new WriteFont();
        headFont.setFontName("宋体");
        headFont.setBold(true);
        headFont.setFontHeightInPoints((short) 12); // 设置字体大小
        headFont.setColor(IndexedColors.WHITE.getIndex());
        headCellStyle.setWriteFont(headFont);
        return headCellStyle;
    }

    /**
     * 设置内容样式
     */
    private static WriteCellStyle getContentCellStyles() {
        // 创建内容样式
        WriteCellStyle contentCellStyle = new WriteCellStyle();
        contentCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        contentCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return contentCellStyle;
    }
}
