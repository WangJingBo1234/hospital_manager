package com.txm.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.txm.hospital.pojo.Bed;

public interface BedMapper extends BaseMapper<Bed> {
    /**
     * 统计今天住院人数
     */
    int bedPeople(String bStart);
}
