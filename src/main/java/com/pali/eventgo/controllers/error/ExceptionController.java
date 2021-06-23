package com.pali.eventgo.controllers.error;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {

    private final Log logger = LogFactory.getLog(ExceptionController.class);

    @ExceptionHandler(value = Exception.class)
    public String handleError(HttpServletRequest req, Exception exception){
        logger.error("Request" + req.getRequestURL() + " raised " + exception);
        return "error";
    }


}
