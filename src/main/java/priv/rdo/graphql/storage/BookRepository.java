package priv.rdo.graphql.storage;

import org.springframework.stereotype.Component;
import priv.rdo.graphql.storage.model.Book;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BookRepository {

    //TODO change to actual DB
    private static final List<Book> books = List.of(
            new Book(
                    "book-1",
                    "Harry Potter and the Philosopher's Stone",
                    223,
                    "author-1",
                    LocalDate.of(2000, 7, 31)
            ),
            new Book(
                    "book-2",
                    "Moby Dick",
                    635,
                    "author-2",
                    LocalDate.of(1835, 7, 31)
            ),
            new Book(
                    "book-3",
                    "Interview with the vampire",
                    371,
                    "author-3",
                    LocalDate.of(1965, 7, 31)
            ),new Book(
                    "book-4",
                    "Harry Potter and the Chamber of Secrets",
                    251,
                    "author-1",
                    LocalDate.of(2002, 5, 5)
            )
    );

    public Optional<Book> findById(String id) {
        return books.stream()
                .filter(item -> item.id().equals(id))
                .findFirst();
    }

    public Collection<Book> booksByAuthorId(String id) {
        return books.stream()
                .filter(item -> item.authorId().equals(id))
                .collect(Collectors.toList());
    }
}
