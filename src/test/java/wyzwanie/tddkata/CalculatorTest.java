package wyzwanie.tddkata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class CalculatorTest {

    private Calculator calculatorUnderTest;



    @BeforeEach
    public void init(){
        calculatorUnderTest = new Calculator();
    }

    @ParameterizedTest
    @MethodSource
    public void calculator_should_throws_exception(String input) {

        Assertions.assertThrows(Exception.class, ()->calculatorUnderTest.add(input));
    }

    private static Stream<Arguments> calculator_should_throws_exception() {
        return Stream.of(
                Arguments.of("//[;\\n5;d;10"),
                Arguments.of("//[;]\\n5,d,10"),
                Arguments.of("1;2")

        );
    }



    @ParameterizedTest
    @MethodSource
    public void calculator_should_add_numbers(String input, Integer exceptedResult) {

        Integer result = calculatorUnderTest.add(input);

        assertThat(result, equalTo(exceptedResult));
    }

    private static Stream<Arguments> calculator_should_add_numbers() {
        return Stream.of(
                Arguments.of("", 0),
                Arguments.of(null, 0),
                Arguments.of("3", 3),
                Arguments.of("2,3", 5),
                Arguments.of("a,2", 2),
                Arguments.of("3,", 3),
                Arguments.of("//[:]\\n1:2:3", 6),
                Arguments.of("//[;]\\n12;100;3000", 112),
                Arguments.of("//[;]\\nd;a;c;b", 0),
                Arguments.of("//[;]\\n5;d", 5),
                Arguments.of("//[;]\\n5;10,10", 5),
                Arguments.of("//[aaa]\\n5aaa10aaa10", 25)
        );
    }


    @ParameterizedTest
    @MethodSource
    public void calculator_should_throws_exception_negative_not_allowed(String input) {

       Assertions.assertThrows(NegativeNotAllowed.class, ()->calculatorUnderTest.add(input));
    }

    private static Stream<Arguments> calculator_should_throws_exception_negative_not_allowed() {
        return Stream.of(
                Arguments.of("//[;]\\n-2;2;3"),
                Arguments.of("//[;]\\n1;-2;3"),
                Arguments.of("//[;]\\n-2;2;3"),
                Arguments.of("//[;]\\n1;2;-3"),
                Arguments.of("//[;]\\n-1;-2;-3")
        );
    }
}