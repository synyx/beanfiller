
package org.synyx.beanfiller;

import org.junit.Assert;
import org.junit.Test;

import org.synyx.beanfiller.creator.EnumCreator;
import org.synyx.beanfiller.creator.SimpleCreator;
import org.synyx.beanfiller.creator.SimpleEnumCreator;
import org.synyx.beanfiller.exceptions.FillingException;
import org.synyx.beanfiller.exceptions.NoEnumConstantsException;
import org.synyx.beanfiller.exceptions.WrongCreatorException;
import org.synyx.beanfiller.testobjects.EnumsObject;
import org.synyx.beanfiller.testobjects.ErrorEnumObject;
import org.synyx.beanfiller.testobjects.TestEnum;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Tests for Enums.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class EnumTest {

    private final BeanFiller beanfiller = new BeanFiller();

    @Test
    public void testEnumIsFilled() throws FillingException {

        EnumsObject o = beanfiller.fillBean(EnumsObject.class);

        Assert.assertNotNull("TestEnum was not filled!", o.getTestEnum());
    }


    @Test(expected = NoEnumConstantsException.class)
    public void testNoEnumConstantsExceptionIsThrownOnEmptyEnum() throws FillingException {

        beanfiller.fillBean(ErrorEnumObject.class);
    }


    @Test
    public void testAddedEnumCreatorIsUsed() throws FillingException {

        SimpleEnumCreator enumCreator = mock(SimpleEnumCreator.class);
        when(enumCreator.createEnum(TestEnum.class)).thenReturn(TestEnum.TEST1);
        beanfiller.addCreator(TestEnum.class, enumCreator);

        beanfiller.fillBean(EnumsObject.class);

        // assert that our mock was called instead of the default EnumCreator
        verify(enumCreator).createEnum(TestEnum.class);
    }


    @Test
    public void testAddedSpecificEnumCreatorIsUsed() throws FillingException {

        EnumCreator enumCreator = mock(EnumCreator.class);
        when(enumCreator.createEnum(TestEnum.class)).thenReturn(TestEnum.TEST1);

        beanfiller.addCreatorForClassAndAttribute(EnumsObject.class, "testEnum", enumCreator);

        beanfiller.fillBean(EnumsObject.class);

        // assert that our mock was called instead of the default EnumCreator
        verify(enumCreator).createEnum(TestEnum.class);
    }


    @Test(expected = WrongCreatorException.class)
    public void testWrongCreatorExceptionIsThrownIfNonEnumCreatorIsUsed() throws FillingException {

        SimpleCreator stringCreator = new org.synyx.beanfiller.creator.StringCreator();

        beanfiller.addCreatorForClassAndAttribute(EnumsObject.class, "testEnum", stringCreator);

        beanfiller.fillBean(EnumsObject.class);
    }
}
