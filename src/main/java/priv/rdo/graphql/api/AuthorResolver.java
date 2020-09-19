package priv.rdo.graphql.api;

import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import priv.rdo.graphql.storage.AuthorRepository;
import priv.rdo.graphql.storage.model.Author;
import priv.rdo.graphql.storage.model.Book;

import java.util.Collection;

@Component
class AuthorResolver implements GraphQLResolver<Author> {
    private final AuthorRepository authorRepository;

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection") //avoiding circular dep in constructor
    @Autowired
    private BookResolver bookResolver;

    AuthorResolver(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author authorById(String id) {
        return authorRepository.findById(id)
                .orElse(null);
    }

    public Collection<Book> books(Author author) {
        return bookResolver.booksByAuthorId(author.id());
    }
}
