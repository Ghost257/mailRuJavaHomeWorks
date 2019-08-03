import lombok.Getter;

public class LibraryFabric {
    @Getter
    private Library library = new Library();

    private Author joanneRowling = createAuthor("Joanne Rowling", "UK");
    private Author aynRand = createAuthor("Ayn Rand", "USA");
    private Author johnTolkin = createAuthor("John R.R. Tolkin", "UK");

    private Book theLordOfRings = createBook(johnTolkin,"The Lord of Rings", "9182643276347");
    private Book hobbit = createBook(johnTolkin,"Hobbit", "12138247878473875");
    private Book atlasShruggled = createBook(aynRand,"Atlas Shruggled", "5437657457847367");
    private Book source = createBook(aynRand,"Source", "113287387283782173");
    private Book harryPotter1 = createBook(joanneRowling, "Harry Potter And Sorcerer's Stone", "42543652537647623");
    private Book harryPotter2 = createBook(joanneRowling, "Harry Potter And Secret Chamber", "42543652537647623");
    public LibraryFabric() {
        fillLibrary();
    }
    public Author createAuthor(String name, String surname) {
        return new Author(name, surname);
    }
    public Book createBook(Author author, String title, String isbn) {
        return new Book(author, title, isbn);
    }
    public void fillLibrary() {
        library.addBook(harryPotter1.getAuthor(), harryPotter1);
        library.addBook(hobbit.getAuthor(), hobbit);
        library.addBook(theLordOfRings.getAuthor(), theLordOfRings);
        library.addBook(source.getAuthor(), source);
        library.addBook(atlasShruggled.getAuthor(), atlasShruggled);
        library.addBook(harryPotter2.getAuthor(), harryPotter2);
     }
}
