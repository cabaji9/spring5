package org.hson.app.controller.view;

import lombok.extern.slf4j.Slf4j;
import org.hson.app.util.PropertyHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Slf4j
@Controller
public class ViewController {

    @Autowired
    private PropertyHolder propertyHolder;

    @GetMapping("/")
    public String home(Model model, Principal principal){

        model.addAttribute("usuario",principal.getName());

        return "home";
    }

    @GetMapping(value = "/hello/{name}")
    public ModelAndView hello(@PathVariable("name") String name) {
        log.info("hello");
        log.debug("hello() is executed d- $name {}", name);
        ModelAndView model = new ModelAndView();
        model.setViewName("hello");
        model.addObject("salute",propertyHolder.getSalute());
        model.addObject("name", name);
        return model;

    }

}
