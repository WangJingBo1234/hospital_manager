package com.txm.hospital.service;

import java.util.List;

import com.txm.hospital.pojo.Arrange;

public interface ArrangeService {
    /**
     * 根据日期查询排班信息
     */
    List<Arrange> findByTime(String arTime, String dSection);
    /**
     * 增加排班信息
     */
    Boolean addArrange(Arrange arrange);
    /**
     * 删除排班信息
     */
    Boolean deleteArrange(String arId);
}
