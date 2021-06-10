package com.pali.eventgo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController  implements org.springframework.boot.web.servlet.error.ErrorController {



    @PostMapping(value = "/error")
    public String postHandleError(HttpServletRequest httpRequest){
        Object status = httpRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(status != null){
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()){
                return "home/error-404";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
                return "home/error-500";
            }else if(statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()){
                return "home/error-405";
            }
        }

        return "home/error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
