package br.com.banco.exception;

public class TransacaoException extends RuntimeException{
    public TransacaoException(String message) {
        super(message);
    }
}
