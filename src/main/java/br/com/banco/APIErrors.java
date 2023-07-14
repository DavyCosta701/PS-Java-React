package br.com.banco;

import lombok.Getter;

import java.util.Collections;
import java.util.List;


public class APIErrors {

    @Getter
    private final List<String> errors;
    public APIErrors(List<String> errors) {
        this.errors = errors;
    }
    public APIErrors(String message) {
        this.errors = Collections.singletonList(message);
    }

}
