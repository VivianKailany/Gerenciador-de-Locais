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
        // Configuração do validador a partir da fábrica padrão
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void whenNomeIsBlank_thenValidationFails() {
        // Criação de um objeto Local com nome em branco
        Local local = new Local();
        local.setNome("   "); // String com apenas espaços em branco

        // Validação do objeto
        Set<ConstraintViolation<Local>> violations = validator.validate(local);

        // Verificação das violações de validação
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().equals("O nome do local é obrigatório.")));
    }

    @Test
    public void whenNomeIsNull_thenValidationFails() {
        // Criação de um objeto Local com nome nulo
        Local local = new Local();
        local.setNome(null);

        // Validação do objeto
        Set<ConstraintViolation<Local>> violations = validator.validate(local);

        // Verificação das violações de validação
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().equals("O nome do local é obrigatório.")));
    }

    @Test
    public void whenNomeIsTooLong_thenValidationFails() {
        // Criação de um objeto Local com nome muito longo
        String nome = "a".repeat(101);
        Local local = new Local();
        local.setNome(nome);

        // Validação do objeto
        Set<ConstraintViolation<Local>> violations = validator.validate(local);

        // Verificação das violações de validação
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().equals("O nome deve ter no máximo 100 caracteres.")));
    }

    @Test
    public void whenBairroIsTooLong_thenValidationFails() {
        // Criação de um objeto Local com bairro muito longo
        String bairro = "a".repeat(256);
        Local local = new Local();
        local.setBairro(bairro);

        // Validação do objeto
        Set<ConstraintViolation<Local>> violations = validator.validate(local);

        // Verificação das violações de validação
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().equals("O bairro deve ter no máximo 100 caracteres.")));
    }

    @Test
    public void whenCidadeIsBlank_thenValidationFails() {
        // Criação de um objeto Local com cidade em branco
        Local local = new Local();
        local.setCidade("   ");

        // Validação do objeto
        Set<ConstraintViolation<Local>> violations = validator.validate(local);

        // Verificação das violações de validação
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().equals("O nome da cidade é obrigatório.")));
    }

    @Test
    public void whenEstadoIsBlank_thenValidationFails() {
        // Criação de um objeto Local com estado em branco
        Local local = new Local();
        local.setEstado("   ");

        // Validação do objeto
        Set<ConstraintViolation<Local>> violations = validator.validate(local);

        // Verificação das violações de validação
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().equals("O nome do estado é obrigatório.")));
    }

    @Test
    public void whenAllFieldsAreValid_thenValidationPasses() {
        // Criação de um objeto Local com todos os campos válidos
        Local local = new Local();
        local.setNome("Praça da Fonte");
        local.setBairro("Centro");
        local.setCidade("Pacatuba");
        local.setEstado("CE");

        // Validação do objeto
        Set<ConstraintViolation<Local>> violations = validator.validate(local);

        // Verificação das violações de validação
        assertTrue(violations.isEmpty());
    }
}
