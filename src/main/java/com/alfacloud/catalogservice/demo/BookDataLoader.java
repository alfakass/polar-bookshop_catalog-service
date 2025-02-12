package com.alfacloud.catalogservice.demo;

import com.alfacloud.catalogservice.domain.Book;
import com.alfacloud.catalogservice.domain.BookRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnProperty("polar.enable-test-data")
public class BookDataLoader {
    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {this.bookRepository = bookRepository;}

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        bookRepository.deleteAll();
        Book book1 = Book.of("1234567891", "Northern Lights", "Lyra Silverstar", 9.90);
        Book book2 = Book.of("1234567892", "Polar Journey", "Iorek Polarson", 12.90);

        bookRepository.saveAll(List.of(book1, book2));
    }
}
