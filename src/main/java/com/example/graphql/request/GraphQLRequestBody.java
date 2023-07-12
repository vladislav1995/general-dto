package com.example.graphql.request;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Uladik
 */
public class GraphQLRequestBody {
    private Map<String, Object> variables = new HashMap<String, Object>();
    private String query;
    private String operationName;

    public GraphQLRequestBody() {
    }

    public GraphQLRequestBody(Map<String, Object> variables, String query, String operationName) {
        this.variables = variables;
        this.query = query;
        this.operationName = operationName;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

}
