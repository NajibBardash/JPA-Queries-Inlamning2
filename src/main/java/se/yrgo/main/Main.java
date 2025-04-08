package se.yrgo.main;

import jakarta.persistence.*;
import se.yrgo.domain.Author;
import se.yrgo.domain.Book;
import se.yrgo.domain.Reader;

import java.util.ArrayList;
import java.util.List;

/**
 * This class sets up some data to the database with entities and their relationships. Author-Book-Reader
 */
public class Main {

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("databaseConfig");

    /**
     * Run the setUpData() with create in persistence.xml first.
     * Then comment it out and change to update and go one question-answer at a time.
     * Each question has its own header.
     */
    public static void main(String[] args) {
		setUpData();
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

//  Uppgift 2: Hämta alla böcker av en specifik författare (JPQL)
//        String authorName = "Leo Tolstoy";
//        List<Book> booksWrittenByTolstoy = em.createQuery("select book from Author a " +
//                        "join a.writtenBooks as book " +
//                        "where a.name = :authorName", Book.class)
//                .setParameter("authorName", authorName)
//                .getResultList();

          // We only expect to get "War and Peace" & "Anna Karenina":
//        for (Book book : booksWrittenByTolstoy) {
//            System.out.println(book);
//        }

//  Uppgift 3: Hämta alla läsare(readers) som har läst en viss bok (member of)
//        Book nilsHolgersson = em.find(Book.class, 4L);
//        List<Reader> readersOfTheBook = em.createQuery("select r from Reader r " +
//                        "where :book member of r.readBooks", Reader.class)
//                .setParameter("book", nilsHolgersson)
//                .getResultList();

          // We only expect "Bosse Bredsladd" & "Eva Larsson" as readers here:
//        for(Reader reader : readersOfTheBook) {
//            System.out.println(reader);}

//  Uppgift 4: Hämta författare vars böcker har lästs av minst en läsare (join)
//        List<Author> authors = em.createQuery("select distinct author from Author author " +
//                "join author.writtenBooks as book " +
//                "join book.readersOfTheBook as reader ", Author.class)
//                .getResultList();

        // Here the "Unread Author" should be missing:
//        for (Author author : authors) {
//            System.out.println(author);
//        }

//  Uppgift 5: Räkna antalet böcker per författare (Aggregation Query)
//        List<Object[]> results = em.createQuery("select distinct author.name, count(book) " +
//                        "from Author author " +
//                        "join author.writtenBooks as book " +
//                        "group by author.name", Object[].class)
//                .getResultList();
//
//        for (Object[] item : results) {
//            System.out.println(item[0] + " ----------- " + item[1] +  " written book(s)");
//        }

//  Uppgift 6: Named Query - Hämta böcker efter genre
//        List<Book> results = em.createNamedQuery("searchByGenre", Book.class)
//                .setParameter("genre", "Classics")
//                .getResultList();
        // Here only four titles are part of the "Classics"-genre:
//        for(Book book: results) {
//            System.out.println(book);
//        }

        tx.commit();
        em.close();
    }

    private static void setUpData() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // Four authors created
        Author leo = new Author("Leo Tolstoy", "Russia");
        Author selma = new Author("Selma Lagerlöf", "Sweden");
        Author charles = new Author("Charles Bukowski", "USA");
        Author unreadAuthor = new Author("Unread Author", "Germany");

        // Six books created, 2 from Tolstoy, 2 from Lagerlöf, 1 from Bukowski and 1 from Unread atuhor (no readers)
        Book annaK = new Book("Anna Karenina", "Classics", 1878);
        Book war = new Book("War and Peace", "Classics", 1867);
        Book nilsH = new Book("Nils Holgersson", "Classics", 1906);
        Book kejsaren = new Book("Kejsaren av Portugallien", "Classics", 1914);
        Book fact = new Book("Factotum", "Poetry", 1975);
        Book notRead = new Book("Not Read", "Economics", 2024);

        // Add the books to the authors by referencing the addBook-method
        leo.addBook(annaK);
        leo.addBook(war);
        selma.addBook(nilsH);
        selma.addBook(kejsaren);
        charles.addBook(fact);
        unreadAuthor.addBook(notRead);

        // Adding 3 readers
        Reader najib = new Reader("Najib Bardash", "najib@gmail.com");
        Reader eva = new Reader("Eva Larsson", "eva@gmail.com");
        Reader bosse = new Reader("Bosse Bredsladd", "bosse@gmail.com");

        // Adding the readers to the book by referencing the addReaderToTheBook-method in the Book-class that ensures a
        //bidirectional relationship that adds the reader in the Book-class and book in the Reader-class simultaneously.
        annaK.addReaderOfTheBook(najib);
        war.addReaderOfTheBook(najib);
        fact.addReaderOfTheBook(najib);

        nilsH.addReaderOfTheBook(eva);
        kejsaren.addReaderOfTheBook(eva);
        annaK.addReaderOfTheBook(eva);

        war.addReaderOfTheBook(bosse);
        fact.addReaderOfTheBook(bosse);
        nilsH.addReaderOfTheBook(bosse);

        // Saving the entities to the database. Cascading is on (PERSIST) for the author and book, so everyone will be
        // added automatically by persisting the author.
        em.persist(leo);
        em.persist(selma);
        em.persist(charles);
        em.persist(unreadAuthor);

        tx.commit();
        em.close();
    }
}