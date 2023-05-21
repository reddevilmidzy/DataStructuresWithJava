package org.example.ch02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    protected List<Integer> mylist;
    protected List<Integer> list;

    @BeforeEach
    public void setUp() throws Exception {
        list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        mylist = new MyArrayList<>();
        mylist.addAll(list);
    }

    @Test
    public void mylist() {
        Assertions.assertEquals(mylist.size(), 3);
    }

    @Test
    public void testAddT() {
        for (int i = 4; i < 20; i++) {
            mylist.add(i);
        }
        //System.out.println(Arrays.toString(mal.toArray()));
        Assertions.assertEquals(mylist.get(18), (new Integer(19)));
    }

    @Test
    public void testAddIntT() {
        mylist.add(1, 5);
        //System.out.println(Arrays.toString(mal.toArray()));
        Assertions.assertEquals(mylist.get(1), (new Integer(5)));
        Assertions.assertEquals(mylist.size(), (4));

        try {
            mylist.set(-1, 0);
            fail();
        } catch (IndexOutOfBoundsException e) {} // good

        try {
            mylist.set(4, 0);
            fail();
        } catch (IndexOutOfBoundsException e) {} // good

        mylist.add(0, 6);
        //System.out.println(Arrays.toString(mal.toArray()));
        Assertions.assertEquals(mylist.get(0), (6));

        mylist.add(5, 7);
        //System.out.println(Arrays.toString(mal.toArray()));
        Assertions.assertEquals(mylist.get(5), (new Integer(7)));
    }
}