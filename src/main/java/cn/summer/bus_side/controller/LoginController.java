package cn.summer.bus_side.controller;

import cn.summer.bus_side.service.UserService;
import cn.summer.bus_side.util.busSideUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserService userService;
    @RequestMapping(path={"/reg/"},method = {RequestMethod.GET,RequestMethod.POST})
    public String reg(Model model, @RequestParam("userId") String userId,
                      @RequestParam("password") String password,
                      @RequestParam(value = "rememberme",defaultValue = "0") int rememberme,
                      HttpServletResponse response) {
        try {
            Map<String, Object> map = userService.register(userId, password);

            if (map.containsKey("ticket")) {
                Cookie cookie = new Cookie("ticket", (String) map.get("ticket"));
                cookie.setPath("/");
                if (rememberme>0){
                    cookie.setMaxAge(3600*24*5);
                }
                response.addCookie(cookie);
                model.addAttribute("resultMessge",busSideUtil.getJSONString(0, "注册成功"));
            } else {

                model.addAttribute("resultMessge",busSideUtil.getJSONString(1, map));
            }

        } catch (Exception e) {


            logger.error("注册异常" + e.getMessage());
            model.addAttribute("resultMessge",busSideUtil.getJSONString(1, "注册异常"));
        }
        return "/regLoginResult";

    }
    @RequestMapping(path={"/login/"},method = {RequestMethod.GET,RequestMethod.POST})
    public String login(Model model, @RequestParam("userId") String userId,
                        @RequestParam("password") String password,
                        @RequestParam(value = "rememberme",defaultValue = "0") int rememberme,
                        HttpServletResponse response
                        ){

        try{
            Map<String,Object> map = userService.login(userId,password);
            if (map.containsKey("ticket")){
                Cookie cookie = new Cookie("ticket", (String) map.get("ticket"));
                cookie.setPath("/");
                if (rememberme>0){
                    cookie.setMaxAge(3600*24*5);
                }
                response.addCookie(cookie);
                model.addAttribute("resultMessge",busSideUtil.getJSONString(0,"登录成功"));
                //return busSideUtil.getJSONString(0,"登录成功");
            }else {
                model.addAttribute("resultMessge",busSideUtil.getJSONString(1,map));
            }

        }catch (Exception e){
            logger.error("登录异常" +e.getMessage());
            model.addAttribute("resultMessge",busSideUtil.getJSONString(1,"登录异常"));
            //return busSideUtil.getJSONString(1,"登录异常");
        }
        return "/regLoginResult";
    }

//    @RequestMapping(path={"/regLoginResult"},method = {RequestMethod.GET,RequestMethod.POST})
//    public String regLoginResult(Model domainCES001){
//       domainCES001.addAttribute(resultMessage);
//        return "/regLoginResult";
//    }
    @RequestMapping(path={"/logout"},method = {RequestMethod.GET,RequestMethod.POST})
    public String logout(@CookieValue("ticket") String ticket){
        userService.logout(ticket);
        return "redirect:/";
    }



    }
