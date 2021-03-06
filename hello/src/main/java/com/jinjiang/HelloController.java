package com.jinjiang;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Laoer on 16/8/19.
 */
@Controller
public class HelloController extends BaseController {

    //    @Autowired
//    MsgSource msgSource;

    @Value("${message}")
    String message;

    @ResponseBody
    @RequestMapping(value = "/hello/{name}")
    public String hello(@PathVariable(value = "name") String name) {
        return "Hello " + message + name;
    }

    @ResponseBody
    @RequestMapping(value = "/msg/{msg}")
    public String message(@PathVariable(value = "msg") String msg) {
//        HelloMsg helloMsg = new HelloMsg("Tim", msg);
//        msgSource.sendMessage(helloMsg);
        return "Msg sent";
    }

    @ResponseBody
    @RequestMapping(value = "/pass")
    public String password() {
        boolean result = false;
        try {
            result = BCrypt.checkpw("password", "$2a$10$4adA");
        } catch (Exception e) {
            System.out.println(e);
        }
        String password = BCrypt.hashpw("password", BCrypt.gensalt());
        //System.out.println(password);
        return String.valueOf(result) + " " + password;
    }


    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(Map<String, Object> model, HttpServletRequest request) {
        model.put("name", "Laoer");
        model.put("msg", "你好!");
        //session.setAttribute("name", "Tim");
        //session.setAttribute("time", System.currentTimeMillis());
        this.setSessionAttribute(request, "name", "Tim");
        this.setSessionAttribute(request, "time", System.currentTimeMillis());
        return "welcome";
    }


}