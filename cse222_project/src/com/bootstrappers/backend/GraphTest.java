package com.bootstrappers.backend;

import org.junit.Test;

import static org.junit.Assert.*;

public class GraphTest {


    Address kartal = new Address("Istanbul", "Turkey", "Kartal");
    Home kartalHome = new Home("3","rent","villa",kartal, 1, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 1300, 0);
    Graph g = new Graph();
    Home kartalHome2 = new Home("3","rent","villa",kartal, 1, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 1300, 0);


    @Test
    public void add() {
        g.add(new Home("3","rent","daire",kartal, 2, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 1300, 150),kartalHome);
        g.add(new Home("3","rent","apart",kartal, 3, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 1300, 350),kartalHome);
        g.add(new Home("3","rent","daire",kartal, 2, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 1300, 450),kartalHome);
        g.add(new Home("3","rent","apart",kartal, 3, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 1300, 50),kartalHome);

    }


    @Test
    public void depthFirstSearch() {
        Address kartal = new Address("Istanbul", "Turkey", "Kartal");
        Home kartalHome = new Home("3","rent","villa",kartal, 1, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 1300, 0);
        Graph g = new Graph();

        System.out.println(g.DepthFirstSearch(100));
        assertEquals(null,g.DepthFirstSearch(100));
    }

    @Test
    public void isConnected() {
        boolean f= true;
        assertEquals(f,g.isConnected());
    }

    @Test
    public void findVertex() {
        Home home1 = new Home("3","rent","daire",kartal, 2, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 1300, 150);
        g.add(home1, kartalHome);
        g.add(new Home("3","rent","apart",kartal, 3, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 1300, 350),kartalHome);
        g.add(new Home("3","rent","daire",kartal, 2, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 1300, 450),kartalHome);
        g.add(new Home("3","rent","apart",kartal, 3, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 1300, 50),kartalHome);

        Graph.Vertex vertex = g.new Vertex(home1);
        assertEquals(vertex.getValue(), g.findVertex(home1).getValue());
    }
}