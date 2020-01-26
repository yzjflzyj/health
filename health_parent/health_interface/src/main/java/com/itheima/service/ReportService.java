package com.itheima.service;

import java.util.Map;

public interface ReportService {
    /**
     * 获得运营统计数据
     * @return
     * @throws Exception
     */
    public Map<String,Object> getBusinessReport() throws Exception;
}
