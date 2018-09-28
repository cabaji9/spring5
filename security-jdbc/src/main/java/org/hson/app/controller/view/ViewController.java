package org.hson.app.controller.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Slf4j
@Controller
public class ViewController {

    @GetMapping("/")
    public String home(Model model, Principal principal){

        model.addAttribute("usuario",principal.getName());

        return "home";
    }

    @GetMapping(value = "/hello/{name}")
    public ModelAndView hello(@PathVariable("name") String name) {
        log.debug("hello() is executed d- $name {}", name);
        ModelAndView model = new ModelAndView();
        model.setViewName("hello");
        model.addObject("name", name);
        return model;

    }

}
