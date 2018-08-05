package com.bootstrappers.backend;

import org.junit.Test;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

import static org.junit.Assert.*;

public class BootStrappersTest {

    @Test
    public void generateDescendingOrderedStack() {
        BootStrappers bootStrappers = new BootStrappers();
        Address kartal = new Address("Istanbul", "Turkey", "Kartal");
        Home home1 = new Home("3","rent","daire",kartal, 2, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 1300, 150);
        Home home2 = new Home("3","rent","villa",kartal, 2, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 1000, 150);
        Home home3 = new Home("2","rent","apart",kartal, 2, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 950, 150);
        Home home4 = new Home("1","rent","apart",kartal, 2, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 600, 150);

        ArrayList<Home> homeArrayList = new ArrayList<>();
        homeArrayList.add(home2);
        homeArrayList.add(home3);
        homeArrayList.add(home1);
        homeArrayList.add(home4);

        Stack<Home> homeStack = new Stack<Home>();
        homeStack.push(home4);
        homeStack.push(home3);
        homeStack.push(home2);
        homeStack.push(home1);

        assertEquals(homeStack, bootStrappers.generateDescendingOrderedStack(homeArrayList));

    }

    @Test
    public void displayPriorityQueue() {

        BootStrappers bootStrappers = new BootStrappers();
        Address kartal = new Address("Istanbul", "Turkey", "Kartal");
        Home home1 = new Home("3","rent","daire",kartal, 2, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 1300, 150);
        Home home2 = new Home("3","rent","villa",kartal, 2, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 1000, 150);
        Home home3 = new Home("2","rent","apart",kartal, 2, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 950, 150);
        Home home4 = new Home("1","rent","apart",kartal, 2, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 600, 150);

        ArrayList<Home> homeArrayList = new ArrayList<>();
        homeArrayList.add(home2);
        homeArrayList.add(home3);
        homeArrayList.add(home1);
        homeArrayList.add(home4);

        PriorityQueue<Home> queue= new PriorityQueue<Home>();

        queue.add(home4);
        queue.add(home3);
        queue.add(home2);
        queue.add(home1);

        assertEquals(600, bootStrappers.generateAscendingOrderedQueue(homeArrayList).poll().getPrice());

    }

    @Test
    public void generateTotalHomesInPlace() {

        BootStrappers bootStrappers = new BootStrappers();
        Address kartal = new Address("Istanbul", "Turkey", "Kartal");
        Home home1 = new Home("3","rent","daire",kartal, 2, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 1300, 150);
        Home home2 = new Home("3","rent","villa",kartal, 2, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 1000, 150);
        Home home3 = new Home("2","rent","apart",kartal, 2, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 950, 150);
        Home home4 = new Home("1","rent","apart",kartal, 2, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 600, 150);

        ArrayList<Home> homeArrayList = new ArrayList<>();
        homeArrayList.add(home2);
        homeArrayList.add(home3);
        homeArrayList.add(home1);
        homeArrayList.add(home4);

        assertEquals(4, bootStrappers.generateTotalHomesInPlace(homeArrayList, "Kartal").get("Kartal").intValue());
    }
}