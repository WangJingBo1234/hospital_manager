package com.txm.hospital.controller;

import com.txm.hospital.pojo.Arrange;
import com.txm.hospital.service.ArrangeService;
import com.txm.hospital.utils.ResponseData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/arrange")
public class ArrangeController {
    @Autowired
    private ArrangeService arrangeService;
    /**
     * 根据日期查询排班信息
     */
    @RequestMapping("findByTime")
    public ResponseData findByTime(@RequestParam(value = "arTime") String arTime, @RequestParam(value = "dSection") String dSection) {
        return ResponseData.success("根据日期查询排班信息成功", this.arrangeService.findByTime(arTime, dSection));
    }
    /**
     * 增加排班信息
     */
    @RequestMapping("addArrange")
    public ResponseData addArrange(Arrange arrange){
        if (this.arrangeService.addArrange(arrange))
            return ResponseData.success("增加排班信息成功");
        return ResponseData.fail("该医生该日已排班");
    }

    /**
     * 删除排班信息
     */
    @RequestMapping("deleteArrange")
    public ResponseData deleteArrange(String arId){
        if (this.arrangeService.deleteArrange(arId))
            return ResponseData.success("删除排班信息成功");
        return ResponseData.fail("排班信息不存在");
    }

}
