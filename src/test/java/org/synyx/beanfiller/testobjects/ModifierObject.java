
package org.synyx.beanfiller.testobjects;

/**
 * Object for the Tests with different modifiers.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class ModifierObject {

    private static String PRIVATE_STATIC_STRING = null;
    private static String PROTECTED_STATIC_STRING = null;
    private static String PACKAGE_STATIC_STRING = null;
    private static String PUBLIC_STATIC_STRING = null;

    private final String publicFinalString = null;

    public static String getPRIVATE_STATIC_STRING() {

        return PRIVATE_STATIC_STRING;
    }


    private static void setPRIVATE_STATIC_STRING(String PRIVATE_STATIC_STRING) {

        ModifierObject.PRIVATE_STATIC_STRING = PRIVATE_STATIC_STRING;
    }


    public static String getPROTECTED_STATIC_STRING() {

        return PROTECTED_STATIC_STRING;
    }


    protected static void setPROTECTED_STATIC_STRING(String PROTECTED_STATIC_STRING) {

        ModifierObject.PROTECTED_STATIC_STRING = PROTECTED_STATIC_STRING;
    }


    public static String getPACKAGE_STATIC_STRING() {

        return PACKAGE_STATIC_STRING;
    }


    static void setPACKAGE_STATIC_STRING(String PACKAGE_STATIC_STRING) {

        ModifierObject.PACKAGE_STATIC_STRING = PACKAGE_STATIC_STRING;
    }


    public static String getPUBLIC_STATIC_STRING() {

        return PUBLIC_STATIC_STRING;
    }


    public static void setPUBLIC_STATIC_STRING(String PUBLIC_STATIC_STRING) {

        ModifierObject.PUBLIC_STATIC_STRING = PUBLIC_STATIC_STRING;
    }


    public String getPublicFinalString() {

        return publicFinalString;
    }
}
