package priv.rdo.graphql.storage;

import org.springframework.stereotype.Component;
import priv.rdo.graphql.storage.model.Author;

import java.util.List;
import java.util.Optional;

@Component
public class AuthorRepository {
    private static final List<Author> authors = List.of(
            new Author(
                    "author-1",
                    "Joanne",
                    "Rowling"
            ),
            new Author(
                    "author-2",
                    "Herman",
                    "Melville"
            ),
            new Author(
                    "author-3",
                    "Anne",
                    "Rice"
            )
    );

    public Optional<Author> findById(String id) {
        return authors.stream()
                .filter(item -> item.id().equals(id))
                .findFirst();
    }
}
