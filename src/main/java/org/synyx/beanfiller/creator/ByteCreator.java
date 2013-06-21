package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.services.RandomGenerator;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class ByteCreator implements SimpleCreator<Byte> {

    @Override
    public Byte create() {

        byte[] bytes = new byte[1];

        bytes = RandomGenerator.getRandomBytes(bytes);

        return bytes[0];
    }
}
