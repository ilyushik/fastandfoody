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
    //400 error
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public String handleBadRequestError(Model model) {
        return "errorTemplates/badRequest";
    }

    //401 error
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public String handleUnauthorizedError(Model model) {
        return "errorTemplates/unauthorized";
    }

    //403 error
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public String handleForbiddenError(Model model) {
        return "errorTemplates/forbidden";
    }

    //404 error
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public String handleNotFoundError(Model model) {
        return "errorTemplates/badRequest";
    }

    //500 error
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({NullPointerException.class,
                       HttpServerErrorException.InternalServerError.class,
                       Exception.class,
                       RuntimeException.class
    })
    public String handleInternalServerError(Model model) {
        return "errorTemplates/internalServer";
    }
}
