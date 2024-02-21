
package org.synyx.beanfiller;

import org.junit.jupiter.api.Test;
import org.synyx.beanfiller.creator.EnumCreator;
import org.synyx.beanfiller.creator.SimpleCreator;
import org.synyx.beanfiller.creator.SimpleEnumCreator;
import org.synyx.beanfiller.exceptions.FillingException;
import org.synyx.beanfiller.exceptions.NoEnumConstantsException;
import org.synyx.beanfiller.exceptions.WrongCreatorException;
import org.synyx.beanfiller.testobjects.EnumsObject;
import org.synyx.beanfiller.testobjects.ErrorEnumObject;
import org.synyx.beanfiller.testobjects.TestEnum;
import org.synyx.beanfiller.util.RandomGenerator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


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
        assertThat(o.getTestEnum(), notNullValue());
    }


    @Test
    public void testNoEnumConstantsExceptionIsThrownOnEmptyEnum() {

        assertThrows(NoEnumConstantsException.class, () -> beanfiller.fillBean(ErrorEnumObject.class));
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


    @Test
    public void testWrongCreatorExceptionIsThrownIfNonEnumCreatorIsUsed() {

        SimpleCreator<String> stringCreator = new org.synyx.beanfiller.creator.StringCreator(new RandomGenerator());
        beanfiller.addCreatorForClassAndAttribute(EnumsObject.class, "testEnum", stringCreator);

        assertThrows(WrongCreatorException.class, () -> beanfiller.fillBean(EnumsObject.class));
    }
}
