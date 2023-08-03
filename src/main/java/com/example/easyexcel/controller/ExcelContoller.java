package com.example.easyexcel.controller;

import java.io.IOException;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.example.easyexcel.listener.UserAnalysisEventListener;
import com.example.easyexcel.model.UserModel;
import com.example.easyexcel.util.ExcelHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/excel")
public class ExcelContoller {
    @PostMapping("/syncReadExcel")
    public Object syncReadExcel(@RequestParam("uploadingFile") MultipartFile uploadingFile) throws IOException {
        return ExcelHelper.syncReadExcel(uploadingFile.getInputStream(), UserModel.class, ExcelTypeEnum.XLSX);
    }

    @PostMapping("/asyncReadExcel")
    public Object asyncReadExcel(@RequestParam("uploadingFile") MultipartFile uploadingFile) throws IOException {
        ExcelHelper.asyncReadExcel(uploadingFile.getInputStream(), ExcelTypeEnum.XLSX, new UserAnalysisEventListener(), UserModel.class);
        return "success";
    }
}
