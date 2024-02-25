package com.danielbontii.tpms.exceptions;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CustomExceptionResolver extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        log.error(ex.getClass() + ": " + ex.getMessage());
        if (ex instanceof NotFoundException) {
            return GraphqlErrorBuilder.newError()
                    .errorType(ErrorType.NOT_FOUND)
                    .message(ex.getMessage())
                    .path(env.getExecutionStepInfo().getPath())
                    .location(env.getField().getSourceLocation())
                    .build();
        }

        if (ex instanceof AlreadyExistsException) {
            /*
             * TODO: See if I can get CONFLICT instead of BAD_REQUEST later
             * To do this, I need to extend the errorType Enum
             * */
            return GraphqlErrorBuilder.newError()
                    .errorType(ErrorType.BAD_REQUEST)
                    .message(ex.getMessage())
                    .path(env.getExecutionStepInfo().getPath())
                    .location(env.getField().getSourceLocation())
                    .build();
        }

        if (ex instanceof BadCredentialsException) {

            return GraphqlErrorBuilder.newError()
                    .errorType(ErrorType.UNAUTHORIZED)
                    .message(ex.getMessage())
                    .path(env.getExecutionStepInfo().getPath())
                    .location(env.getField().getSourceLocation())
                    .build();
        }

        if (ex instanceof AccessDeniedException) {

            return GraphqlErrorBuilder.newError()
                    .errorType(ErrorType.FORBIDDEN)
                    .message(ex.getMessage())
                    .path(env.getExecutionStepInfo().getPath())
                    .location(env.getField().getSourceLocation())
                    .build();
        }


        if (ex instanceof Exception) {
            return GraphqlErrorBuilder.newError()
                    .errorType(ErrorType.INTERNAL_ERROR)
                    .message("Something went wrong. Please try again later")
                    .path(env.getExecutionStepInfo().getPath())
                    .location(env.getField().getSourceLocation())
                    .build();
        }

        return null;
    }

    @Override
    protected List<GraphQLError> resolveToMultipleErrors(Throwable ex, DataFetchingEnvironment env) {
        log.error(ex.getClass() + ": " + ex.getMessage());
        if (ex instanceof ConstraintViolationException constraintViolationException) {
            return constraintViolationException.getConstraintViolations()
                    .stream()
                    .map(violation -> GraphqlErrorBuilder.newError()
                            .errorType(ErrorType.BAD_REQUEST)
                            .message(violation.getMessage())
                            .path(env.getExecutionStepInfo().getPath())
                            .location(env.getField().getSourceLocation())
                            .build()
                    ).toList();
        }
        return super.resolveToMultipleErrors(ex, env);
    }

}
