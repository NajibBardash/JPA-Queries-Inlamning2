package se.yrgo.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 30)
    private String title;

    @Column(length = 20)
    private String genre;

    private int publicationYear;

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "readBooks")
    private Set<Reader> readersOfTheBook;

    public Book() {}

    public Book(String title, String genre, int publicationYear) {
        this.title = title;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.readersOfTheBook = new HashSet<>();
    }

    public void addReaderOfTheBook(Reader reader) {
        this.readersOfTheBook.add(reader);
        reader.getReadBooks().add(this);
    }

    @Override
    public String toString() {
        return "Book{" +
                " title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", publicationYear=" + publicationYear +
                '}';
    }
}
