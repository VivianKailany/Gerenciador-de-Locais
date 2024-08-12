package br.com.viviankailany.locais;

import br.com.viviankailany.locais.model.Local;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocalTest {

    private Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void whenNomeIsBlank_thenValidationFails() {
        Local local = new Local();
        local.setNome("   "); // String com apenas espaços em branco

        Set<ConstraintViolation<Local>> violations = validator.validate(local);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().equals("O nome do local é obrigatório.")));
    }

    @Test
    public void whenNomeIsNull_thenValidationFails() {
        Local local = new Local();
        local.setNome(null); // Nome é nulo

        Set<ConstraintViolation<Local>> violations = validator.validate(local);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().equals("O nome do local é obrigatório.")));
    }

    @Test
    public void whenNomeIsTooLong_thenValidationFails() throws Exception {
        // Cria um objeto Local com descrição muito longa
        String nome = "a".repeat(101);
        Local local = new Local();
        local.setNome(nome);
        Set<ConstraintViolation<Local>> violations = validator.validate(local);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().equals("A descrição do local deve ter no máximo 100 caracteres.")));
    }

    @Test
    public void whenDescricaoIsTooLong_thenValidationFails() throws Exception {
        // Cria um objeto Local com descrição muito longa
        String descricao = "a".repeat(256);
        Local local = new Local();
        local.setBairro(descricao);
        Set<ConstraintViolation<Local>> violations = validator.validate(local);

            assertFalse(violations.isEmpty());
            assertTrue(violations.stream()
                    .anyMatch(v -> v.getMessage().equals("A descrição do local deve ter no máximo 255 caracteres.")));
    }


    @Test
    public void whenNomeIsValid_thenValidationPasses() {
        Local local = new Local();
        local.setNome("Local Válido"); // Nome válido

        Set<ConstraintViolation<Local>> violations = validator.validate(local);

        assertTrue(violations.isEmpty());
    }
}
