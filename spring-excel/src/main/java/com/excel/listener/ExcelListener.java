package com.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Listener 监听excel读取
 * @author  yh
 * @date  2022/6/3
 */

public class ExcelListener<T> extends AnalysisEventListener<T> {
    private static final int BATCH_COUNT = 3000;
    private List<T> datas = new ArrayList();

    public ExcelListener() {
    }

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        this.datas.add(t);
    }

    public List<T> getDatas() {
        return this.datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }
}