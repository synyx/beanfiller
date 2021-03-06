package org.synyx.beanfiller.services;

import org.junit.Assert;
import org.junit.Test;

import org.synyx.beanfiller.domain.ObjectInformation;
import org.synyx.beanfiller.testobjects.BaseObject;
import org.synyx.beanfiller.testobjects.ObjectWithBeans;
import org.synyx.beanfiller.testobjects.TestEnum;

import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class AnalyzerTest {

    @Test
    public void testAnalyzerReturnsListWithRightSize() {

        List<ObjectInformation> objectInformationList = BeanAnalyzer.analyzeBean(ObjectWithBeans.class);

        assertEquals("Expected list to have 2 entries, one for each field!", 2, objectInformationList.size());
    }


    @Test
    public void testAnalyzerSetsClassesCorrectly() {

        List<ObjectInformation> objectInformationList = BeanAnalyzer.analyzeBean(ObjectWithBeans.class);

        ObjectInformation information1 = objectInformationList.get(0);
        ObjectInformation information2 = objectInformationList.get(1);

        assertEquals("Expected first entry to be BaseObject", BaseObject.class, information1.getClazz());
        assertEquals("Expected second entry to be TestEnum", TestEnum.class, information2.getClazz());
    }


    @Test
    public void testAnalyzerSetsFieldsCorrectly() {

        List<ObjectInformation> objectInformationList = BeanAnalyzer.analyzeBean(ObjectWithBeans.class);

        ObjectInformation information1 = objectInformationList.get(0);
        ObjectInformation information2 = objectInformationList.get(1);

        assertEquals("Expected first entry field to be baseObject", "baseObject",
            information1.getField().getName());
        assertEquals("Expected second entry field to be testEnum", "testEnum",
            information2.getField().getName());
    }


    @Test
    public void testAnalyzerSetsTypeCorrectly() {

        List<ObjectInformation> objectInformationList = BeanAnalyzer.analyzeBean(ObjectWithBeans.class);

        ObjectInformation information1 = objectInformationList.get(0);
        ObjectInformation information2 = objectInformationList.get(1);

        assertEquals("Expected first entry type to be 'class org.synyx.beanfiller.testobjects.BaseObject'",
            "class org.synyx.beanfiller.testobjects.BaseObject", information1.getType().toString());
        assertEquals("Expected second entry type to be 'class org.synyx.beanfiller.testobjects.TestEnum'",
            "class org.synyx.beanfiller.testobjects.TestEnum", information2.getType().toString());
    }


    @Test
    public void testAnalyzerSetsPathCorrectly() {

        List<ObjectInformation> objectInformationList = BeanAnalyzer.analyzeBean(ObjectWithBeans.class);

        ObjectInformation information1 = objectInformationList.get(0);
        ObjectInformation information2 = objectInformationList.get(1);

        assertEquals("Expected first entry path to be ObjectWithBeans.baseObject", "ObjectWithBeans.baseObject",
            information1.getPath());
        assertEquals("Expected second entry path to be ObjectWithBeans.testEnum", "ObjectWithBeans.testEnum",
            information2.getPath());
    }
}
