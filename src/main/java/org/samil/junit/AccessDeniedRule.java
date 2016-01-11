package org.samil.junit;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import java.security.AccessControlException;

/**
 * User: Samil
 * Date: 11/01/2016
 */
public class AccessDeniedRule implements MethodRule {
    @Override
    public Statement apply(Statement statement, FrameworkMethod frameworkMethod, Object o) {
        try {
            statement.evaluate();
            return statement;
        } catch (AccessControlException exception) {
            throw exception;
        } catch (Throwable throwable) {
            return statement;
        }
    }
}
