package cn.summer.bus_side.interceptor;

import cn.summer.bus_side.dao.TicketDao;
import cn.summer.bus_side.dao.UserDao;
import cn.summer.bus_side.domain.HostHolder;
import cn.summer.bus_side.domain.LoginTicket;
import cn.summer.bus_side.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class PassportInterceptor implements HandlerInterceptor {
    @Autowired
    private TicketDao ticketDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private HostHolder hostHolder;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ticket = null;
        if (request.getCookies()!=null){
            for (Cookie cookie:request.getCookies()){
                if (cookie.getName().equals("ticket")){
                    ticket = cookie.getValue();
                    break;
                }
            }
        }

        if (ticket!=null){
            LoginTicket loginTicket = ticketDao.selectByTicket(ticket);
            if (loginTicket ==null||loginTicket.getExpired().before(new Date())||loginTicket.getStatus()!=0){
                return true;
            }

            User user = userDao.selectById(loginTicket.getUserId());
            hostHolder.setUser(user);

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if (modelAndView!=null&& hostHolder.getUser()!=null){
            modelAndView.addObject("user",hostHolder.getUser());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clear();

    }
}
