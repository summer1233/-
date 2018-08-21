package cn.summer.bus_side.domain;

import java.util.Date;

public class Bill {
    private Integer id;

    private String busId;

    private String cdNo;

    private String cdAmt;

    private Date dueDt;

    private String payInt;

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

    public String getCdNo() {
        return cdNo;
    }

    public void setCdNo(String cdNo) {
        this.cdNo = cdNo == null ? null : cdNo.trim();
    }

    public String getCdAmt() {
        return cdAmt;
    }

    public void setCdAmt(String cdAmt) {
        this.cdAmt = cdAmt == null ? null : cdAmt.trim();
    }

    public Date getDueDt() {
        return dueDt;
    }

    public void setDueDt(Date dueDt) {
        this.dueDt = dueDt;
    }

    public String getPayInt() {
        return payInt;
    }

    public void setPayInt(String payInt) {
        this.payInt = payInt == null ? null : payInt.trim();
    }

    public String getSetAmt() {
        return setAmt;
    }

    public void setSetAmt(String setAmt) {
        this.setAmt = setAmt == null ? null : setAmt.trim();
    }
}