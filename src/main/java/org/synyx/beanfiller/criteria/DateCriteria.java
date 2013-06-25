
package org.synyx.beanfiller.criteria;

import java.util.Date;


/**
 * Criteria for Dates.
 *
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

        this.minDate = new Date(minDate.getTime());
        this.maxDate = new Date(maxDate.getTime());
    }

    public Date getMinDate() {

        return new Date(minDate.getTime());
    }


    public void setMinDate(Date minDate) {

        this.minDate = new Date(minDate.getTime());
    }


    public Date getMaxDate() {

        return new Date(maxDate.getTime());
    }


    public void setMaxDate(Date maxDate) {

        this.maxDate = new Date(maxDate.getTime());
    }
}
