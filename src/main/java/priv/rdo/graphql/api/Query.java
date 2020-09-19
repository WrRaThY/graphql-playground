package priv.rdo.graphql.api;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import priv.rdo.graphql.storage.model.Author;
import priv.rdo.graphql.storage.model.Book;

@Component
class Query implements GraphQLQueryResolver {
    private final BookResolver bookResolver;
    private final AuthorResolver authorResolver;

    Query(BookResolver bookResolver, AuthorResolver authorResolver) {
        this.bookResolver = bookResolver;
        this.authorResolver = authorResolver;
    }

    Book bookById(String id) {
        return bookResolver.bookById(id);
    }

    Author authorById(String id) {
        return authorResolver.authorById(id);
    }
}
