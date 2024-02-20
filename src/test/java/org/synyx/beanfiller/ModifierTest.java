
package org.synyx.beanfiller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.synyx.beanfiller.exceptions.FillingException;
import org.synyx.beanfiller.testobjects.ModifierObject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;


/**
 * Tests using different modifiers on the methods and variables.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class ModifierTest {

    private BeanFiller beanfiller;
    private ModifierObject modifierObject;

    @BeforeEach
    public void setup() throws FillingException {

        beanfiller = new BeanFiller();
        modifierObject = beanfiller.fillBean(ModifierObject.class);
    }


    /**
     * Test that using public static setters on domain objects works.
     */
    @Test
    public void testPublicStaticSetter() {

        assertThat(ModifierObject.getPUBLIC_STATIC_STRING(), notNullValue());
    }


    /**
     * Test that using package protected static setters on domain objects doesn't work.
     */
    @Test
    public void testPackageStaticSetter() {

        // we can't do that, so it's still null
        assertThat(ModifierObject.getPACKAGE_STATIC_STRING(), nullValue());
    }


    /**
     * Test that using protected static setters on domain objects doesn't work.
     */
    @Test
    public void testProtectedStaticSetter() {

        // we can't do that, so it's still null
        assertThat(ModifierObject.getPROTECTED_STATIC_STRING(), nullValue());
    }


    /**
     * Test that using private static setters on domain objects doesn't work.
     */
    @Test
    public void testPrivateStaticSetter() {

        // we can't do that, so it's still null
        assertThat(ModifierObject.getPRIVATE_STATIC_STRING(), nullValue());
    }


    /**
     * Test that setting final variables on domain objects doesn't work.
     */
    @Test
    public void testPublicFinalString() {

        // has to be null, because it's final and initialized with null
        assertThat(modifierObject.getPublicFinalString(), nullValue());
    }
}
