package com.example.easyexcel.util;

import java.io.InputStream;
import java.util.List;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;

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
    public static <T> List<T> syncReadExcel(InputStream inputStream, Class<T> clazz, ExcelTypeEnum excelTypeEnum) {
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
    public static <T> List<T> syncReadExcel(InputStream inputStream, Class<T> clazz, ExcelTypeEnum excelTypeEnum, Integer sheetNo, Integer headRowNumber) {
        int newHeadRowNumber = headRowNumber == null ? 1 : headRowNumber;
        return EasyExcelFactory.read(inputStream).excelType(excelTypeEnum).head(clazz).sheet(sheetNo).headRowNumber(newHeadRowNumber).doReadSync();
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
    public static <T> Object asyncReadExcel(InputStream inputStream, ExcelTypeEnum excelTypeEnum, ReadListener<T> excelListener, Class clazz) {
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
    public static <T> void asyncReadExcel(InputStream inputStream, ExcelTypeEnum excelTypeEnum, ReadListener<T> excelListener, Class clazz, Integer sheetNo, Integer headRowNumber) {
        int newHeadRowNumber = headRowNumber == null ? 1 : headRowNumber;
        EasyExcelFactory.read(inputStream, clazz, excelListener).excelType(excelTypeEnum).sheet(sheetNo).headRowNumber(newHeadRowNumber).doRead();
    }


}
