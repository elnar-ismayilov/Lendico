package com.lendico.finance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WrongInputParameter extends Exception
{


    private static final long serialVersionUID = 7639266411435590641L;

    public WrongInputParameter(String message)
    {
        super(message);
    }

}
