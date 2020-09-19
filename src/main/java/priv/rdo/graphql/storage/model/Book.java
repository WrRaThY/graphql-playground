package priv.rdo.graphql.storage.model;

import java.time.LocalDate;

public record Book(String id,
                   String name,
                   Integer pageCount,
                   String authorId,
                   LocalDate releaseDate) {
}