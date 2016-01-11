package org.samil;

import org.dojo.calculator.IStringCalculator;
import org.dojo.calculator.StringCalculatorTest;

/**
 * User: Samil
 * Date: 06/01/2016
 */
public enum Katas {
    STRINGCALCULATOR("org.dojo.calculator.StringCalculator", IStringCalculator.class, StringCalculatorTest.class),
    FIZZBUZZ("", null, null);

    Katas(String className, Class<IStringCalculator> clazz, Class<?> testClass) {
        this.className = className;
        this.clazz = clazz;
        this.testClass = testClass;
    }

    private final String className;
    private final Class clazz;
    private final Class<?> testClass;

    public String getClassName() {
        return className;
    }

    public Class<?> getTestClass() {
        return testClass;
    }
}
