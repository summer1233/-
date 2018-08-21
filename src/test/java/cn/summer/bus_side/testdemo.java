package cn.summer.bus_side;

import cn.summer.bus_side.dao.TicketDao;
import cn.summer.bus_side.dao.UserDao;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
public class testdemo {
    @Autowired
    TicketDao ticketDao;
    @Autowired
    UserDao userDao;
    @Test
    public  void test(){
        System.out.println(userDao.selectById("1"));

        //System.out.println(ticketDao.selectByTicket("09fc687b4b9e46219a291cd49dbcb7bc").getStatus());



        Date date = new Date();
        long nowtime = date.getTime();
        System.out.println(nowtime);
        JSON resultMessge = new JSONObject();
        System.out.println(resultMessge);


//
//        System.out.println(date.getTime());
//        date.setTime(date.getTime()+1000*3600*24);
//
//        System.out.println(date.getTime());




    }
}
