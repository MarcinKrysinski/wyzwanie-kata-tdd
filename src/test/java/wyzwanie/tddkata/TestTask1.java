package wyzwanie.tddkata;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class TestTask1 {
    private Calculator calculatorUnderTest;

    @Before
    public void init(){
        calculatorUnderTest = new Calculator();
    }

    @Test
    public void should_return_zero_for_empty_string() {
        //given
        String emptyString = "";

        //when
        Integer result = calculatorUnderTest.add(emptyString);

        //then
        assertThat(result, equalTo(0));
    }

    @Test
    public void should_return_zero_for_null_input() {
        //given
        String input = null;

        //when
        Integer result = calculatorUnderTest.add(input);

        //then
        assertThat(result, equalTo(0));
    }

    @Test
    public void should_return_provided_numbers(){
        //given
        String providedNumber = "3";

        //when
        Integer result = calculatorUnderTest.add(providedNumber);

        //then
        assertThat(result, equalTo(3));

    }

    @Test
    public void should_return_sum_of_two_numbers_divided_by_comma(){
        //given
        String providedTwoNumbersDividedByComma = "2,3";

        //when
        Integer result = calculatorUnderTest.add(providedTwoNumbersDividedByComma);

        //then
        assertThat(result, equalTo(5));

    }

    @Test
    public void should_ignore_non_numbers_and_sum_rest() {
        //given
        String input = "a,2";

        //when
        Integer result = calculatorUnderTest.add(input);

        //then
        assertThat(result, equalTo(2));
    }

    @Test(expected = Exception.class)
    public void should_return_error_when_no_comma_as_delimeter() {
        //given
        String input = "1;2";

        //when
        Integer result = calculatorUnderTest.add(input);

    }

    @Test
    public void should_return_three_for_comma_at_end() {
        //given
        String input = "3,";

        //when
        Integer result = calculatorUnderTest.add(input);

        //then
        assertThat(result, equalTo(3));
    }
}
