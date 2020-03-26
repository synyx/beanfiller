package org.synyx.beanfiller;

import org.junit.Assert;
import org.junit.Test;

import org.synyx.beanfiller.creator.ListCreator;
import org.synyx.beanfiller.creator.SimpleCreator;
import org.synyx.beanfiller.exceptions.FillingException;
import org.synyx.beanfiller.exceptions.WrongCreatorException;
import org.synyx.beanfiller.testobjects.GenericsObject;
import org.synyx.beanfiller.testobjects.SimpleGenericsObject;
import org.synyx.beanfiller.util.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Tests for Objects with generic Types.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class GenericsTest {

    private final BeanFiller beanfiller = new BeanFiller();
    private GenericsObject genericsObject;

    @Test
    public void testGenericsObjectIsCreated() throws FillingException {

        genericsObject = beanfiller.fillBean(GenericsObject.class);
        assertNotNull("GenericsObject is null!", genericsObject);
    }


    @Test
    public void testListIsFilled() throws FillingException {

        genericsObject = beanfiller.fillBean(GenericsObject.class);
        assertNotNull("StringList is null!", genericsObject.getStringList());
        assertFalse("StringList is empty!", genericsObject.getStringList().isEmpty());
    }


    @Test
    public void testMapIsFilled() throws FillingException {

        genericsObject = beanfiller.fillBean(GenericsObject.class);
        assertNotNull("StringMap is null!", genericsObject.getStringMap());
        assertFalse("StringMap is empty!", genericsObject.getStringMap().isEmpty());
    }


    @Test
    public void testListMapIsFilled() throws FillingException {

        genericsObject = beanfiller.fillBean(GenericsObject.class);
        assertNotNull("StringListMap is null!", genericsObject.getStringListMap());
        assertFalse("StringListMap is empty!", genericsObject.getStringListMap().isEmpty());
    }


    @Test
    public void testAddedGenericsCreatorIsUsed() throws FillingException {

        ListCreator listCreator = mock(ListCreator.class);

        when(listCreator.createCollection(anyList())).thenReturn(new ArrayList<>());

        beanfiller.addCreator(List.class, listCreator);

        beanfiller.fillBean(SimpleGenericsObject.class);

        // assert that our mock was called
        verify(listCreator).createCollection(anyList());
    }


    @Test
    public void testAddedSpecificGenericsCreatorIsUsed() throws FillingException {

        ListCreator listCreator = mock(ListCreator.class);
        when(listCreator.createCollection(anyList())).thenReturn(new ArrayList<>());
        beanfiller.addCreatorForClassAndAttribute(SimpleGenericsObject.class, "stringList", listCreator);

        beanfiller.fillBean(SimpleGenericsObject.class);

        // assert that our mock was called
        verify(listCreator).createCollection(anyList());
    }


    @Test(expected = WrongCreatorException.class)
    public void testWrongCreatorExceptionIsThrownIfNonGenericsCreatorIsUsed() throws FillingException {

        SimpleCreator stringCreator = new org.synyx.beanfiller.creator.StringCreator(new RandomGenerator());

        beanfiller.addCreatorForClassAndAttribute(GenericsObject.class, "stringList", stringCreator);

        beanfiller.fillBean(GenericsObject.class);
    }
}
