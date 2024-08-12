package br.com.viviankailany.locais.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

/**
 * Manipulador global de exceções para a aplicação.
 * Esta classe captura exceções lançadas em qualquer parte da aplicação e fornece respostas apropriadas.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Manipula exceções de validação de argumentos, retornando erros detalhados.
     *
     * @param ex a exceção de validação
     * @return uma resposta com status HTTP 400 (BAD_REQUEST) contendo uma mensagem de erro e os campos inválidos
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((org.springframework.validation.FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Por favor, verifique os campos obrigatórios e tente novamente.");
        response.put("errors", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Manipula exceções genéricas, retornando uma mensagem de erro inesperado.
     *
     * @param ex a exceção genérica
     * @return uma resposta com status HTTP 500 (INTERNAL_SERVER_ERROR) contendo uma mensagem de erro inesperado
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ocorreu um erro inesperado: " + ex.getMessage());
    }

    /**
     * Manipula exceções específicas para locais não encontrados.
     *
     * @param ex a exceção de local não encontrado
     * @return uma resposta com status HTTP 404 (NOT_FOUND) contendo a mensagem da exceção
     */
    @ExceptionHandler(LocalNotFoundException.class)
    public ResponseEntity<String> handleLocalNotFoundException(LocalNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
