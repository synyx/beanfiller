
package org.synyx.beanfiller;

import org.junit.jupiter.api.Test;
import org.synyx.beanfiller.creator.Creator;
import org.synyx.beanfiller.creator.SimpleCreator;
import org.synyx.beanfiller.util.RandomGenerator;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;


/**
 * Tests of the different setters and Constructors.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class SettersAndConstructorsTest {

    @Test
    public void testDefaultCreatorMapIsUsed() {

        BeanFiller beanfiller = new BeanFiller();

        assertThat(beanfiller.getCreatorMap(), notNullValue());
        assertThat(beanfiller.getCreatorMap().isEmpty(), is(false));
    }


    @Test
    public void testSetCreatorMapWithConstructor() {

        BeanFiller beanfiller = new BeanFiller(new HashMap<>());
        assertThat(beanfiller.getCreatorMap(), notNullValue());
        assertThat(beanfiller.getCreatorMap().isEmpty(), is(true));
    }


    /**
     * Test the adding of Creators to the map.
     */
    @Test
    public void testAddCreator() {

        BeanFiller beanfiller = new BeanFiller();

        SimpleCreator<String> creator = new org.synyx.beanfiller.creator.StringCreator(new RandomGenerator());
        beanfiller.addCreator(this.getClass(), creator);

        Map<String, Creator> creatorMap = beanfiller.getCreatorMap();
        assertThat(creatorMap.get(this.getClass().getName()), is(creator));
    }


    /**
     * Test the adding of a Creator for an Array class to the map.
     */
    @Test
    public void testAddArrayCreator() {

        BeanFiller beanfiller = new BeanFiller();

        SimpleCreator<String> creator = new org.synyx.beanfiller.creator.StringCreator(new RandomGenerator());
        beanfiller.addCreator(String[].class, creator);

        Map<String, Creator> creatorMap = beanfiller.getCreatorMap();
        assertThat(creatorMap.get("[Ljava.lang.String;"), is(creator));
    }


    /**
     * Test the adding of Creators to the map is not done without a class given.
     */
    @Test
    public void testAddCreatorWithoutClass() {

        BeanFiller beanfiller = new BeanFiller(new HashMap<>());

        SimpleCreator<String> creator = new org.synyx.beanfiller.creator.StringCreator(new RandomGenerator());
        beanfiller.addCreator(null, creator);

        Map<String, Creator> creatorMap = beanfiller.getCreatorMap();
        assertThat(creatorMap.isEmpty(), is(true));
    }


    /**
     * Test the adding of Creators to the map is not done without a Creator given.
     */
    @Test
    public void testAddCreatorWithoutCreator() {

        BeanFiller beanfiller = new BeanFiller(new HashMap<>());

        beanfiller.addCreator(this.getClass(), null);

        Map<String, Creator> creatorMap = beanfiller.getCreatorMap();
        assertThat(creatorMap.isEmpty(), is(true));
    }


    /**
     * Test the adding of Creators to the specific Creators map.
     */
    @Test
    public void testAddSpecificCreator() {

        BeanFiller beanfiller = new BeanFiller();

        SimpleCreator<String> creator = new org.synyx.beanfiller.creator.StringCreator(new RandomGenerator());
        beanfiller.addCreatorForClassAndAttribute(this.getClass(), "test", creator);

        Map<String, Creator> creatorMap = beanfiller.getClassAndAttributeSpecificCreatorMap();
        assertThat( creatorMap.get(this.getClass().getName() + ".test"), is(creator));
    }


    /**
     * Test the adding of Creators to the specific Creators map is not done without a class given.
     */
    @Test
    public void testAddSpecificCreatorWithoutClass() {

        BeanFiller beanfiller = new BeanFiller(new HashMap<>());

        SimpleCreator<String> creator = new org.synyx.beanfiller.creator.StringCreator(new RandomGenerator());
        beanfiller.addCreatorForClassAndAttribute(null, "test", creator);

        Map<String, Creator> creatorMap = beanfiller.getClassAndAttributeSpecificCreatorMap();
        assertThat(creatorMap.isEmpty(), is(true));
    }


    /**
     * Test the adding of Creators to the specific Creators map is not done without an attributeName given.
     */
    @Test
    public void testAddSpecificCreatorWithoutAttributeName() {

        // we need an empty Creator map for this test
        BeanFiller beanfiller = new BeanFiller(new HashMap<>());

        SimpleCreator<String> creator = new org.synyx.beanfiller.creator.StringCreator(new RandomGenerator());
        beanfiller.addCreatorForClassAndAttribute(this.getClass(), null, creator);

        Map<String, Creator> creatorMap = beanfiller.getClassAndAttributeSpecificCreatorMap();
        assertThat(creatorMap.isEmpty(), is(true));
    }


    /**
     * Test the adding of Creators to the specific Creators map is not done without a Creator given.
     */
    @Test
    public void testAddSpecificCreatorWithoutCreator() {

        BeanFiller beanfiller = new BeanFiller(new HashMap<>());

        beanfiller.addCreatorForClassAndAttribute(this.getClass(), "test", null);

        Map<String, Creator> creatorMap = beanfiller.getClassAndAttributeSpecificCreatorMap();
        assertThat(creatorMap.isEmpty(), is(true));
    }
}
