package com.example.easyexcel.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;

/**
 * @author xiaxianpeng
 */
public class ExcelHelper {
    private ExcelHelper() {
    }

    /**
     * 同步读取excel
     *
     * @param inputStream   输入流
     * @param clazz         类型
     * @param excelTypeEnum excel类型
     * @param <T>           泛型
     * @return 数据
     */
    public static <T> List<T> syncReadExcel(InputStream inputStream,
                                            Class<T> clazz,
                                            ExcelTypeEnum excelTypeEnum) {
        return EasyExcelFactory.read(inputStream).excelType(excelTypeEnum).head(clazz).sheet().doReadSync();
    }

    /**
     * 同步读取excel
     *
     * @param inputStream   输入流
     * @param clazz         类型
     * @param excelTypeEnum excel类型
     * @param sheetNo       sheet数
     * @param headRowNumber 头行数
     * @param <T>           泛型
     * @return
     */
    public static <T> List<T> syncReadExcel(InputStream inputStream,
                                            Class<T> clazz,
                                            ExcelTypeEnum excelTypeEnum,
                                            Integer sheetNo,
                                            Integer headRowNumber) {
        int newHeadRowNumber = headRowNumber == null ? 1 : headRowNumber;
        return EasyExcelFactory.read(inputStream)
                .excelType(excelTypeEnum)
                .head(clazz)
                .sheet(sheetNo).
                headRowNumber(newHeadRowNumber).
                doReadSync();
    }

    /**
     * 异步读取excel
     *
     * @param inputStream   输入流
     * @param clazz         类型
     * @param excelTypeEnum excel类型
     * @param <T>           泛型
     * @return
     */
    public static <T> void asyncReadExcel(InputStream inputStream,
                                          ExcelTypeEnum excelTypeEnum,
                                          ReadListener<T> excelListener,
                                          Class clazz) {
        EasyExcelFactory.read(inputStream, clazz, excelListener).excelType(excelTypeEnum).sheet().doRead();
    }

    /**
     * 异步读取excel
     *
     * @param inputStream   输入流
     * @param clazz         类型
     * @param excelTypeEnum excel类型
     * @param sheetNo       sheet数
     * @param headRowNumber 头行数
     * @param <T>           泛型
     */
    public static <T> void asyncReadExcel(InputStream inputStream,
                                          ExcelTypeEnum excelTypeEnum,
                                          ReadListener<T> excelListener,
                                          Class clazz, Integer sheetNo,
                                          Integer headRowNumber) {
        int newHeadRowNumber = headRowNumber == null ? 1 : headRowNumber;
        EasyExcelFactory.read(inputStream, clazz, excelListener).excelType(excelTypeEnum).sheet(sheetNo).headRowNumber(newHeadRowNumber).doRead();
    }

    /**
     * 无模板导出excel文件
     *
     * @param outputStream  文件流
     * @param excelTypeEnum 文件格式
     * @param clazz         表头类型
     * @param data          数据
     * @param <T>           泛型
     */
    public static <T> void exportExcel(OutputStream outputStream,
                                       ExcelTypeEnum excelTypeEnum,
                                       Class<T> clazz,
                                       Collection<?> data) {
        ExcelWriter excelWriter = EasyExcel.write(outputStream, clazz).excelType(excelTypeEnum).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        excelWriter.write(data, writeSheet);
        excelWriter.finish();
    }


    /**
     * 指定模板导出excel文件
     *
     * @param outputStream        文件流
     * @param excelTypeEnum       文件格式
     * @param clazz               表头类型
     * @param templateInputStream 模板
     * @param data                数据
     * @param <T>                 泛型
     */
    public static <T> void exportExcelWithTemplate(OutputStream outputStream,
                                                   ExcelTypeEnum excelTypeEnum,
                                                   Class<T> clazz,
                                                   InputStream templateInputStream,
                                                   Collection<?> data) {
        ExcelWriter excelWriter = EasyExcel.write(outputStream, clazz)
                .excelType(excelTypeEnum).withTemplate(templateInputStream).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        excelWriter.fill(data, writeSheet);
        excelWriter.finish();
    }

}
