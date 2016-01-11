package org.dojo.calculator;

import org.junit.Rule;
import org.junit.Test;
import org.samil.junit.AccessDeniedRule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;


public class StringCalculatorTest  {
    @Rule
    public AccessDeniedRule accessDeniedRule = new AccessDeniedRule();

    private IStringCalculator initSut(){
        try {
            Class<IStringCalculator> calculatorClass = (Class<IStringCalculator>)Thread.currentThread().getContextClassLoader().loadClass("org.dojo.calculator.StringCalculator");
            System.out.println();
            return calculatorClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Test
    public void shouldReturnZeroWhenEmpty() throws Exception {

        // Given
        IStringCalculator sut = initSut();
        // When
        int result = sut.add("");
        // Then
        assertThat(result).isEqualTo(0);
    }
    @Test
    public void shouldReturnOne() throws Exception {

        // Given
        IStringCalculator sut = initSut();
        // When
        int result = sut.add("1");
        // Then
        assertThat(result).isEqualTo(1);
    }
    @Test
    public void shouldReturnThree() throws Exception {

        // Given
        IStringCalculator sut = initSut();
        // When
        int result = sut.add("1,2");
        // Then
        assertThat(result).isEqualTo(3);
    }
    @Test
    public void shouldSumMultipleNumbers() throws Exception {

        // Given
        IStringCalculator sut = initSut();
        // When
        int result = sut.add("1,2,4,1");
        // Then
        assertThat(result).isEqualTo(8);
    }
    @Test
    public void shouldHandleLineBreaks() throws Exception {

        // Given
        IStringCalculator sut = initSut();
        // When
        int result = sut.add("1\n2,3");
        // Then
        assertThat(result).isEqualTo(6);
    }
    @Test
    public void shouldSupportDelimiter() throws Exception {

        // Given
        IStringCalculator sut = initSut();
        // When
        int result = sut.add("//;\\n1;2");
        // Then
        assertThat(result).isEqualTo(3);
    }
    @Test
    public void shouldThrowExceptionWithNegativeNumbers() throws Exception {

        // Given
        IStringCalculator sut = initSut();
        // When
        try {
            int result = sut.add("-1,2,-3");
            fail("Exception expected ");
        } catch (Exception e) {
            // Then
            assertThat(e).hasMessage("Negatives not allowed:[-1, -3]");
        }
    }
}
