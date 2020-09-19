package priv.rdo.graphql.storage;

import org.springframework.stereotype.Component;
import priv.rdo.graphql.storage.model.Author;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class AuthorRepository {
    private static final List<Author> authors = List.of(
            new Author(
                    "author-1",
                    "Joanne",
                    "Rowling",
                    LocalDate.of(1965, 7, 31)
            ),
            new Author(
                    "author-2",
                    "Herman",
                    "Melville",
                    LocalDate.of(1819, 8, 1)
            ),
            new Author(
                    "author-3",
                    "Anne",
                    "Rice",
                    LocalDate.of(1941, 8, 4)
            )
    );

    public Optional<Author> findById(String id) {
        return authors.stream()
                .filter(item -> item.id().equals(id))
                .findFirst();
    }
}
