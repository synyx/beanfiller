
package org.synyx.beanfiller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.synyx.beanfiller.exceptions.FillingException;
import org.synyx.beanfiller.testobjects.ModifierObject;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


/**
 * Tests using different modifiers on the methods and variables.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class ModifierTest {

    private final BeanFiller beanfiller = new BeanFiller();
    private ModifierObject modifierObject;

    @Before
    public void setup() throws FillingException {

        modifierObject = beanfiller.fillBean(ModifierObject.class);
    }


    /**
     * Test that using public setters on domain objects works.
     */
    @Test
    public void testPublicStaticSetter() {

        assertNotNull(ModifierObject.getPUBLIC_STATIC_STRING());
    }


    /**
     * Test that using package protected setters on domain objects doesn't work.
     */
    @Test
    public void testPackageStaticSetter() {

        // we can't do that, so it's still null
        assertNull(ModifierObject.getPACKAGE_STATIC_STRING());
    }


    /**
     * Test that using protected setters on domain objects doesn't work.
     */
    @Test
    public void testProtectedStaticSetter() {

        // we can't do that, so it's still null
        assertNull(ModifierObject.getPROTECTED_STATIC_STRING());
    }


    /**
     * Test that using private setters on domain objects doesn't work.
     */
    @Test
    public void testPrivateStaticSetter() {

        // we can't do that, so it's still null
        assertNull(ModifierObject.getPRIVATE_STATIC_STRING());
    }


    /**
     * Test that setting final variables on domain objects doesn't work.
     */
    @Test
    public void testPublicFinalString() {

        // has to be null, because it's final and initialized with null
        assertNull(modifierObject.getPublicFinalString());
    }
}
