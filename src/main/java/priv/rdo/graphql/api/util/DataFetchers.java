package priv.rdo.graphql.api.util;

import graphql.GraphqlErrorException;
import graphql.execution.DataFetcherResult;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;
import java.util.stream.Collector;

public class DataFetchers {

    private DataFetchers() {}

    /** @return a result with no errors with the given data */
    public static <T> DataFetcherResult<T> result(T data) {
        return DataFetcherResult.<T>newResult().data(data).build();
    }

    /**
     * Collects multiple DataFetcherResult instances into a single result. Errors are added to the error
     * list, and data is combined into a single result using the provided downstream collector.
     *
     * @param <T> the type of data of the input results
     * @param <R> the data type of the resulting singular result
     */
    public static <T, R> Collector<DataFetcherResult<T>, List<DataFetcherResult<T>>, DataFetcherResult<R>> toResult(Collector<? super T, ?, R> dataCollector) {
        return new DataFetcherCollector<T, R>(dataCollector);
    }

    /** Shortcut for creating a result with a single error and no data */
    public static <T> DataFetcherResult<T> errorResult(DataFetchingEnvironment env, Throwable t) {
        return DataFetcherResult.<T>newResult()
                .error(errorException(env, t))
                .build();
    }

    /** Shortcut for creating a graphql error with a throwable cause and env context */
    public static GraphqlErrorException errorException(DataFetchingEnvironment env, Throwable t) {
        return GraphqlErrorException.newErrorException()
                .cause(t)
                .sourceLocation(env.getField().getSourceLocation())
                .path(env.getExecutionStepInfo().getPath().toList())
                .build();
    }
}

