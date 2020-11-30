package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class LoggingController {

    @GetMapping("")
    public String getAllUsers () {
        return "login";
    }

    @PostMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
}
