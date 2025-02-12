package com.alfacloud.catalogservice.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class BookValidationTests {
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsAreCorrectThenValidationSuccess() {
        Book book = Book.of("1234567890", "Title", "Author", 9.90);
        Set<ConstraintViolation<Book>> constraintViolations = validator.validate(book);
        assertTrue(constraintViolations.isEmpty());
    }

    @Test
    void whenIsbnDefinedButIncorrectThenValidationFails() {
        Book book = Book.of("a1234567890", "Title", "Author", 9.90);
        Set<ConstraintViolation<Book>> constraintViolations = validator.validate(book);
        assertEquals(1, constraintViolations.size());
        assertThat(constraintViolations.iterator().next().getMessage()).isEqualTo("The ISBN format must be valid.");
    }
}