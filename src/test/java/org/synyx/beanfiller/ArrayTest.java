
package org.synyx.beanfiller;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.synyx.beanfiller.creator.ArrayCreator;
import org.synyx.beanfiller.creator.SimpleArrayCreator;
import org.synyx.beanfiller.creator.StringCreator;
import org.synyx.beanfiller.exceptions.FillingException;
import org.synyx.beanfiller.exceptions.WrongCreatorException;
import org.synyx.beanfiller.testobjects.ArraysObject;
import org.synyx.beanfiller.util.RandomGenerator;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


/**
 * Tests for Arrays of different Types.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class ArrayTest {

    private final BeanFiller beanfiller = new BeanFiller();
    private ArraysObject arraysObject;

    @Test
    public void testObjectIsCreated() throws FillingException {

        arraysObject = beanfiller.fillBean(ArraysObject.class);
        assertNotNull(arraysObject);
    }


    @Test
    public void testStringArrayIsFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(ArraysObject.class);
        assertThat(arraysObject.getStringArray(), notNullValue());
    }


    @Test
    public void testStringArrayElementsAreFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(ArraysObject.class);
        assertThat(arraysObject.getStringArray()[0], notNullValue());
    }


    @Test
    public void testIntArrayIsFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(ArraysObject.class);
        assertThat(arraysObject.getIntArray(), notNullValue());
    }


    @Test
    public void testIntArrayElementsAreFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(ArraysObject.class);
        assertThat(arraysObject.getIntArray()[0], is(not(0)));
    }


    @Test
    public void testObjectArrayIsFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(ArraysObject.class);
        assertThat(arraysObject.getObjectArray(), notNullValue());
    }


    @Test
    public void testObjectArrayElementsAreFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(ArraysObject.class);
        assertThat(arraysObject.getObjectArray()[0], notNullValue());
    }


    @Test
    public void testEnumArrayIsFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(ArraysObject.class);
        assertThat(arraysObject.getEnumArray(), notNullValue());
    }


    @Test
    public void testEnumArrayElementsAreFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(ArraysObject.class);
        assertThat(arraysObject.getEnumArray()[0], notNullValue());
    }


    @Test
    public void testListArrayIsFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(ArraysObject.class);
        assertThat(arraysObject.getListArray(), notNullValue());
    }


    @Test
    public void testListArrayElementsAreFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(ArraysObject.class);
        assertThat(arraysObject.getListArray()[0], notNullValue());
    }


    @Test
    public void testAddedArrayCreatorIsUsed() throws FillingException {

        ArrayCreator arrayCreator = mock(ArrayCreator.class);
        when(arrayCreator.createArray(Mockito.anyList(), Mockito.any(Class.class))).thenReturn(null);

        beanfiller.addCreator(String[].class, arrayCreator);

        beanfiller.fillBean(ArraysObject.class);

        // assert that our mock was called instead of the default ArrayCreator
        verify(arrayCreator).createArray(Mockito.anyList(), Mockito.any(Class.class));
    }


    @Test
    public void testAddedSpecificArrayCreatorIsUsed() throws FillingException {

        SimpleArrayCreator arrayCreator = mock(SimpleArrayCreator.class);
        when(arrayCreator.createArray(Mockito.anyList(), Mockito.any(Class.class))).thenReturn(null);

        beanfiller.addCreatorForClassAndAttribute(ArraysObject.class, "stringArray", arrayCreator);

        beanfiller.fillBean(ArraysObject.class);

        // assert that our mock was called instead of the default ArrayCreator
        verify(arrayCreator).createArray(Mockito.anyList(), Mockito.any(Class.class));
    }


    @Test
    public void testWrongCreatorExceptionIsThrownIfNonArrayCreatorIsUsed() {

        StringCreator stringCreator = new StringCreator(new RandomGenerator());

        beanfiller.addCreatorForClassAndAttribute(ArraysObject.class, "stringArray", stringCreator);

        assertThrows(WrongCreatorException.class, () -> beanfiller.fillBean(ArraysObject.class));
    }
}
