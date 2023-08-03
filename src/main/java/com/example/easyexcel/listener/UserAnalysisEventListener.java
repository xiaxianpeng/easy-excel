package com.example.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserAnalysisEventListener extends AnalysisEventListener {
    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        log.info("object:{}", o);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("analysisContext:{}", analysisContext);
    }
}
