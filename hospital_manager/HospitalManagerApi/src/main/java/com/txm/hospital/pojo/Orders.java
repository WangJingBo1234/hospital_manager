package com.txm.hospital.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

@TableName("orders")
public class Orders {
    @TableId(value = "o_id")
    @JsonProperty("oId")
    private int oId;
    @JsonProperty("pId")
    private int pId;
    @JsonProperty("dId")
    private int dId;
    @JsonProperty("oRecord")
    private String oRecord;
    @JsonProperty("oStart")
    private String oStart;
    @JsonProperty("oEnd")
    private String oEnd;
    @JsonProperty("oState")
    private Integer oState;
    @JsonProperty("oDrug")
    private String oDrug;
    @JsonProperty("oCheck")
    private String oCheck;
    @JsonProperty("oTotalPrice")
    private Double oTotalPrice;
    @JsonProperty("oPriceState")
    private Integer oPriceState;
    @JsonProperty("countGender")
    @TableField(exist = false)
    private Integer countGender;
    @JsonProperty("oAdvice")
    private String oAdvice;
    //多表查询用
    @TableField(exist = false)//声明不是数据库里面的字段
    private Doctor doctor;
    //多表查询用
    @TableField(exist = false)//声明不是数据库里面的字段
    private Patient patient;
    @TableField(exist = false)
    private Integer countSection;
    @JsonProperty("dName")
    @TableField(exist = false)
    private String dName;
    @JsonProperty("pName")
    @TableField(exist = false)
    private String pName;

    public Orders() {
    }

    public Orders(int oId, int pId, int dId, String oRecord, String oStart, String oEnd, Integer oState, String oDrug, String oCheck, Double oTotalPrice, Integer oPriceState, Integer countGender, String oAdvice, Doctor doctor, Patient patient, Integer countSection, String dName, String pName) {
        this.oId = oId;
        this.pId = pId;
        this.dId = dId;
        this.oRecord = oRecord;
        this.oStart = oStart;
        this.oEnd = oEnd;
        this.oState = oState;
        this.oDrug = oDrug;
        this.oCheck = oCheck;
        this.oTotalPrice = oTotalPrice;
        this.oPriceState = oPriceState;
        this.countGender = countGender;
        this.oAdvice = oAdvice;
        this.doctor = doctor;
        this.patient = patient;
        this.countSection = countSection;
        this.dName = dName;
        this.pName = pName;
    }

    public int getOId() {
        return oId;
    }

    public void setOId(int oId) {
        this.oId = oId;
    }

    public int getPId() {
        return pId;
    }

    public void setPId(int pId) {
        this.pId = pId;
    }

    public int getdId() {
        return dId;
    }

    public void setdId(int dId) {
        this.dId = dId;
    }

    public String getORecord() {
        return oRecord;
    }

    public void setORecord(String oRecord) {
        this.oRecord = oRecord;
    }

    public String getOStart() {
        return oStart;
    }

    public void setOStart(String oStart) {
        this.oStart = oStart;
    }

    public String getOEnd() {
        return oEnd;
    }

    public void setOEnd(String oEnd) {
        this.oEnd = oEnd;
    }

    public Integer getOState() {
        return oState;
    }

    public void setOState(Integer oState) {
        this.oState = oState;
    }

    public String getODrug() {
        return oDrug;
    }

    public void setODrug(String oDrug) {
        this.oDrug = oDrug;
    }

    public String getOCheck() {
        return oCheck;
    }

    public void setOCheck(String oCheck) {
        this.oCheck = oCheck;
    }

    public Double getOTotalPrice() {
        return oTotalPrice;
    }

    public void setOTotalPrice(Double oTotalPrice) {
        this.oTotalPrice = oTotalPrice;
    }

    public Integer getOPriceState() {
        return oPriceState;
    }

    public void setOPriceState(Integer oPriceState) {
        this.oPriceState = oPriceState;
    }

    public Integer getCountGender() {
        return countGender;
    }

    public void setCountGender(Integer countGender) {
        this.countGender = countGender;
    }

    public String getOAdvice() {
        return oAdvice;
    }

    public void setOAdvice(String oAdvice) {
        this.oAdvice = oAdvice;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Integer getCountSection() {
        return countSection;
    }

    public void setCountSection(Integer countSection) {
        this.countSection = countSection;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getPName() {
        return pName;
    }

    public void setPName(String pName) {
        this.pName = pName;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "oId=" + oId +
                ", pId=" + pId +
                ", dId=" + dId +
                ", oRecord='" + oRecord + '\'' +
                ", oStart='" + oStart + '\'' +
                ", oEnd='" + oEnd + '\'' +
                ", oState=" + oState +
                ", oDrug='" + oDrug + '\'' +
                ", oCheck='" + oCheck + '\'' +
                ", oTotalPrice=" + oTotalPrice +
                ", oPriceState=" + oPriceState +
                ", countGender=" + countGender +
                ", oAdvice='" + oAdvice + '\'' +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", countSection=" + countSection +
                ", dName='" + dName + '\'' +
                ", pName='" + pName + '\'' +
                '}';
    }
}
