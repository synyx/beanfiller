beanfiller
==========

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
Double (and double)
Enums
Float (and float)
Integer (and int)
List (using ArrayList)
Long (and long)
Map (using HashMap)
String
</pre>

Bean members are recursivle scanned for the above and also filled.

Support filling custom Objects in your Beans
============================================
To support custom Objects, you first have to provide a corresponding Builder for it. 
To do so, implement the Builder interface. For Arrays the ArrayBuilder, 
for Enums the EnumsBuilder (though the standard EnumBuilder should support all standard Enums) 
and for Objects with generic types, extend the GenericsBuilder. 
For further information please consult the javadoc of those classes.

To add your Builder to the BeanFiller, call
<pre>beanFiller.addBuilder(Class clazz, Builder builder)</pre>

You can even add a Builder for one specific field of a class by using
<pre>beanFiller.addBuilderForClassAndAttribute(Class clazz, String attributeName, Builder builder)</pre>

For more information, see javadoc.


