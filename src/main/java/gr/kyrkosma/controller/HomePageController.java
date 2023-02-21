package gr.kyrkosma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {
    @RequestMapping("/")
    public String welcome() throws Exception {
        return "home.html";
    }
}
