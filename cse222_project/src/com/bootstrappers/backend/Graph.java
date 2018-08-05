package com.bootstrappers.backend;
import java.util.ArrayList;

@SuppressWarnings("unchecked")

public class Graph<T extends Comparable<T>>
{
    /**
     * An enum for the three stated used by the Depth first search
     */
    public enum State { UNVISITED, VISITED, COMPLETE };

    // a list to hold all the vertices
    private ArrayList<Vertex> vertexList;

    // list to hold the edges
    // not really used for anything
    // but display purposes
    private ArrayList<Edge> edgeList;

    ArrayList<Home> result = new ArrayList<>();


    /**
     * Constructor.
     */
    public Graph()
    {
        vertexList = new ArrayList<>();
        edgeList = new ArrayList<>();
    }


    /**
     * Add method.
     * @param x vertex to be added.
     * @param y vertex to be added.
     */
    public void add(T x, T y)
    {
        Edge e = new Edge(x, y);
        edgeList.add(e);
    }

    /**
     * Add method for district.
     * @param x vertex to be added.
     */
    public void add(T x) {
        Address kartal = new Address("Istanbul", "Turkey", "Kartal");
        Home kartalHome = new Home("3","rent","merkez",kartal, 1, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 1300, 0);
        add(x, (T) kartalHome);
    }

    /**
     * Finds desired vertex.
     * @param v vertex to be searched.
     * @return Vertex.
     */
    public Vertex findVertex(T v)
    {
        for (Vertex each : vertexList)
        {
            if (((Home)each.getValue()).compareToWithDistance((Home)v)==0) {
                return each;
            }
        }
        return null;
    }

    /**
     * To string method for graph.
     * @return string.
     */
    public String toString()
    {
        String retval = "";
        for (Vertex each : vertexList)
        {
            retval += each.toString() + "\n";
        }
        return retval;
    }

    /**
     * To string method for Edges.
     * @return string.
     */
    public String edgesToString()
    {
        String retval = "";
        for (Edge each : edgeList)
        {
            retval += each;
        }
        return retval;
    }

    /**
     * Depth First Search for graph.
     * @param GUIValue starting point.
     * @return traversed list of houses.
     */
    public ArrayList<Home> DepthFirstSearch(int GUIValue)
    {
        if (vertexList.isEmpty()) return null;

        // get first node
        Vertex root = vertexList.get(0);
        if (root==null) return null;

        // call recursive function
        return DepthFirstSearch(root, GUIValue);
    }

    /**
     * DFS wrapper method.
     * @param v vertex.
     * @param GUIValue starting point.
     * @return traversed list of houses.
     */
    private ArrayList<Home> DepthFirstSearch(Vertex v, int GUIValue)
    {

        // loop through neighbors
        for (Vertex each : v.getAdjacentList())
        {
            if (each.getState()==State.UNVISITED)
            {
                if (((Home)each.getValue()).getDistance() <= GUIValue && !((Home)each.getValue()).getType().equals("merkez")){
                    result.add((Home) each.getValue());
                    //System.out.println("Value: " + each.getValue());
                }
                each.setState(State.VISITED);
                DepthFirstSearch(each, GUIValue);
            }
        }
        v.setState(State.COMPLETE);
        return result;
    }

    /**
     * Checks if the graph is connected.
     * @return true if connected, false otherwise.
     */
    public boolean isConnected()
    {
        for (Vertex each : vertexList)
        {
            if (each.getState() != State.COMPLETE)
                return false;
        }
        return true;
    }

    // vertex class
    class Vertex
    {
        T value;
        ArrayList<Vertex> adjacent;
        State state;

        /**
         * Constructor.
         * @param v vertex to be initialized.
         */
        public Vertex(T v)
        {
            value = v;
            adjacent = new ArrayList<>();
            state = State.UNVISITED;
        }

        /**
         * Getter.
         * @return state.
         */
        public State getState()
        {
            return state;
        }

        /**
         * Setter.
         * @param s state to be set.
         */
        public void setState(State s)
        {
            state = s;
        }
        /**
         * Getter.
         * @return value.
         */
        public T getValue()
        {
            return value;
        }

        /**
         * Adds neighbour vertex.
         * @param n vertex to be added.
         */
        public void addNeighbor(Vertex n)
        {
            adjacent.add(n);
        }

        /**
         * Getter.
         * @return adjacent list.
         */
        public ArrayList<Vertex> getAdjacentList()
        {
            return adjacent;
        }

        /**
         * To string method.
         * @return string.
         */
        public String toString()
        {
            String retval = "";
            retval += "Vertex: " + value + ":";
            for (Vertex each : adjacent)
            {
                retval += each.getValue() + " ";
            }
            return retval;
        }


    }

    // edge class
    class Edge
    {
        private Vertex x;
        private Vertex y;
        /**
         * Constructor.
         * @param v1 vertex to be added.
         * @param v2 vertex to be added.
         */
        public Edge(T v1, T v2)
        {
            // check to see if first vertex exists
            x = findVertex(v1);
            if (x == null)
            {
                // doesn't exist, add new
                x = new Vertex(v1);
                // and add to master list
                vertexList.add(x);
            }
            // same for second vertex
            /*System.out.println("\n\nv2:" + v2);

            System.out.println("Vertex List:");
            for (Vertex ver: vertexList
                 ) {
                System.out.println(ver.getValue());
            }
*/
            y = findVertex(v2);
            //System.out.println("dÃ¶nen:" + y + "\n\n\n");

            if (y == null)
            {
                y = new Vertex(v2);
                vertexList.add(y);
            }
            // add each vertex to the adjacent list for the other
            x.addNeighbor(y);
            y.addNeighbor(x);

        }


        /**
         * To string method.
         * @return string.
         */
        public String toString()
        {
            return "Edge X:" + x.getValue() + " Y:" + y.getValue() + "\n";
        }


    }

    public static void main(String[] args) {

        Address kartal = new Address("Istanbul", "Turkey", "Kartal");
        Home kartalHome = new Home("3","rent","merkez",kartal, 1, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 1300, 0);
        Graph g = new Graph();
        //System.out.println(g.DepthFirstSearch(100));
        g.add(new Home("3","rent","daire",kartal, 2, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 1300, 150),kartalHome);
        g.add(new Home("3","rent","apart",kartal, 3, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 1300, 350),kartalHome);
        g.add(new Home("3","rent","daire",kartal, 2, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 1300, 450),kartalHome);
        g.add(new Home("3","rent","apart",kartal, 3, new Member("melih", "kavraz", "123qwe", "123123", "asasdasd@gmail.com", "member"),130, 1300, 50),kartalHome);

        ArrayList<Home> result = g.DepthFirstSearch(100);
        System.out.println(result);

    }
}