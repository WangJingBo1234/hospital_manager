package com.txm.hospital.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

@TableName("admini")
public class Admin {
    @TableId(value = "a_id")
    @JsonProperty("aId")
    private int aId;
    @JsonProperty("aPassword")
    private String aPassword;
    @JsonProperty("aName")
    private String aName;
    @JsonProperty("aGender")
    private String aGender;
    @JsonProperty("aCard")
    private String aCard;
    @JsonProperty("aEmail")
    private String aEmail;
    @JsonProperty("aPhone")
    private String aPhone;

    public Admin() {
    }

    public Admin(int aId, String aPassword, String aName, String aGender, String aCard, String aEmail, String aPhone) {
        this.aId = aId;
        this.aPassword = aPassword;
        this.aName = aName;
        this.aGender = aGender;
        this.aCard = aCard;
        this.aEmail = aEmail;
        this.aPhone = aPhone;
    }

    public int getAId() {
        return aId;
    }

    public void setAId(int aId) {
        this.aId = aId;
    }

    public String getAPassword() {
        return aPassword;
    }

    public void setAPassword(String aPassword) {
        this.aPassword = aPassword;
    }

    public String getAName() {
        return aName;
    }

    public void setAName(String aName) {
        this.aName = aName;
    }

    public String getAGender() {
        return aGender;
    }

    public void setAGender(String aGender) {
        this.aGender = aGender;
    }

    public String getACard() {
        return aCard;
    }

    public void setACard(String aCard) {
        this.aCard = aCard;
    }

    public String getAEmail() {
        return aEmail;
    }

    public void setAEmail(String aEmail) {
        this.aEmail = aEmail;
    }

    public String getAPhone() {
        return aPhone;
    }

    public void setAPhone(String aPhone) {
        this.aPhone = aPhone;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "aId=" + aId +
                ", aPassword='" + aPassword + '\'' +
                ", aName='" + aName + '\'' +
                ", aGender='" + aGender + '\'' +
                ", aCard='" + aCard + '\'' +
                ", aEmail='" + aEmail + '\'' +
                ", aPhone='" + aPhone + '\'' +
                '}';
    }
}
