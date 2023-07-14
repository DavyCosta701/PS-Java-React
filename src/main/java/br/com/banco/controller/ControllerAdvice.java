package br.com.banco.controller;

import br.com.banco.APIErrors;

import br.com.banco.exception.DateException;
import br.com.banco.exception.ExceptionUtil;
import br.com.banco.exception.TransacaoException;
import org.hibernate.TransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TransacaoException.class)
    @ResponseBody
    public APIErrors handleTransactionException(TransacaoException exception){
        return ExceptionUtil.lidarException(exception);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DateException.class)
    public APIErrors handleDateException(DateException exception){
        return ExceptionUtil.lidarException(exception);
    }

}
