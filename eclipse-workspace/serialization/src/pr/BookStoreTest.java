package pr;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookStoreTest {

	@BeforeEach
	void setUp() throws Exception {
		// delete save.data
	}
	
	@Test
	void test() throws IOException, ClassNotFoundException {
		BookStore store1 = new BookStore();
		store1.addSomeBooks();
		store1.saveBooks("helloBooks.bin");
		
		BookStore store2 = new BookStore();
		store2.loadBooks("helloBooks.bin");
		
		// Check whether book is existing or not
		Optional<Book> b = store2.get("Math");
		assertTrue(b.isPresent());
		assertEquals("Anh", b.get().getAuthor());
	}

}

