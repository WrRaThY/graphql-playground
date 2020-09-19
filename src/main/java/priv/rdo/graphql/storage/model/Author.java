package priv.rdo.graphql.storage.model;

import java.time.LocalDate;

public record Author(
        String id,
        String firstName,
        String lastName,
        LocalDate birthDate) {
}
