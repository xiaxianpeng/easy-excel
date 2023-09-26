package com.example.easyexcel.util;

import java.util.Collection;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

public class WriteSheetHolder {
    private ExcelWriter excelWriter;
    private WriteSheet writeSheet;

    public WriteSheetHolder(ExcelWriter excelWriter, WriteSheet writeSheet) {
        this.excelWriter = excelWriter;
        this.writeSheet = writeSheet;
    }

    public void writeSheetData(Collection<?> data) {
        excelWriter.write(data, writeSheet);
    }

}
