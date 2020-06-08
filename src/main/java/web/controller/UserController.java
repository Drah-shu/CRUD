package web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class UserController {

    @GetMapping("user")
    public String getAllUsers (ModelMap model) {
        return "userPage";
    }
}
