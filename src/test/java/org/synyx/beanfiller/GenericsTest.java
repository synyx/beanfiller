package org.synyx.beanfiller;

import org.junit.jupiter.api.Test;
import org.synyx.beanfiller.creator.ListCreator;
import org.synyx.beanfiller.creator.SimpleCreator;
import org.synyx.beanfiller.exceptions.FillingException;
import org.synyx.beanfiller.exceptions.WrongCreatorException;
import org.synyx.beanfiller.testobjects.GenericsObject;
import org.synyx.beanfiller.testobjects.SimpleGenericsObject;
import org.synyx.beanfiller.util.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;


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
        assertThat(genericsObject, notNullValue());
    }


    @Test
    public void testListIsFilled() throws FillingException {

        genericsObject = beanfiller.fillBean(GenericsObject.class);
        assertThat(genericsObject.getStringList(), notNullValue());
        assertThat(genericsObject.getStringList().isEmpty(), is(false));
    }


    @Test
    public void testMapIsFilled() throws FillingException {

        genericsObject = beanfiller.fillBean(GenericsObject.class);
        assertThat(genericsObject.getStringMap(), notNullValue());
        assertThat(genericsObject.getStringMap().isEmpty(), is(false));
    }


    @Test
    public void testListMapIsFilled() throws FillingException {

        genericsObject = beanfiller.fillBean(GenericsObject.class);
        assertThat(genericsObject.getStringListMap(), notNullValue());
        assertThat(genericsObject.getStringListMap().isEmpty(), is(false));
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


    @Test
    public void testWrongCreatorExceptionIsThrownIfNonGenericsCreatorIsUsed() {

        SimpleCreator<String> stringCreator = new org.synyx.beanfiller.creator.StringCreator(new RandomGenerator());

        beanfiller.addCreatorForClassAndAttribute(GenericsObject.class, "stringList", stringCreator);

        assertThrows(WrongCreatorException.class, () -> beanfiller.fillBean(GenericsObject.class));
    }
}
