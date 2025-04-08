package se.yrgo.domain;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;

    @Column(length = 20)
    private String email;

    @ManyToMany
    private Set<Book> readBooks;

    public Reader() {}

    public Reader(String name, String email) {
        this.name = name;
        this.email = email;
        this.readBooks = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Set<Book> getReadBooks() {
        return readBooks;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
