package priv.rdo.graphql.storage.model;

public record Book(String id,
                   String name,
                   Integer pageCount,
                   String authorId) {
}