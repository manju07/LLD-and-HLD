package com.lld.and.hld.lldandhld.designpattern.structural;

/**
 * Handling 2 incompatible interfaces thru adapter (process() ---> adapter ---> processing())
 * 
 * Approach - 1
 *      Object Adapter
 *          Client -> Target(Interface, process()), Adapter(Class implements Target, internally hold object of Adapter), Adapter(Class, processing())  
 * 
 * Approach - 2
 *      Class Adapter
 *          Client -> Target(Interface), Adapter(Class extends Adapter implements Target), Adapter(Class)  
 */

/**
 * Target
 */
interface Target {
  void process();
}

/**
 * ObjectAdapter
 */
class ObjectAdapter implements Target {

  Adaptee adaptee = new Adaptee();

  @Override
  public void process() {
    System.out.print("\nObject Adapter calling - ");
    adaptee.processing();
  }

}

/**
 * ClassAdapter
 */
class ClassAdapter extends Adaptee implements Target {

  @Override
  public void process() {
    System.out.print("\nClass Adapter calling - ");
    this.processing();
  }
}

/**
 * Adaptee
 */
class Adaptee {

  public void processing() {
    System.out.print("Processing");
  }
}

public class AdapterPattern {
  public static void main(String[] args) {

    Target objectAdapter = new ObjectAdapter();
    objectAdapter.process();

    Target classAdapter = new ClassAdapter();
    classAdapter.process();
    System.out.println("\n\n");
  }
}
