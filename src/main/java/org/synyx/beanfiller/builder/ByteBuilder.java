package org.synyx.beanfiller.builder;

import java.util.Random;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class ByteBuilder implements Builder<Byte> {

    @Override
    public Byte build() {

        Random rand = new Random();

        byte[] bytes = new byte[1];

        rand.nextBytes(bytes);

        return bytes[0];
    }
}
