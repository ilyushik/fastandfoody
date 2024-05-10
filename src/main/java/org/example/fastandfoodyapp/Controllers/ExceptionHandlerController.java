package org.example.fastandfoodyapp.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class ExceptionHandlerController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public String handleBadRequestError(Model model) {
        model.addAttribute("error_code", "400");
        model.addAttribute("error_text", "Opps... We can't handle this");
        model.addAttribute("error_image", "400.png");
        return "errorPage";
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public String handleUnauthorizedError(Model model) {
        model.addAttribute("error_code", "401");
        model.addAttribute("error_text", "Seems like you are unauthorized!");
        model.addAttribute("error_image", "401.png");
        return "errorPage";
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public String handleForbiddenError(Model model) {
        model.addAttribute("error_code", "403");
        model.addAttribute("error_text", "Hey! You don't have permission to do this!");
        model.addAttribute("error_image", "403.png");
        return "errorPage";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public String handleNotFoundError(Model model) {
        model.addAttribute("error_code", "404");
        model.addAttribute("error_text", "Sorry... We can't find this page!");
        model.addAttribute("error_image", "404.png");
        return "errorPage";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({NullPointerException.class,
                       HttpServerErrorException.InternalServerError.class,
                       Exception.class,
                       RuntimeException.class
    })
    public String handleInternalServerError(Model model) {
        model.addAttribute("error_code", "500");
        model.addAttribute("error_text", "Something went wrong!");
        model.addAttribute("error_image", "500.png");
        return "errorPage";
    }
}
