
package org.synyx.beanfiller.criteria;

/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class StringCriteria implements Criteria<String> {

    private int minlength;
    private int maxlength;
    private String charset;

    public StringCriteria() {

        this(6, 8, "abcABCD0123äß!?");
    }


    public StringCriteria(int minlength, int maxlength, String charset) {

        this.minlength = minlength;
        this.maxlength = maxlength;
        this.charset = charset;
    }

    public int getMinlength() {

        return minlength;
    }


    public void setMinlength(int minlength) {

        this.minlength = minlength;
    }


    public int getMaxlength() {

        return maxlength;
    }


    public void setMaxlength(int maxlength) {

        this.maxlength = maxlength;
    }


    public String getCharset() {

        return charset;
    }


    public void setCharset(String charset) {

        this.charset = charset;
    }
}
