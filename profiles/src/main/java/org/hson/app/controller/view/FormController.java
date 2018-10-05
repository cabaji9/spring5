package org.hson.app.controller.view;

import lombok.extern.slf4j.Slf4j;
import org.hson.app.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by Hyun Woo Son on 6/13/18
 **/
@Slf4j
@Controller
public class FormController {

    @RequestMapping(value = "/form")
    public String redirectToForm(Model model, Principal principal) {

        log.info(principal.getName());

        model.addAttribute("user",new User());
        return "form";

    }


    @RequestMapping(value = "/login")
    public String redirectToLogin(Model model) {
        return "login";

    }


    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String processRegister(@Valid  User user, Errors errors) {
        if(errors.hasErrors()){
            return "form";
        }

        log.info("processRegister | user {}",user);
        return "redirect:/hello/" + user.getFirstName();
    }



}
