
package org.synyx.beanfiller.criteria;

import java.util.Date;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class DateCriteria implements Criteria<Date> {

    private static final long FIFTY_DAYS = 4320000000L;

    private Date minDate;
    private Date maxDate;

    public DateCriteria() {

        long current = System.currentTimeMillis();

        minDate = new Date(current - FIFTY_DAYS);
        maxDate = new Date(current + FIFTY_DAYS);
    }


    public DateCriteria(Date minDate, Date maxDate) {

        this.minDate = minDate;
        this.maxDate = maxDate;
    }

    public Date getMinDate() {

        return minDate;
    }


    public void setMinDate(Date minDate) {

        this.minDate = minDate;
    }


    public Date getMaxDate() {

        return maxDate;
    }


    public void setMaxDate(Date maxDate) {

        this.maxDate = maxDate;
    }
}
