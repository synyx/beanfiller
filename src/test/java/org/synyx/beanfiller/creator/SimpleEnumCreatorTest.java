package org.synyx.beanfiller.creator;


import org.junit.jupiter.api.Test;
import org.synyx.beanfiller.exceptions.FillingException;
import org.synyx.beanfiller.exceptions.NoEnumConstantsException;
import org.synyx.beanfiller.testobjects.EmptyEnum;
import org.synyx.beanfiller.testobjects.TestEnum;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Tests for the SimpleEnumCreator.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class SimpleEnumCreatorTest {

    @Test
    public void testEnumCreatorSelectsEnum() throws FillingException {

        EnumCreator enumCreator = new SimpleEnumCreator();
        TestEnum testEnum = (TestEnum) enumCreator.createEnum(TestEnum.class);

        assertThat(testEnum, notNullValue());
    }


    @Test
    public void testEnumCreatorThrowsExceptionIfNoEnumConstants() {

        EnumCreator enumCreator = new SimpleEnumCreator();
        assertThrows(NoEnumConstantsException.class, () -> enumCreator.createEnum(EmptyEnum.class));
    }
}
