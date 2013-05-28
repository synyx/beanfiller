package org.synyx.beanfiller.builder;

import org.synyx.beanfiller.criteria.ListCriteria;

import java.util.ArrayList;
import java.util.List;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class ListBuilder extends GenericsBuilder<List<?>> {

    private ListCriteria criteria;

    public ListBuilder(ListCriteria criteria) {

        this.criteria = criteria;
    }

    @Override
    public List<?> buildWithGenerics(List<Object> genericObjects) {

        if (genericObjects.size() >= 1) {
            List<Object> list = new ArrayList<Object>();

            for (int i = 0; i < genericObjects.size(); i++) {
                list.add(genericObjects.get(i));
            }

            return list;
        } else {
            return null;
        }
    }


    @Override
    public int getSize() {

        return criteria.getSize();
    }
}
