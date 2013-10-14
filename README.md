beanfiller
==========

Small library for filling java beans with random data.

Usage
=====

<pre>
BeanFiller beanfiller = new BeanFiller();
Bean bean = beanfiller.fillBean(Bean.class);
</pre>

Currently supported Object Types
================================
<pre>
Arrays
BigDecimal
BigInteger
Boolean (and boolean)
Byte (and byte)
Date
Double (and double)
Enums
Float (and float)
Integer (and int)
List (using ArrayList)
Long (and long)
Map (using HashMap)
String
</pre>

Bean members of your Bean are scanned recursivly for the above classes and are also beeing filled.

TODO more documentation in the readme
=====================================

For more information, see javadoc.


next up
=======
* Documentation in the readme.md
* Fixing Problems with cycling dependencies
* Add support for some basic Types
* JSR303 Bean Validation Extension


