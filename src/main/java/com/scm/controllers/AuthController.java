package com.scm.controllers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.scm.service.UserService;
import com.scm.utils.constants.ResponseMessage;
import com.scm.utils.helper.AlertMessage;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import com.scm.enums.MessageAlertType;
import com.scm.payload.request.RegisterRequest;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    // processing registration
    @PostMapping("/do-register")
    public String processingForm(@Valid @ModelAttribute("userForm") RegisterRequest registerRequest,
            BindingResult bindingResult, HttpSession session) {
        LOGGER.info("User registration request:");
        try {
            // fetch data
            // validate data
            if (bindingResult.hasErrors()) {
                LOGGER.error("inside binding result", bindingResult.toString());
                return "register";
            }
            // save to database
            LOGGER.info("User email: {}", registerRequest.getEmail());
            Map<String, Object> map = userService.saveUser(registerRequest);
            LOGGER.info("Description: {}", map.get(ResponseMessage.DESCRIPTION));

            // alert type base on resposne code success -> green, failed -> red, conflict ->
            // yellow, default -> blue
            String code = String.valueOf(map.get(ResponseMessage.CODE));
            MessageAlertType alertType = MessageAlertType.fromCode(code);
            String content = String.valueOf(map.get(ResponseMessage.DESCRIPTION));

            LOGGER.info("testing response code {}, and type {}", code, alertType);
            AlertMessage message = AlertMessage.builder()
                    .content(content)
                    .type(alertType)
                    .build();

            session.setAttribute("message", message);
            // redirect to register

        } catch (Exception e) {
        }
        return "redirect:/register";
    }
}
