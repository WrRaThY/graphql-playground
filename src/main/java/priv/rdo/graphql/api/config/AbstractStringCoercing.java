package priv.rdo.graphql.api.config;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;

import java.util.Objects;
import java.util.function.Function;


public class AbstractStringCoercing<T> implements Coercing<T, String> {

    private final Class<T> klass;
    private final Function<T, String> serializer;
    private final Function<String, T> parser;

    public AbstractStringCoercing(Class<T> klass, Function<T, String> serializer, Function<String, T> parser) {
        this.klass = Objects.requireNonNull(klass);
        this.serializer = Objects.requireNonNull(serializer);
        this.parser = Objects.requireNonNull(parser);
    }

    @Override
    public String serialize(Object input)  {
        if(klass.isInstance(input)) {
            return serializer.apply(klass.cast(input));
        }
        throw new CoercingParseValueException(
                String.format("Expected %s but was %s", klass, input.getClass()));
    }

    @Override
    public T parseValue(Object input) {
        if (klass.isInstance(input)) {
            return klass.cast(input);
        } else if (input instanceof String) {
            try {
                return parser.apply((String)input);
            } catch (Exception e) {
                throw new CoercingParseValueException(e);
            }
        } else {
            throw new CoercingParseValueException(
                    "Expected a 'String' but was '" + input.getClass() + "'.");
        }
    }

    @Override
    public T parseLiteral(Object input) {
        if (!(input instanceof StringValue)) {
            throw new CoercingParseLiteralException(
                    "Expected AST type 'StringValue' but was '" + input.getClass() + "'."
            );
        }
        try {
            return parser.apply( ((StringValue)input).getValue());
        } catch (Exception e) {
            throw new CoercingParseLiteralException(e);
        }
    }
}
