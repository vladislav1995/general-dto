package com.example.graphql.util;

import com.google.common.collect.ImmutableMap;
import graphql.ExecutionResult;

import java.util.Map;

/**
 * @author Uladik
 */
public final class ResponseUtils {
    private ResponseUtils() {}

    public static Object patchExecutionResult(ExecutionResult result) {
        if(((Map)result.getData()).containsKey("_service")) {
            Map service = (Map) ((Map) result.getData()).get("_service");
            String sdl = (String) service.get("sdl");
            if (sdl != null) {
                final String modified = sdl.replace("schema {\n  query: Query\n  mutation: Mutation\n}\n\n", "");
                service.put("sdl", modified);

                return ImmutableMap.of("data", result.getData());
            }
        }
        return result;

    }
}
