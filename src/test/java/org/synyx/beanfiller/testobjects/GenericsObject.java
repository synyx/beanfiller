
package org.synyx.beanfiller.testobjects;

import java.util.List;
import java.util.Map;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class GenericsObject {

    private List<String> stringList;
    private Map<String, String> stringMap;
    private Map<String, List<Map<String, List<Integer>>>> stringListMap;

    public List<String> getStringList() {

        return stringList;
    }


    public void setStringList(List<String> stringList) {

        this.stringList = stringList;
    }


    public Map<String, String> getStringMap() {

        return stringMap;
    }


    public void setStringMap(Map<String, String> stringMap) {

        this.stringMap = stringMap;
    }


    public Map<String, List<Map<String, List<Integer>>>> getStringListMap() {

        return stringListMap;
    }


    public void setStringListMap(Map<String, List<Map<String, List<Integer>>>> stringListMap) {

        this.stringListMap = stringListMap;
    }
}
