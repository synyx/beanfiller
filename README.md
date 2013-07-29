beanfiller
==========

OUTDATED - NEW DOCUMENTATION IN THE MAKING
==========================================


Small library for filling beans with random data.

Usage
=====

<pre>
BeanFiller beanfiller = new BeanFiller();
Bean bean = beanfiller.fillBean(new Bean());
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

Support filling custom Objects in your Beans
============================================
To support custom Objects, you first have to provide a corresponding Creator for it. 
To do so, implement the Creators interface. For Arrays the ArrayCreator, 
for Enums the EnumsCreator (though the default SimpleEnumCreator should support all standard Enums) 
and for Objects with generic types, implement the GenericsCreator. 
For further information please consult the javadoc of those classes.

To add your Creator to the BeanFiller, call
<pre>beanFiller.addCreator(Class clazz, Creator creator)</pre>

You can even add a Creator for one specific field of a class by using
<pre>beanFiller.addCreatorForClassAndAttribute(Class clazz, String attributeName, Creator creator)</pre>

For more information, see javadoc.


next up
=======
* Refactorings


