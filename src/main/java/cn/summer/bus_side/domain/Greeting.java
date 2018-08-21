package cn.summer.bus_side.domain;

import cn.summer.bus_side.domainCES001.MainBody;

public class Greeting extends MainBody {
    private int Id;
    private String BusId;
    private String MsgType;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getBusId() {
        return BusId;
    }

    public void setBusId(String busId) {
        BusId = busId;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }


    //    private String userId;
//    private String msg;
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//
//        this.userId = userId;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
}
