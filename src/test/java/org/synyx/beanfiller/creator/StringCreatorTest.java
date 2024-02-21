package org.synyx.beanfiller.creator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.synyx.beanfiller.criteria.StringCriteria;
import org.synyx.beanfiller.util.RandomGenerator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Tests for the StringCreator class.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class StringCreatorTest {

    private RandomGenerator randomGeneratorMock;

    @BeforeEach
    public void setUp() {

        randomGeneratorMock = mock(RandomGenerator.class);
    }


    @Test
    public void testStringCreatorUsesCorrectMinimumLength() {

        int min = 2;
        int max = 5;

        // ensure that the RandomGenerator returns the min value as we only want to test what the StringCreator does with this value.
        when(randomGeneratorMock.getRandomIntBetween(min, max)).thenReturn(min);

        StringCreator stringCreator = new StringCreator(randomGeneratorMock, new StringCriteria(min, max, "A"));
        String string = stringCreator.create();

        assertThat(string.length(), is(min));
    }


    @Test
    public void testStringCreatorUsesCorrectMaximumLength() {

        int min = 2;
        int max = 5;

        // ensure that the RandomGenerator returns the max value as we only want to test what the StringCreator does with this value.
        when(randomGeneratorMock.getRandomIntBetween(min, max)).thenReturn(max);

        StringCreator stringCreator = new StringCreator(randomGeneratorMock, new StringCriteria(min, max, "A"));
        String string = stringCreator.create();

        assertThat(string.length(), is(max));
    }


    @Test
    public void testStringCreatorUsesFirstLetterOfCharset() {

        int min = 10;
        int max = 10;
        String charset = "ABCDEFG";

        // always return 0, to ensure the first possible character is used.
        when(randomGeneratorMock.getRandomInt(charset.length())).thenReturn(0);

        StringCreator stringCreator = new StringCreator(randomGeneratorMock, new StringCriteria(min, max, charset));
        String string = stringCreator.create();

        for (int i = 0; i < string.length(); i++) {
            assertThat(string.charAt(i), is("A"));
        }
    }


    @Test
    public void testStringCreatorUsesLastLetterOfCharset() {

        int min = 10;
        int max = 10;
        String charset = "ABCDEFG";

        // always return the length of the charset, to ensure the last possible character is used.
        when(randomGeneratorMock.getRandomInt(charset.length())).thenReturn(charset.length());

        StringCreator stringCreator = new StringCreator(randomGeneratorMock, new StringCriteria(min, max, charset));
        String string = stringCreator.create();

        for (int i = 0; i < string.length(); i++) {
            assertThat(string.charAt(i), is("G"));
        }
    }
}
