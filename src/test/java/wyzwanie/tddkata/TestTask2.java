package wyzwanie.tddkata;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class TestTask2 {

    private Calculator calculatorUnderTest;

    @Before
    public void init(){
        calculatorUnderTest = new Calculator();
    }

    @Test
    public void should_return_sum_when_pattern_given() {
        //given
        String input = "//[:]\\n1:2:3";

        //when
        Integer result = calculatorUnderTest.add(input);

        //then
        assertThat(result, equalTo(6));
    }

    @Test
    public void should_return_sum_when_pattern_given_with_one_negative_digit_in_the_middle(){
        //given
        String input = "//[;]\\n1;-2;3";

        //then
        Assertions.assertThrows(NegativeNotAllowed.class, ()->calculatorUnderTest.add(input));
    }

    @Test
    public void should_return_sum_when_pattern_given_with_one_negative_digit_first(){
        //given
        String input = "//[;]\\n-2;2;3";

        //then
        Assertions.assertThrows(NegativeNotAllowed.class, ()->calculatorUnderTest.add(input));
    }

    @Test
    public void should_return_sum_when_pattern_given_with_one_negative_digit_last(){
        //given
        String input = "//[;]\\n1;2;-3";

        //then
        Assertions.assertThrows(NegativeNotAllowed.class, ()->calculatorUnderTest.add(input));
    }

    @Test
    public void should_return_sum_when_pattern_given_with_all_negative_digits(){
        //given
        String input = "//[;]\\n-1;-2;-3";

        //then
        Assertions.assertThrows(NegativeNotAllowed.class, ()->calculatorUnderTest.add(input));
    }

    @Test
    public void should_return_sum_when_pattern_given_with_one_number(){
        //given
        String input = "//[;]\\n1;100;3";

        //when
        Integer result = calculatorUnderTest.add(input);

        //then
        assertThat(result, equalTo(104));
    }

    @Test
    public void should_return_sum_when_pattern_given_with_one_negative_number(){
        //given
        String input = "//[;]\\n1;-100;3";

        //then
        Assertions.assertThrows(NegativeNotAllowed.class, ()->calculatorUnderTest.add(input));
    }

    @Test
    public void should_return_sum_when_pattern_given_with_all_numbers(){
        //given
        String input = "//[;]\\n12;100;3000";

        //when
        Integer result = calculatorUnderTest.add(input);

        //then
        assertThat(result, equalTo(112));
    }

    @Test
    public void should_return_sum_when_pattern_given_with_all_negative_numbers(){
        //given
        String input = "//[;]\\n-12;-100;-3000";

        //then
        Assertions.assertThrows(NegativeNotAllowed.class, ()->calculatorUnderTest.add(input));
    }


    @Test
    public void should_return_zero_when_pattern_given_all_letters(){
        //given
        String input = "//[;]\\nd;a;c;b";

        //when
        Integer result = calculatorUnderTest.add(input);

        //then
        assertThat(result, equalTo(0));
    }

    @Test
    public void should_return_five_when_pattern_given_only_five_first_only_letter_last(){
        //given
        String input = "//[;]\\n5;d";

        //when
        Integer result = calculatorUnderTest.add(input);

        //then
        assertThat(result, equalTo(5));
    }

    @Test(expected = Exception.class)
    public void should_return_error_when_pattern_given_wrong_delimeter(){
        //given
        String input = "//[;]\\n5,d,10";

        //when
        Integer result = calculatorUnderTest.add(input);

    }

    @Test
    public void should_return_five_when_pattern_given_only_one_delimeter_match(){
        //given
        String input = "//[;]\\n5;10,10";

        //when
        Integer result = calculatorUnderTest.add(input);

        //then
        assertThat(result, equalTo(5));
    }

    @Test
    public void should_return_error_when_wrong_pattern_given(){
        //given
        String input = "//;]\\n5,d,10";

        //when
        Assertions.assertThrows(Exception.class, ()->calculatorUnderTest.add(input));

    }
}
