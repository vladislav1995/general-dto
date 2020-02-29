package com.miniq.example.graphql.provider;

import com.apollographql.federation.graphqljava.Federation;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.TypeResolver;
import graphql.schema.idl.RuntimeWiring;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @author Uladik
 */
public abstract class AbstractSchemaProvider {

    private GraphQL graphQL;
    private final String schemaFileName;

    public AbstractSchemaProvider(String schemaFileName) throws IOException {
        this.schemaFileName = schemaFileName;
    }

    protected abstract RuntimeWiring buildWiring();

    protected abstract DataFetcher dataFetcher();

    protected abstract TypeResolver typeResolver();

    @PostConstruct
    private void initGraphQLScheme() throws IOException {
        Resource sdl = new ClassPathResource(schemaFileName);
        GraphQLSchema schema = Federation.transform(sdl.getFile(), buildWiring())
                .fetchEntities(dataFetcher())
                .resolveEntityType(typeResolver())
                .build();
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}
