package priv.rdo.graphql.api.util;

import graphql.execution.DataFetcherResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class DataFetcherCollector<T, R> implements Collector<DataFetcherResult<T>, List<DataFetcherResult<T>>, DataFetcherResult<R>> {

    private final Collector<? super T, ?, R> dataCollector;

    DataFetcherCollector(Collector<? super T, ?, R> dataCollector) {
        this.dataCollector = dataCollector;
    }

    @Override
    public Supplier<List<DataFetcherResult<T>>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<DataFetcherResult<T>>, DataFetcherResult<T>> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<DataFetcherResult<T>>> combiner() {
        return (left, right) -> {
            left.addAll(right);
            return left;
        };
    }

    @Override
    public Function<List<DataFetcherResult<T>>, DataFetcherResult<R>> finisher() {
        return list -> DataFetcherResult.<R>newResult()
                .data(list.stream()
                        .map(DataFetcherResult::getData)
                        .collect(dataCollector)
                )
                .errors(list.stream()
                        .flatMap(result -> result.getErrors().stream())
                        .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}
