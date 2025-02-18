package com.alfacloud.catalogservice.domain;

import com.alfacloud.catalogservice.config.DataConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJdbcTest
@Import(DataConfig.class)
@ActiveProfiles("integration")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryJdbcTests {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JdbcAggregateTemplate aggregateTemplate;

    @Test
    public void findBookByIsbnWhenExisting() {
        String isbn ="1234561237";
        Book book = Book.of(isbn, "Title", "Author", 15.00, "Polarsophia");
        aggregateTemplate.insert(book);

        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        assertTrue(optionalBook.isPresent());
        assertEquals(isbn, optionalBook.get().isbn());
    }
}
