package cn.summer.bus_side.service;

import cn.summer.bus_side.dao.BillMapper;
import cn.summer.bus_side.dao.DealMapper;
import cn.summer.bus_side.dao.GreetingDao;
import cn.summer.bus_side.domain.Deal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.middle_service.MsgHandlerForBusi;
import share.msg_class.CES001Msg.MainBody;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class GreetingService {
    @Autowired
    public GreetingDao greetingDao;
    @Autowired
    public DealMapper dealMapper;
    @Autowired
    public  BillMapper billMapper;


    private MsgHandlerForBusi msgHandlerForBusi;

    public void insertDeal(Deal deal){

        dealMapper.insert(deal);
        //billMapper.insert(bill);


        //调用接口传递参数
        //send001Msg(deal);

    }
    public void resultDeal(Deal deal, String recCode){
       // send011Msg(deal,recCode);
    }

    private void send001Msg(Deal deal){
        MainBody mainBody001 = new MainBody();
        createCES001Object(mainBody001, deal);
        msgHandlerForBusi.sendMsgCES001ToShcpe(deal.getBusId(),deal.getMsgType(),mainBody001);
    }
    private void  send011Msg(Deal deal, String recCode){
        String busId = deal.getBusId();
        String msgType = deal.getMsgType();
        share.msg_class.CES011Msg.MainBody mainBody011 = new share.msg_class.CES011Msg.MainBody();
        createCES011Object(mainBody011,deal,recCode);
        msgHandlerForBusi.sendMsgCES011ToShcpe(busId,msgType,mainBody011);

    }
    public List<Deal> listByBusId(String bus_id){

        return dealMapper.selectByBusId(bus_id);
    }

    public List<Deal> selectByStatus(Byte status){
        return dealMapper.selectByStatus(status);


    }

    private void createCES001Object(share.msg_class.CES001Msg.MainBody ces001Msg, Deal deal) {
        ces001Msg.setMsgId(new share.msg_class.CES001Msg.MsgId());
        ces001Msg.setOrgnlMsgId(new share.msg_class.CES001Msg.OrgnlMsgId());
        ces001Msg.setQuoteInf(new share.msg_class.CES001Msg.QuoteInf());
        ces001Msg.setSlfInf(new share.msg_class.CES001Msg.SlfInf());
        ces001Msg.setBlist(new share.msg_class.CES001Msg.Blist());
        ces001Msg.setCpInf(new share.msg_class.CES001Msg.CpInf());
        ces001Msg.setQuoteFctInf(new share.msg_class.CES001Msg.QuoteFctInf());
        share.msg_class.CES001Msg.CurrencyAndAmount currencyAndAmount = new share.msg_class.CES001Msg.CurrencyAndAmount();
        share.msg_class.CES001Msg.Bill bill = new share.msg_class.CES001Msg.Bill();
        //格式化时间
        String ISODateTimeFormater = "yyyy-MM-dd'T'HH:mm:ss";
        String ISODateFormater = "yyyy-MM-dd";
        String ISOTimeFormater = "HH:mm:ss";
        DateFormat dateTimeFormat = new SimpleDateFormat(ISODateTimeFormater);
        DateFormat dateFormat = new SimpleDateFormat(ISODateFormater);
        DateFormat timeFormat = new SimpleDateFormat(ISOTimeFormater);
        Date date = new Date();
        XMLGregorianCalendar isoDateTime = null;
        XMLGregorianCalendar isoDate = null;
        XMLGregorianCalendar isoTime = null;
        try {
            isoDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateFormat.format(date));
            isoDateTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateTimeFormat.format(date));
            isoTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(timeFormat.format(date));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        //报文标识
        ces001Msg.getMsgId().setId("264748363726473637273646372727");
        ces001Msg.getMsgId().setCreDtTm(isoDateTime);
        //原报文标识
        ces001Msg.getOrgnlMsgId().setId("264748363726473637273646372727");
        ces001Msg.getOrgnlMsgId().setCreDtTm(isoDateTime);
        //报价单信息
        ces001Msg.getQuoteInf().setTrdDir(share.msg_class.CES001Msg.TrdDir.fromValue("TDD01"));
        ces001Msg.getQuoteInf().setQuoteOp("0");
        ces001Msg.getQuoteInf().setBusiType(share.msg_class.CES001Msg.BusiType.fromValue("BT01"));
        //本方信息
        ces001Msg.getSlfInf().setReqBranch(deal.getReqBranch());
        ces001Msg.getSlfInf().setProductId("000000000");
        ces001Msg.getSlfInf().setReqUser(deal.getReqUser());
        //对方信息
        ces001Msg.getCpInf().setCpBranch(deal.getCpBranch());
        ces001Msg.getCpInf().setCpProductId("222222222");
        ces001Msg.getCpInf().setCpUser(deal.getReqUser());
        //报价信息
        ces001Msg.getQuoteFctInf().setCdType(share.msg_class.CES001Msg.CdType.fromValue(deal.getCdType()));
        ces001Msg.getQuoteFctInf().setCdMedia(share.msg_class.CES001Msg.CdMedia.fromValue("ME01"));
        ces001Msg.getQuoteFctInf().setDrftNm(deal.getDrftNm());
        currencyAndAmount.setCcy("CNY");
        currencyAndAmount.setValue(new BigDecimal(deal.getSetAmt()));
        ces001Msg.getQuoteFctInf().setSumAmt(currencyAndAmount);
        ces001Msg.getQuoteFctInf().setTenorDays("20");
        ces001Msg.getQuoteFctInf().setSubDeal("1");
        ces001Msg.getQuoteFctInf().setQuoteTime(xmlToDate(deal.getQuoteTime()));
        ces001Msg.getQuoteFctInf().setSetSpeed(share.msg_class.CES001Msg.SetSpeed.fromValue("CS00"));
        ces001Msg.getQuoteFctInf().setClrTp(share.msg_class.CES001Msg.ClrTp.fromValue("CT01"));
        ces001Msg.getQuoteFctInf().setSetTime(xmlToDate(deal.getSetTime()));
        ces001Msg.getQuoteFctInf().setSetMode(share.msg_class.CES001Msg.SetMode.fromValue("ST01"));
        ces001Msg.getQuoteFctInf().setSetAmt(currencyAndAmount);
        ces001Msg.getQuoteFctInf().setSetDate(isoDate);
        ces001Msg.getQuoteFctInf().setTrdRate("0.534");
        ces001Msg.getQuoteFctInf().setPayInt(currencyAndAmount);
        ces001Msg.getQuoteFctInf().setYieldRate("12.22");
        //票据清单
        bill.setCdNo("01");
        bill.setCdAmt(currencyAndAmount);
        bill.setDueDt(isoDate);
        bill.setMatDt(isoDate);
        bill.setTenorDays("23");
        bill.setPayInt(currencyAndAmount);
        bill.setSetAmt(currencyAndAmount);
        for (int i = 0; i < 10; i++) {
            ces001Msg.getBlist().getBill().add(bill);
        }
    }
    private void createCES011Object(share.msg_class.CES011Msg.MainBody ces011Msg,Deal deal,String recCode) {
        ces011Msg.setMsgId(new share.msg_class.CES011Msg.MsgId());
        ces011Msg.setOrgnlMsgId(new share.msg_class.CES011Msg.OrgnlMsgId());
        ces011Msg.setQuoteInf(new share.msg_class.CES011Msg.QuoteInf());
        ces011Msg.setRecInf(new share.msg_class.CES011Msg.RecInf());
        String ISODateTimeFormater = "yyyy-MM-dd'T'HH:mm:ss";
        DateFormat dateTimeFormat = new SimpleDateFormat(ISODateTimeFormater);
        Date date = new Date();
        XMLGregorianCalendar isoDateTime = null;
        try {
            isoDateTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateTimeFormat.format(date));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        //报文标识
        ces011Msg.getMsgId().setId("11");
        ces011Msg.getMsgId().setCreDtTm(isoDateTime);
        //原报文标识
        ces011Msg.getOrgnlMsgId().setId("12345678901928374657348372361637473");
        ces011Msg.getOrgnlMsgId().setCreDtTm(isoDateTime);
        //报价单信息
        ces011Msg.getQuoteInf().setBusiType(share.msg_class.CES011Msg.BusiType.fromValue("BT01"));
        //应答信息
        ces011Msg.getRecInf().setRecBranch(deal.getReqBranch());
        ces011Msg.getRecInf().setRecUser(deal.getReqUser());
        ces011Msg.getRecInf().setRecProductId("111111111");
        ces011Msg.getRecInf().setRecCmd(recCode);
    }

    public XMLGregorianCalendar xmlToDate(Date date){
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar gc = null;
        try {
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gc;
    }

}
