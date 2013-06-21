package org.synyx.beanfiller.builder;

import org.synyx.beanfiller.services.RandomGenerator;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class ByteBuilder implements Builder<Byte> {

    @Override
    public Byte build() {

        byte[] bytes = new byte[1];

        bytes = RandomGenerator.getRandomBytes(bytes);

        return bytes[0];
    }
}
