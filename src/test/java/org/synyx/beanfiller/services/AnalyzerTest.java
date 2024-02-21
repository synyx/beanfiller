package org.synyx.beanfiller.services;

import org.junit.jupiter.api.Test;
import org.synyx.beanfiller.domain.ObjectInformation;
import org.synyx.beanfiller.testobjects.BaseObject;
import org.synyx.beanfiller.testobjects.ObjectWithBeans;
import org.synyx.beanfiller.testobjects.TestEnum;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class AnalyzerTest {

    @Test
    public void testAnalyzerReturnsListWithRightSize() {

        List<ObjectInformation> objectInformationList = BeanAnalyzer.analyzeBean(ObjectWithBeans.class);
        assertThat( objectInformationList.size(), is(2));
    }


    @Test
    public void testAnalyzerSetsClassesCorrectly() {

        List<ObjectInformation> objectInformationList = BeanAnalyzer.analyzeBean(ObjectWithBeans.class);

        ObjectInformation information1 = objectInformationList.get(0);
        ObjectInformation information2 = objectInformationList.get(1);

        assertThat(information1.getClazz(), is(BaseObject.class));
        assertThat(information2.getClazz(), is(TestEnum.class));
    }


    @Test
    public void testAnalyzerSetsFieldsCorrectly() {

        List<ObjectInformation> objectInformationList = BeanAnalyzer.analyzeBean(ObjectWithBeans.class);

        ObjectInformation information1 = objectInformationList.get(0);
        ObjectInformation information2 = objectInformationList.get(1);

        assertThat(information1.getField().getName(), is( "baseObject"));
        assertThat(information2.getField().getName(), is( "testEnum"));
    }


    @Test
    public void testAnalyzerSetsTypeCorrectly() {

        List<ObjectInformation> objectInformationList = BeanAnalyzer.analyzeBean(ObjectWithBeans.class);

        ObjectInformation information1 = objectInformationList.get(0);
        ObjectInformation information2 = objectInformationList.get(1);

        assertThat(information1.getType().toString(), is( "class org.synyx.beanfiller.testobjects.BaseObject"));
        assertThat(information2.getType().toString(), is( "class org.synyx.beanfiller.testobjects.TestEnum"));
    }


    @Test
    public void testAnalyzerSetsPathCorrectly() {

        List<ObjectInformation> objectInformationList = BeanAnalyzer.analyzeBean(ObjectWithBeans.class);

        ObjectInformation information1 = objectInformationList.get(0);
        ObjectInformation information2 = objectInformationList.get(1);

        assertThat(information1.getPath(), is( "ObjectWithBeans.baseObject"));
        assertThat(information2.getPath(), is( "ObjectWithBeans.testEnum"));
    }
}
