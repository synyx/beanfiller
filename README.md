beanfiller
==========

Small library for recursively filling java beans with random data.

Usage
=====

<pre>
BeanFiller beanfiller = new BeanFiller();
Bean bean = beanfiller.fillBean(Bean.class);
</pre>

Beanfiller creates the class using a constructor (preferably with the least number of arguments) and
fills the members using the available setter methods. It does **NOT** set the members directly.

Currently supported Object Types
================================
<pre>
Arrays
BigDecimal
BigInteger
Boolean (and boolean)
Byte (and byte)
Character (and char)
Date
Double (and double)
Enums
Float (and float)
Integer (and int)
List (using ArrayList)
Long (and long)
Map (using HashMap)
Short (and short)
String
</pre>

Bean members of your Bean are scanned recursively for the above classes and are also being filled.


Simple workflow of BeanFiller
=============================
BeanFiller -> Strategy -> Creator -> Criteria

The BeanFiller gets the appropriate Strategy for the given class to fill, which then calls the corresponding Creator for this class to create it. The Creator relies on a Criteria to provide some edge conditions like min and max values for numbers. This happens recursively, so other Beans within your Beans also get filled.

You can heavily customise the BeanFiller by:
* adding your own Strategies
* adding your own Creators or replacing the standard Creators
* adding your own Criterias or replacing / reconfiguring the standard Criterias

For more information, see javadoc and tests.


