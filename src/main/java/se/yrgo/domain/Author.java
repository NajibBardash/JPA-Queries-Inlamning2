package se.yrgo.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * This class defines an author and its one-directional relationship to the books written buy such author.
 */
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 20)
    private String name;

    @Column(length = 20)
    private String nationality;

    @OneToMany (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "AUTHOR_FK")
    private Set<Book> writtenBooks;

    public Author() {}

    public Author(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
        this.writtenBooks = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void addBook(Book book) {
        writtenBooks.add(book);
    }

    public int getNumberOfBooks() {
        return writtenBooks.size();
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}