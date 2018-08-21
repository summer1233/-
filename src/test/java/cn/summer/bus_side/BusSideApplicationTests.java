package cn.summer.bus_side;

import cn.summer.bus_side.dao.TicketDao;
import cn.summer.bus_side.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusSideApplicationTests {
    @Autowired
    TicketDao ticketDao;
    @Autowired
    UserDao userDao;
    @Test
    public void contextLoads() {
        //System.out.println(userDao.selectById("1"));
        Date expired = ticketDao.selectByTicket("98226d334ddc4571adfd33f530b380de").getExpired();
        System.out.println(expired);
        System.out.println(expired.getTime());

        Date date = new Date();
        long nowtime = date.getTime();
        System.out.println(nowtime);
        System.out.println(date);
        System.out.println(date.before(expired));


//
//        System.out.println(date.getTime());
//        date.setTime(date.getTime()+1000*3600*24);
//
//        System.out.println(date.getTime());




        }

}
