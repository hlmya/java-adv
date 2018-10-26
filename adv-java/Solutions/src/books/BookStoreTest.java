package books;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookStoreTest {
	@BeforeEach
	void setUp() throws Exception {
		// delete save.data
	}

	@Test
	void testLocal() throws Exception {
		BookStore store = new BookStore();
		store.addSomeBooks();
		store.save("save.data");

		BookStore store2 = new BookStore();
		store2.load("save.data");
		Optional<Book> book = store2.get("HelloWorld");
		assertTrue(book.isPresent());
		assertEquals(1234, book.get().getYear());
	}

}
