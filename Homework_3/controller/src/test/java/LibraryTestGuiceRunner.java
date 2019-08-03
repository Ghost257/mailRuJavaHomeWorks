import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import lombok.Getter;
import net.lamberto.junit.GuiceJUnitRunner;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import javax.inject.Inject;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(GuiceJUnitRunner.class)
public class LibraryTestGuiceRunner {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    public static class LibraryTestModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(LibraryFabric.class).to(LibraryFabricTest.class);
        }
    }

    private final static class LibraryFabricTest implements LibraryFabric {
        public Collection<Book> books() {
            Collection<Book> books = new ArrayList<>();
            books.add(Mockito.mock(Book.class));
            books.add(Mockito.mock(Book.class));
            books.add(Mockito.mock(Book.class));
            return books;
        }

        public Library createLibrary(int length) throws LibraryException {

            return new Library(length, new ArrayList<Book>(books()));
        }
    }

    //1st test
    @Inject
    private LibraryFabric libraryFabric;

    @Test
    @GuiceJUnitRunner.GuiceModules(LibraryTestModule.class)
    public void throwExceptionWhenLibrarySizeTooLow() {

        Exception ex = assertThrows(LibraryException.class, () -> {
            libraryFabric.createLibrary(1);
        });
        assertEquals(ex.getMessage(), Application.LOW_LENGTH);
    }

    //3rd test

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @GuiceJUnitRunner.GuiceModules(LibraryTestModule.class)
    public void booksInTheSameOrderAsInFabric() throws LibraryException {
        Author author = new Author("TestAuthor");
        Book book = new Book(author, "TestBook");
        Library library = libraryFabric.createLibrary(5);
        library.addBook(book);
        int cellId = 3;
        System.setOut(new PrintStream(outContent));
        library.getBook(3);
        assertEquals("Cell #" + cellId + ": " + new Gson().toJson(book), outContent.toString().trim());
    }

    //4th test
    @Test
    @GuiceJUnitRunner.GuiceModules(LibraryTestModule.class)
    public void throwExceptionWhenGetEmptyCell() throws LibraryException {
        int length = 5;
        Library library = libraryFabric.createLibrary(length);
        Exception ex = assertThrows(LibraryException.class, () -> {
            library.getBook(3);
        });
        assertEquals(ex.getMessage(), Application.EMPTY_CELL);
    }

    //7th test
    @Test
    @GuiceJUnitRunner.GuiceModules(LibraryTestModule.class)
    public void throwExceptionWhenNoFreeCells() throws LibraryException {
        Author author = new Author("TestAuthor");
        Book book = new Book(author, "TestBook");
        int length = 3;
        Library library = libraryFabric.createLibrary(length);
        Exception ex = assertThrows(LibraryException.class, () -> {
            library.addBook(book);
        });
        assertEquals(ex.getMessage(), Application.NO_FREE_CELL);
    }
}


