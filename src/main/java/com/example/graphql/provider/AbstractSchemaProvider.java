package com.example.graphql.provider;

import com.apollographql.federation.graphqljava.Federation;
import com.apollographql.federation.graphqljava._Entity;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.TypeResolver;
import graphql.schema.idl.RuntimeWiring;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Collections.emptyMap;

/**
 * @author Uladik
 */
public abstract class AbstractSchemaProvider {

    private GraphQL graphQL;
    private final String schemaFileName;
    private final Map<String, Function<Map<String, Object>, ?>> fetchers;
    private final Map<String, Class<?>> types;

    public AbstractSchemaProvider(String schemaFileName) {
        this(schemaFileName, emptyMap(), emptyMap());
    }

    public AbstractSchemaProvider(String schemaFileName, Map<String, Function<Map<String, Object>, ?>> fetchers, Map<String, Class<?>> types) {
        this.schemaFileName = schemaFileName;
        this.fetchers = fetchers;
        this.types = types;
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
    protected abstract RuntimeWiring buildWiring();

    protected DataFetcher<List<Object>> dataFetcher() {
        return env -> env.<List<Map<String, Object>>>getArgument(_Entity.argumentName).stream()
                .map(this::processValues)
                .collect(Collectors.toList());
    }

    protected TypeResolver typeResolver() {
        return env -> {
            for (Map.Entry<String, Class<?>> type : types.entrySet()) {
                if (type.getValue().isAssignableFrom(env.getObject().getClass())) {
                    return env.getSchema().getObjectType(type.getKey());
                }
            }
            return null;
        };
    }

    @PostConstruct
    private void initGraphQLScheme() throws IOException {
        Resource sdl = new ClassPathResource(schemaFileName);
        GraphQLSchema schema = Federation.transform(sdl.getFile(), buildWiring())
                .fetchEntities(dataFetcher())
                .resolveEntityType(typeResolver())
                .build();
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private Object processValues(Map<String, Object> values) {
        String typename = (String) values.get("__typename");
        Function<Map<String, Object>, ?> fetcher = fetchers == null ? null : fetchers.get(typename);
        return fetcher == null ? null : fetcher.apply(values);
    }
}
