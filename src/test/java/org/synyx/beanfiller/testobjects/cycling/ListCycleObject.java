
package org.synyx.beanfiller.testobjects.cycling;

import java.util.List;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class ListCycleObject {

    private List<ListCycleObject> list;

    public List<ListCycleObject> getList() {

        return list;
    }


    public void setList(List<ListCycleObject> list) {

        this.list = list;
    }
}
