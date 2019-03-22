package org.synyx.beanfiller.creator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.synyx.beanfiller.criteria.StringCriteria;
import org.synyx.beanfiller.util.RandomGenerator;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Tests for the StringCreator class.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class StringCreatorTest {

    private RandomGenerator randomGeneratorMock;

    @Before
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

        Assert.assertEquals("StringCreator has not used the given minimum length!", min, string.length());
    }


    @Test
    public void testStringCreatorUsesCorrectMaximumLength() {

        int min = 2;
        int max = 5;

        // ensure that the RandomGenerator returns the max value as we only want to test what the StringCreator does with this value.
        when(randomGeneratorMock.getRandomIntBetween(min, max)).thenReturn(max);

        StringCreator stringCreator = new StringCreator(randomGeneratorMock, new StringCriteria(min, max, "A"));
        String string = stringCreator.create();

        Assert.assertEquals("StringCreator has not used the given maximum length!", max, string.length());
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
            Assert.assertEquals("StringCreator did not only use the first letter of the charset.", 'A',
                string.charAt(i));
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
            Assert.assertEquals("StringCreator did not only use the last letter of the charset.", 'G',
                string.charAt(i));
        }
    }
}
