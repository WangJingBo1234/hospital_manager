package com.txm.hospital.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

@TableName("patient")
public class Patient {
    @TableId(value = "p_id")
    @JsonProperty("pId")
    private int pId;
    @JsonProperty("pPassword")
    //@TableField(select = false)
    private String pPassword;
    @JsonProperty("pName")
    private String pName;
    @JsonProperty("pGender")
    private String pGender;
    @JsonProperty("pCard")
    private String pCard;
    @JsonProperty("pEmail")
    private String pEmail;
    @JsonProperty("pPhone")
    private String pPhone;
    @JsonProperty("pState")
    private Integer pState;
    @JsonProperty("pBirthday")
    private String pBirthday;
    @JsonProperty("pAge")
    private Integer pAge;

    public Patient() {
    }

    public Patient(int pId, String pPassword, String pName, String pGender, String pCard, String pEmail, String pPhone, Integer pState, String pBirthday, Integer pAge) {
        this.pId = pId;
        this.pPassword = pPassword;
        this.pName = pName;
        this.pGender = pGender;
        this.pCard = pCard;
        this.pEmail = pEmail;
        this.pPhone = pPhone;
        this.pState = pState;
        this.pBirthday = pBirthday;
        this.pAge = pAge;
    }

    public int getPId() {
        return pId;
    }

    public void setPId(int pId) {
        this.pId = pId;
    }

    public String getPPassword() {
        return pPassword;
    }

    public void setPPassword(String pPassword) {
        this.pPassword = pPassword;
    }

    public String getPName() {
        return pName;
    }

    public void setPName(String pName) {
        this.pName = pName;
    }

    public String getPGender() {
        return pGender;
    }

    public void setPGender(String pGender) {
        this.pGender = pGender;
    }

    public String getPCard() {
        return pCard;
    }

    public void setPCard(String pCard) {
        this.pCard = pCard;
    }

    public String getPEmail() {
        return pEmail;
    }

    public void setPEmail(String pEmail) {
        this.pEmail = pEmail;
    }

    public String getPPhone() {
        return pPhone;
    }

    public void setPPhone(String pPhone) {
        this.pPhone = pPhone;
    }

    public Integer getPState() {
        return pState;
    }

    public void setPState(Integer pState) {
        this.pState = pState;
    }

    public String getPBirthday() {
        return pBirthday;
    }

    public void setPBirthday(String pBirthday) {
        this.pBirthday = pBirthday;
    }

    public Integer getPAge() {
        return pAge;
    }

    public void setPAge(Integer pAge) {
        this.pAge = pAge;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "pId=" + pId +
                ", pPassword='" + pPassword + '\'' +
                ", pName='" + pName + '\'' +
                ", pGender='" + pGender + '\'' +
                ", pCard='" + pCard + '\'' +
                ", pEmail='" + pEmail + '\'' +
                ", pPhone='" + pPhone + '\'' +
                ", pState=" + pState +
                ", pBirthday='" + pBirthday + '\'' +
                ", pAge=" + pAge +
                '}';
    }
}
