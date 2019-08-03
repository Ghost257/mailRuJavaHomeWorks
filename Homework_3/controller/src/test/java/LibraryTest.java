import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LibraryTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private ArrayList<Book> books = new ArrayList<Book>();
    private String expected = "";

    public static class LibraryTestModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(LibraryFabric.class).to(FileLibraryFabric.class);
        }
    }
    Injector injector = Guice.createInjector(new LibraryTestModule());
    @Inject
    private LibraryFabric fileLibraryFabric = injector.getInstance(LibraryFabric.class);

    public LibraryFabric libraryFabric = Mockito.spy(fileLibraryFabric);

    @Before
    public void setupBeforeTests() {
        int booksLength = 5;
        for (int i = 0; i < booksLength; i++) {
            books.add(new Book(new Author("TestAuthor" + i), "TestBook"+i));
        }
        doReturn(books).when(libraryFabric).books();

    }
    //2nd test
    @Test
    public void checkOrderOfBooks() throws LibraryException {
        int libraryLength = 10;

        Library library = libraryFabric.createLibrary(libraryLength);
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getName() + " " + books.get(i).getAuthor().getName());
           assertEquals(library.getBook(i), books.get(i));
        }
        for (int i = books.size(); i < libraryLength; i++) {
            try {
                assertNull(library.getBook(i));
            }
            catch (LibraryException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //5th test
    @Test
    public void isGottenBookTheSameBook() throws LibraryException {
        int libraryLength = 10;
        Library library = libraryFabric.createLibrary(libraryLength);
        for (int i = 0; i < books.size(); i++) {
            assertEquals(library.getBook(i), books.get(i));
        }
    }
    //6th test
    @Test
    public void bookAddsInTheFirstFreeCell() throws LibraryException {
        Author author = new Author("TestAuthor");
        Book book = new Book(author, "TestBook");
        int length = 8;
        int freeCell = 5;
        Library library = libraryFabric.createLibrary(length);
        library.addBook(book);
        try {

            for (int i = 0; i < length; i++) {
                if (library.getBook(i).equals(book)) {
                    assertEquals(i, freeCell);
                }
            }
            book = new Book(author, "TestBook_2");
            freeCell = 1;
            library.getBook(freeCell);
            for (int i = 0; i < length; i++) {
                if (library.getBook(i).equals(book)) {
                    assertEquals(i, freeCell);
                }
            }
        }
        catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
    }
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
    //8th test
    @Test
    public void methodLibraryGetAllTest() {
        int libraryLength = 20;
        try {
            Library library = libraryFabric.createLibrary(libraryLength);
            System.setOut(new PrintStream(outContent));
            library.getAll();
            Gson gson = new Gson();
            expected = "Library contains:\r\n";
            for (int i = 0; i < books.size(); i++) {
                expected += gson.toJson(books.get(i)) + "\r\n";
            }
            for (int i = books.size(); i < libraryLength; i++) {
                expected += "Empty cell\r\n";
            }
        } catch (LibraryException e) {
            e.printStackTrace();
        }
        assertEquals(expected, outContent.toString());



    }

}
