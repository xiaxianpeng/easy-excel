package com.example.easyexcel.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.example.easyexcel.listener.UserAnalysisEventListener;
import com.example.easyexcel.model.UserModel;
import com.example.easyexcel.util.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/excel")
public class ExcelContoller {

    @Autowired
    ResourceLoader resourceLoader;

    @PostMapping("/syncReadExcel")
    public Object syncReadExcel(@RequestParam("uploadingFile") MultipartFile uploadingFile) throws IOException {
        return ExcelHelper.syncReadExcel(uploadingFile.getInputStream(), UserModel.class, ExcelTypeEnum.XLSX);
    }

    @PostMapping("/asyncReadExcel")
    public Object asyncReadExcel(@RequestParam("uploadingFile") MultipartFile uploadingFile) throws IOException {
        ExcelHelper.asyncReadExcel(uploadingFile.getInputStream(), ExcelTypeEnum.XLSX, new UserAnalysisEventListener(), UserModel.class);
        return "success";
    }

    @GetMapping("/log")
    public String log() {
        return "success";
    }

    @GetMapping("/export")
    public String export() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("user10.xlsx");
            List<UserModel> data = UserModel.generateUserList(10);
            ExcelHelper.exportExcel(fileOutputStream, ExcelTypeEnum.XLSX, UserModel.class, data);
            return "success";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "failure";
        }
    }

    @GetMapping("/exportExcelWithTemplate")
    public String exportExcelWithTemplate() {
        try {
            Resource resource = resourceLoader.getResource("classpath:templates/user-export-template.xlsx");
            OutputStream outputStream = new FileOutputStream("user100.xlsx");
            InputStream templateInputStream = resource.getInputStream();
            List<UserModel> data = UserModel.generateUserList(100);
            ExcelHelper.exportExcelWithTemplate(outputStream, ExcelTypeEnum.XLSX, UserModel.class, templateInputStream, data);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }

    @GetMapping("/exportMore")
    public String exportMore() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("user100000.xlsx");
            List<UserModel> data = UserModel.generateUserList(100000);
            ExcelHelper.exportExcel(fileOutputStream,
                    ExcelTypeEnum.XLSX,
                    UserModel.class,
                    writeSheetHolder -> writeSheetHolder.writeSheetData(data));
            return "success";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "failure";
        }
    }

    @GetMapping("/exportExcelMoreWithTemplate")
    public String exportExcelMoreWithTemplate() {
        try {
            Resource resource = resourceLoader.getResource("classpath:templates/user-export-template.xlsx");
            OutputStream outputStream = new FileOutputStream("user150000.xlsx");
            InputStream templateInputStream = resource.getInputStream();
            List<UserModel> data = UserModel.generateUserList(150000);
            ExcelHelper.exportExcelWithTemplate(outputStream,
                    ExcelTypeEnum.XLSX,
                    UserModel.class,
                    templateInputStream,
                    fillSheetHolder -> {
                        fillSheetHolder.writeSheetData(data);
                    });
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }
}
