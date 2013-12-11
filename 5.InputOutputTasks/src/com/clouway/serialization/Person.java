package com.clouway.serialization;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/6/13
 * Time: 4:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class Person implements Serializable {


    public final String name;
    public final transient int SSH;
    public final String address;
    public final int age;

    public Person(String name, String address, int SSH, int age) {
        this.name = name;
        this.address = address;
        this.SSH = SSH;
        this.age = age;
    }
}
