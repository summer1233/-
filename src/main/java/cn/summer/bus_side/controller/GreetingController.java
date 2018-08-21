package cn.summer.bus_side.controller;

import cn.summer.bus_side.dao.DealMapper;
import cn.summer.bus_side.domain.Deal;
import cn.summer.bus_side.domain.LoginTicket;
import cn.summer.bus_side.domain.User;
import cn.summer.bus_side.service.GreetingService;
import cn.summer.bus_side.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class GreetingController {
    @Autowired
    UserService userService;
    @Autowired
    private GreetingService greetingService;
    @Autowired
    DealMapper dealMapper;
//    @GetMapping("/test")
//    public String Index (Model model){
//        return "test";
//    }

    @RequestMapping(path = {"/", "/index"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model,
                        @ModelAttribute User user,
                        @ModelAttribute LoginTicket ticket,
                        HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("ticket")) {
                    String ticketValue = cookie.getValue();
                    String userId = userService.loginProve(ticketValue);
                    if (userId != null) {
                        user.setUserId(userId);
                    }
                }
            }
        }
        //int status = userService.loginProve(ticketValue);


        model.addAttribute("user", user);
        model.addAttribute("ticket", ticket);

        return "index";
    }

    @GetMapping("/regAndLogin")
    public String regAndLogin(Model model) {

        return "regAndLogin";
    }

    @RequestMapping(path = {"/user/{userId}"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String userIndex(Model model, @PathVariable("userId") int userId,
                            @RequestParam(value = "pop", defaultValue = "0") int pop) {
        model.addAttribute(userId);
        model.addAttribute("pop", pop);
        return "index";
    }


    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("deal", new Deal());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Deal deal, BindingResult bindingResult) {
        //bill.setBusId(Deal.getBusId());

        deal.setMsgType("CES.001.001");
        deal.setTrdDir("TDD01");
        deal.setStatus((byte) 0);
        deal.setCdType("AC01");
        greetingService.insertDeal(deal);
        return "result";
    }

    @GetMapping("/selectById")
    public String selectById(Model model) {
        model.addAttribute("deal", new Deal());
        return "selectById";
    }

    @PostMapping("/selectByIdResult")
    public String selectByIdResult(Model model, @ModelAttribute Deal deal) {
        List<Deal> deals = greetingService.listByBusId(deal.getBusId());
        model.addAttribute("deals", deals);
        //System.out.println(Deal.getBusId());
        //Greeting greetings = greetingService.listByBusId(greeting.getBusId());
        return "selectByIdResult";
    }


    @RequestMapping(path = {"/unhandledDeal"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String unhandledDeal(Model model,@ModelAttribute Deal deal) {
        List<Deal> deals = greetingService.selectByStatus((byte) 1);
        model.addAttribute("deals", deals);
        model.addAttribute("deal", deal);
        return "unhandledDeal";
    }
    @PostMapping("/handleDeal")
    public String handleDeal(@RequestParam(value = "choice")String choice, @ModelAttribute Deal deal, BindingResult bindingResult) {
        System.out.println(choice);
        System.out.println(deal.getBusId());
        deal.setMsgType("CES.001.001");
        deal.setTrdDir("TDD01");
        deal.setCdType("AC01");
        deal.setStatus((byte) 0);
        dealMapper.updateStatus(deal.getBusId());
        if (choice.equals("不同意报价，提交修改")){

            greetingService.insertDeal(deal);

        }else if (choice.equals("同意报价，交易成功")){
            greetingService.resultDeal(deal,"0");

        }else{
            greetingService.resultDeal(deal,"1");

        }

        return "result";


    }



}

