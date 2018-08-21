package cn.summer.bus_side.service;

import cn.summer.bus_side.dao.TicketDao;
import cn.summer.bus_side.dao.UserDao;
import cn.summer.bus_side.domain.LoginTicket;
import cn.summer.bus_side.domain.User;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private TicketDao ticketDao;

    public Map<String,Object> register(String userId, String password){
        Map<String,Object> map = new HashMap<String, Object>();
        if (StringUtils.isBlank(userId)){
            map.put("userid","id不能为空");
            return map;

        }
        if (StringUtils.isBlank(password)){
            map.put("password","password不能为空");
            return map;
        }
        //System.out.println(userId+","+password);
        User user = userDao.selectById(userId);
       // System.out.println(user.getUserId()+","+user.getPassword());

        if ((user !=null)){
            map.put("userId","userid已经被注册");
            System.out.println(map);
            return map;
        }

        user = new User();
        user.setUserId(userId);
        user.setPassword(password);
        userDao.addUser(user);

        String ticket = addLoginTicket(user.getUserId());
        map.put("ticket",ticket);
        return map;
    }

    public Map<String,Object> login(String userId, String password){
        Map<String,Object> map = new HashMap<String, Object>();
        if (StringUtils.isBlank(userId)){
            map.put("userid","id不能为空");
            return map;

        }
        if (StringUtils.isBlank(password)){
            map.put("password","password不能为空");
            return map;
        }

        User user = userDao.selectById(userId);

        if ((user ==null)){
            map.put("userId","userid不存在");
            return map;
        }
        if(!password.equals(user.getPassword())){
            map.put("password","密码不正确");
            return map;

        }

        String ticket = addLoginTicket(user.getUserId());
        map.put("ticket",ticket);

        return map;
    }
    //ticket
    private String addLoginTicket(String userId){
        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(userId);
        Date date = new Date();
        date.setTime(date.getTime()+1000*3600*24);
        ticket.setExpired(date);
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-",""));
        ticketDao.addTicket(ticket);
        return ticket.getTicket();

    }
    public String loginProve(String ticket){
        LoginTicket ticket1 = new LoginTicket();
        ticket1 = ticketDao.selectByTicket(ticket);
        Date nowTime = new Date();

        if (ticket1.getStatus()==0&&nowTime.before(ticket1.getExpired())){
            return ticket1.getUserId();
        }
        else {
            return null;
        }


    }
    public void logout(String ticket){
        ticketDao.updateStatus(ticket,1);

    }
}
