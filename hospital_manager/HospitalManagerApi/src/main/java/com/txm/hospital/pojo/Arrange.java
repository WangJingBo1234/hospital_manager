package com.txm.hospital.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

@TableName("arrange")
public class Arrange {
    @TableId(value = "ar_id")
    @JsonProperty("arId")
    private String arId;
    @JsonProperty("arTime")
    private String arTime;
    @JsonProperty("dId")
    private int dId;
    /**
     * 多表查询用
     */
    @TableField(exist = false)
    private Doctor doctor;

    public Arrange() {
    }

    public Arrange(String arId, String arTime, int dId, Doctor doctor) {
        this.arId = arId;
        this.arTime = arTime;
        this.dId = dId;
        this.doctor = doctor;
    }

    public String getArId() {
        return arId;
    }

    public void setArId(String arId) {
        this.arId = arId;
    }

    public String getArTime() {
        return arTime;
    }

    public void setArTime(String arTime) {
        this.arTime = arTime;
    }

    public int getdId() {
        return dId;
    }

    public void setdId(int dId) {
        this.dId = dId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Arrange{" +
                "arId='" + arId + '\'' +
                ", arTime='" + arTime + '\'' +
                ", dId=" + dId +
                ", doctor=" + doctor +
                '}';
    }
}
