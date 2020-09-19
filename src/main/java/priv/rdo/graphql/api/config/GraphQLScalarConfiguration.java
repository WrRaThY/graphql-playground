package priv.rdo.graphql.api.config;

import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.time.Instant;
import java.time.LocalDate;

@Configuration
public class GraphQLScalarConfiguration {

    @Bean
    GraphQLScalarType uriScalar() {
        return GraphQLScalarType.newScalar()
                .name("URI")
                .coercing(new AbstractStringCoercing<>(URI.class, URI::toString, URI::create))
                .build();
    }

    @Bean
    GraphQLScalarType dateTimeScalar() {
        return GraphQLScalarType.newScalar()
                .name("DateTime")
                .coercing(new AbstractStringCoercing<>(Instant.class, Instant::toString, Instant::parse))
                .build();
    }

    @Bean
    GraphQLScalarType localDateScalar() {
        return GraphQLScalarType.newScalar()
                .name("LocalDate")
                .coercing(new AbstractStringCoercing<>(LocalDate.class, LocalDate::toString, LocalDate::parse))
                .build();
    }

    @Bean
    GraphQLScalarType voidScalar() {
        return GraphQLScalarType.newScalar()
                .name("Void")
                .coercing(new AbstractStringCoercing<Void>(
                        Void.class,
                        value -> {
                            throw new IllegalStateException();
                        },
                        string -> {
                            throw new IllegalStateException();
                        }))
                .build();
    }
}