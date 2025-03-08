package com.txm.hospital.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

@TableName("drug")
public class Drug {
    @TableId(value = "dr_id")
    @JsonProperty("drId")
    private int drId;
    @JsonProperty("drName")
    private String drName;
    @JsonProperty("drPrice")
    private double drPrice;
    @JsonProperty("drNumber")
    private int drNumber;
    @JsonProperty("drUnit")
    private String drUnit;
    @JsonProperty("drPublisher")
    private String drPublisher;

    public Drug() {
    }

    public Drug(int drId, String drName, double drPrice, int drNumber, String drUnit, String drPublisher) {
        this.drId = drId;
        this.drName = drName;
        this.drPrice = drPrice;
        this.drNumber = drNumber;
        this.drUnit = drUnit;
        this.drPublisher = drPublisher;
    }

    public int getDrId() {
        return drId;
    }

    public void setDrId(int drId) {
        this.drId = drId;
    }

    public String getDrName() {
        return drName;
    }

    public void setDrName(String drName) {
        this.drName = drName;
    }

    public double getDrPrice() {
        return drPrice;
    }

    public void setDrPrice(double drPrice) {
        this.drPrice = drPrice;
    }

    public int getDrNumber() {
        return drNumber;
    }

    public void setDrNumber(int drNumber) {
        this.drNumber = drNumber;
    }

    public String getDrUnit() {
        return drUnit;
    }

    public void setDrUnit(String drUnit) {
        this.drUnit = drUnit;
    }

    public String getDrPublisher() {
        return drPublisher;
    }

    public void setDrPublisher(String drPublisher) {
        this.drPublisher = drPublisher;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "drId=" + drId +
                ", drName='" + drName + '\'' +
                ", drPrice=" + drPrice +
                ", drNumber=" + drNumber +
                ", drUnit='" + drUnit + '\'' +
                ", drPublisher='" + drPublisher + '\'' +
                '}';
    }
}
