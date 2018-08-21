package cn.summer.bus_side.domain;

import java.util.Date;

public class Deal {
    private Integer id;

    private String busId;

    private Byte status;

    private String trdDir;

    private String msgType;

    private String reqBranch;

    private String reqUser;

    private String cpBranch;

    private String cpUser;

    private String cdType;

    private String drftNm;

    private String sumAmt;

    private Date quoteTime;

    private Date setTime;

    private String setAmt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId == null ? null : busId.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getTrdDir() {
        return trdDir;
    }

    public void setTrdDir(String trdDir) {
        this.trdDir = trdDir == null ? null : trdDir.trim();
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
    }

    public String getReqBranch() {
        return reqBranch;
    }

    public void setReqBranch(String reqBranch) {
        this.reqBranch = reqBranch == null ? null : reqBranch.trim();
    }

    public String getReqUser() {
        return reqUser;
    }

    public void setReqUser(String reqUser) {
        this.reqUser = reqUser == null ? null : reqUser.trim();
    }

    public String getCpBranch() {
        return cpBranch;
    }

    public void setCpBranch(String cpBranch) {
        this.cpBranch = cpBranch == null ? null : cpBranch.trim();
    }

    public String getCpUser() {
        return cpUser;
    }

    public void setCpUser(String cpUser) {
        this.cpUser = cpUser == null ? null : cpUser.trim();
    }

    public String getCdType() {
        return cdType;
    }

    public void setCdType(String cdType) {
        this.cdType = cdType == null ? null : cdType.trim();
    }

    public String getDrftNm() {
        return drftNm;
    }

    public void setDrftNm(String drftNm) {
        this.drftNm = drftNm == null ? null : drftNm.trim();
    }

    public String getSumAmt() {
        return sumAmt;
    }

    public void setSumAmt(String sumAmt) {
        this.sumAmt = sumAmt == null ? null : sumAmt.trim();
    }

    public Date getQuoteTime() {
        return quoteTime;
    }

    public void setQuoteTime(Date quoteTime) {
        this.quoteTime = quoteTime;
    }

    public Date getSetTime() {
        return setTime;
    }

    public void setSetTime(Date setTime) {
        this.setTime = setTime;
    }

    public String getSetAmt() {
        return setAmt;
    }

    public void setSetAmt(String setAmt) {
        this.setAmt = setAmt == null ? null : setAmt.trim();
    }
}