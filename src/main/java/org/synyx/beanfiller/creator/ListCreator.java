package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.ListCriteria;

import java.util.ArrayList;
import java.util.List;


/**
 * Creator for Lists.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class ListCreator implements GenericsCreator<List<?>> {

    private ListCriteria criteria;

    public ListCreator(ListCriteria criteria) {

        this.criteria = criteria;
    }

    @Override
    public List<?> createWithGenerics(List<Object> genericObjects) {

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
