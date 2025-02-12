package com.alfacloud.catalogservice;

import com.alfacloud.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class CatalogServiceApplicationTests {
	@Autowired
	private WebTestClient webTestClient;

	Book expected = Book.of("1234567890", "Title", "Author", 9.90);

	@Test
	void contextLoads() {
	}

	@Test
	void whenPostRequestThenBookCreated() {
		webTestClient
				.post()
				.uri("/books")
				.bodyValue(expected)
				.exchange()
				.expectStatus()
				.isCreated()
				.expectBody(Book.class)
				.value(book -> {
					assertEquals(book.author(), expected.author());
					assertEquals(book.title(), expected.title());
					assertEquals(book.price(), expected.price());
					assertEquals(book.isbn(), expected.isbn());
				});
	}
}
