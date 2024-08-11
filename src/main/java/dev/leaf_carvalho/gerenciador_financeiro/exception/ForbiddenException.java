package dev.leaf_carvalho.gerenciador_financeiro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {

    /**
     * Construtor padrão que aceita uma mensagem personalizada.
     *
     * @param message a mensagem de erro que será exibida quando a exceção for lançada.
     */
    public ForbiddenException(String message) {
        super(message);
    }

    /**
     * Construtor que aceita uma mensagem personalizada e uma causa.
     *
     * @param message a mensagem de erro que será exibida quando a exceção for lançada.
     * @param cause a causa da exceção, que pode ser outra exceção.
     */
    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }
}