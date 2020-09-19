package priv.rdo.graphql.api;

import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import priv.rdo.graphql.storage.BookRepository;
import priv.rdo.graphql.storage.model.Author;
import priv.rdo.graphql.storage.model.Book;

import java.util.Collection;

@Component
class BookResolver implements GraphQLResolver<Book> {
    private final BookRepository bookRepository;

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection") //avoiding circular dep in constructor
    @Autowired
    private AuthorResolver authorResolver;

    BookResolver(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    Book bookById(String id) {
        return bookRepository.findById(id)
                .orElse(null);
    }

    Collection<Book> booksByAuthorId(String id) {
        return bookRepository.booksByAuthorId(id);
    }

    Author getAuthor(Book book) {
        return authorResolver.authorById(book.authorId());
    }
}
