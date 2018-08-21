package cn.summer.bus_side.service_impl;

import cn.summer.bus_side.dao.DealMapper;
import cn.summer.bus_side.domain.Deal;
import org.springframework.beans.factory.annotation.Autowired;
import share.BusSide_service.InfoRecFromMiddleware;
import share.util.MsgHandleResult;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;

public class InfoRecFromMiddlewareImp implements InfoRecFromMiddleware {
    @Autowired
    DealMapper dealMapper;
    @Override
    public MsgHandleResult sendMsgCES010ToBusSide(String busiId, String msgType,share.msg_class.CES010Msg.MainBody ces010Msg) {
        MsgHandleResult result = new MsgHandleResult();
        result.setResultCode("1");

        return result;
    }

    @Override
    public MsgHandleResult sendMsgCES002ToBusSide(String busiId, String msgType, share.msg_class.CES002Msg.MainBody ces002Msg) {
        MsgHandleResult result = new MsgHandleResult();
        try {
            Deal deal = new Deal();
            deal.setBusId(busiId);
            deal.setTrdDir(ces002Msg.getQuoteInf().getTrdDir().value());
            deal.setMsgType(msgType);
            deal.setStatus((byte)1);
            share.msg_class.CES002Msg.SlfInf slfInf = ces002Msg.getSlfInf();
            deal.setReqBranch(slfInf.getReqBranch());
            deal.setReqUser(slfInf.getReqUser());
            share.msg_class.CES002Msg.CpInf cpInf = ces002Msg.getCpInf();
            deal.setCpBranch(cpInf.getCpBranch());
            deal.setCpUser(cpInf.getCpUser());
            share.msg_class.CES002Msg. QuoteFctInf quoteFctInf = ces002Msg.getQuoteFctInf();
            deal.setCdType(quoteFctInf.getCdType().value());
            deal.setDrftNm(quoteFctInf.getDrftNm());
            deal.setSumAmt(quoteFctInf.getSumAmt().getCcy());
            deal.setQuoteTime(convertToDate(quoteFctInf.getQuoteTime()));
            deal.setSetTime(convertToDate(quoteFctInf.getSetTime()));
            deal.setSumAmt(quoteFctInf.getSumAmt().getValue().toString());

            dealMapper.insert(deal);

        }catch (Exception e){
            System.out.println("ces002保存失败"+e.getMessage());

        }

        result.setResultCode("1");

        return result;
    }

    @Override
    public MsgHandleResult sendMsgCES003ToBusSide(String busiId, String msgType, share.msg_class.CES003Msg.MainBody ces003Msg) {
        MsgHandleResult result = new MsgHandleResult();
        result.setResultCode("1");

        //传到前端，该笔交易已经成交
        return result;
    }

    @Override
    public MsgHandleResult sendMsgCES012ToBusSide(String busiId, String msgType, share.msg_class.CES012Msg.MainBody ces012Msg) {
        MsgHandleResult result = new MsgHandleResult();
        result.setResultCode("1");
        //传到前端，该笔交易已经终止
        return result;
    }


    public static Date convertToDate(XMLGregorianCalendar cal){
        return cal.toGregorianCalendar().getTime();
    }
}
