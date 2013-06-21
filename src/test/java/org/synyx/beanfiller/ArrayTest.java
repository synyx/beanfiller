
package org.synyx.beanfiller;

import org.junit.Assert;
import org.junit.Test;

import org.mockito.Mockito;

import org.synyx.beanfiller.creator.ArrayCreator;
import org.synyx.beanfiller.creator.SimpleArrayCreator;
import org.synyx.beanfiller.creator.StringCreator;
import org.synyx.beanfiller.testobjects.ArraysObject;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Tests for Arrays of different Types.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class ArrayTest {

    private BeanFiller beanfiller = new BeanFiller();
    private ArraysObject arraysObject;

    @Test
    public void testObjectIsCreated() throws FillingException {

        arraysObject = beanfiller.fillBean(new ArraysObject());
        Assert.assertNotNull("ArraysObject is null!", arraysObject);
    }


    @Test
    public void testStringArrayIsFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(new ArraysObject());
        Assert.assertNotNull("StringArray is null!", arraysObject.getStringArray());
    }


    @Test
    public void testStringArrayElementsAreFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(new ArraysObject());
        Assert.assertNotNull("StringArray Element is null!", arraysObject.getStringArray()[0]);
    }


    @Test
    public void testIntArrayIsFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(new ArraysObject());
        Assert.assertNotNull("IntArray is null!", arraysObject.getIntArray());
    }


    @Test
    public void testIntArrayElementsAreFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(new ArraysObject());
        Assert.assertNotNull("IntArray Element is null!", arraysObject.getIntArray()[0]);
    }


    @Test
    public void testObjectArrayIsFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(new ArraysObject());
        Assert.assertNotNull("ObjectArray is null!", arraysObject.getObjectArray());
    }


    @Test
    public void testObjectArrayElementsAreFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(new ArraysObject());
        Assert.assertNotNull("ObjectArray Element is null!", arraysObject.getObjectArray()[0]);
    }


    @Test
    public void testEnumArrayIsFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(new ArraysObject());
        Assert.assertNotNull("EnumArray is null!", arraysObject.getEnumArray());
    }


    @Test
    public void testEnumArrayElementsAreFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(new ArraysObject());
        Assert.assertNotNull("EnumArray Element is null!", arraysObject.getEnumArray()[0]);
    }


    @Test
    public void testListArrayIsFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(new ArraysObject());
        Assert.assertNotNull("ListArray is null!", arraysObject.getListArray());
    }


    @Test
    public void testListArrayElementsAreFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(new ArraysObject());
        Assert.assertNotNull("ListArray Element is null!", arraysObject.getListArray()[0]);
    }


    @Test
    public void testAddedArrayCreatorIsUsed() throws FillingException {

        ArrayCreator arrayCreator = mock(ArrayCreator.class);
        when(arrayCreator.createArray(Mockito.anyList(), Mockito.any(Class.class))).thenReturn(null);

        beanfiller.addCreator(String[].class, arrayCreator);

        beanfiller.fillBean(new ArraysObject());

        // assert that our mock was called instead of the default ArrayCreator
        verify(arrayCreator).createArray(Mockito.anyList(), Mockito.any(Class.class));
    }


    @Test
    public void testAddedSpecificArrayCreatorIsUsed() throws FillingException {

        SimpleArrayCreator arrayCreator = mock(SimpleArrayCreator.class);
        when(arrayCreator.createArray(Mockito.anyList(), Mockito.any(Class.class))).thenReturn(null);

        beanfiller.addCreatorForClassAndAttribute(ArraysObject.class, "stringArray", arrayCreator);

        beanfiller.fillBean(new ArraysObject());

        // assert that our mock was called instead of the default ArrayCreator
        verify(arrayCreator).createArray(Mockito.anyList(), Mockito.any(Class.class));
    }


    @Test(expected = WrongCreatorException.class)
    public void testWrongCreatorExceptionIsThrownIfNonArrayCreatorIsUsed() throws FillingException {

        StringCreator stringCreator = new StringCreator();

        beanfiller.addCreatorForClassAndAttribute(ArraysObject.class, "stringArray", stringCreator);

        beanfiller.fillBean(new ArraysObject());
    }
}
