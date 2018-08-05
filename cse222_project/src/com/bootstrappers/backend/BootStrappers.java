package com.bootstrappers.backend;

import java.util.*;


public class BootStrappers extends BinarySearchTree<Integer> implements Comparator<Home>{

    private ArrayList<Home> homeArrayList = new ArrayList<>();
    private ArrayList<SystemUserAbstract> userArrayList = new ArrayList<>();
    Comparator<Home> com = new MyComparator();
    PriorityQueue<Home> homeQueue = new PriorityQueue<Home>(10, com);
    Stack<Home> home = new Stack<Home>();
    private BinarySearchTree<Integer> areaOfHomes = new BinarySearchTree<>();
    private ArrayList<Integer> descendingArea = new ArrayList<>();
    private ArrayList<Integer> ascendingArea = new ArrayList<>();
    private Map<String, Integer> homesInPlace = new HashMap<>();
    private Graph graph = new Graph();


    /**
     * Constructor for main class.
     */
    public BootStrappers() {
        FileOperations fileOperations = new FileOperations();
        fileOperations.readUserList(userArrayList);
        fileOperations.readHomeList(homeArrayList, userArrayList);

    }

    /**
     * Getter.
     * @return home array.
     */
    public ArrayList<Home> getHomeArrayList() {
        return homeArrayList;
    }

    /**
     * Getter.
     * @return user array.
     */
    public ArrayList<SystemUserAbstract> getUserArrayList() {
        return userArrayList;
    }

    /**
     * Filter houses according to the desired categories.
     * @param neighborhood district.
     * @param countOfRoom number of rooms.
     * @param status status of houses; rent or sale.
     * @param type type of houses; apartment, house, mansion, etc.
     * @param priceLow lowest price of the house.
     * @param priceHigh highest price of the house.
     * @return filtered array.
     */
    public ArrayList<Home> filterHomes(String neighborhood, String countOfRoom, String status,
                                       String type, Integer priceLow, Integer priceHigh) {


        if(!neighborhood.equals("null") && countOfRoom.equals("null") && status.equals("null")
                && type.equals("null") && priceHigh==-1 && priceLow==-1) {
            generateTotalHomesInPlace(getHomeArrayList(), neighborhood);
            updateGraph();
        }

        ArrayList<Home> tempList = new ArrayList<Home>();
        for(int i=0; i<homeArrayList.size(); ++i)
            tempList.add(homeArrayList.get(i));

        for(int i=0; i<homeArrayList.size(); ++i) {

            if(!neighborhood.equals("null") && !homeArrayList.get(i).getAddress().getNeighborhood().equals(neighborhood))
                tempList.set(i, null);

            else if(!countOfRoom.equals("null") && !homeArrayList.get(i).getCountOfRoom().equals(countOfRoom))
                tempList.set(i, null);

            else if(!status.equals("null") && !homeArrayList.get(i).getStatus().equals(status))
                tempList.set(i, null);

            else if(!type.equals("null") && !homeArrayList.get(i).getType().equals(type))
                tempList.set(i, null);

            else if(priceLow!=-1 && !(homeArrayList.get(i).getPrice()>=priceLow))
                tempList.set(i, null);

            else if(priceHigh!=-1 && !(homeArrayList.get(i).getPrice()<=priceHigh))
                tempList.set(i, null);
        }
        ArrayList<Home> returnList = new ArrayList<Home>();
        for(int i=0; i<tempList.size(); ++i) {
            if(tempList.get(i) != null)
                returnList.add(tempList.get(i));
        }
        return returnList;
    }

    /**
     * Generates houses in descending order according to their prices.
     * @param homeList list of houses.
     * @return houses sorted in descending order according to their prices.
     */
    public Stack<Home> generateDescendingOrderedStack(ArrayList<Home> homeList) {
        //Collections.sort(homeList);
        int[] homes = new int[homeList.size()];
        for(int i=0; i<homeList.size(); ++i)
            homes[i]=homeList.get(i).getPrice();

        sort(homes);

        for (int i=0;i<homeList.size();i++) {
            System.out.println("price: " + homes[i]);
        }
        for(int i=0; i<homeList.size(); ++i) {
            int search=homes[i];
            for(int j=0; j<homeList.size(); ++j) {
                if(homeList.get(j).getPrice() == search) {
                    home.add(homeList.get(j));
                }
            }
        }
        return home;
    }

    /**
     * Displays the houses in descending order according to their prices.
     * @param home Stack of houses.
     */
    public void displayStack(Stack<Home> home) {
        System.out.println("Prices: " + home.size());
        while(!home.isEmpty())
            System.out.println(home.pop().getPrice() + "->");
    }

    /**
     * Generates houses in ascending order according to their prices.
     * @param homeList list of houses.
     * @return houses sorted in ascending order according to their prices.
     */
    public PriorityQueue<Home> generateAscendingOrderedQueue(ArrayList<Home> homeList) {

        PriorityQueue<Home> sorted= new PriorityQueue<Home>();
        for (int i=0;i<homeList.size();i++){
            homeQueue.add(homeList.get(i));
        }
        while(homeQueue.size()!=0){
            Home temp = homeQueue.remove();
            sorted.offer(temp);

        }
        while(sorted.size()!=0)
        {
            homeQueue.offer(sorted.remove());
        }

        return homeQueue;
    }

    /**
     * Displays the houses in descending order according to their prices.
     * @param homeQueue Queue of houses.
     */
    public void displayPriorityQueue(PriorityQueue<Home> homeQueue) {
        System.out.println("Prices: " + homeQueue.size());
        while(!homeQueue.isEmpty())
            System.out.println("->"+homeQueue.poll().getPrice());

    }

    /**
     * Generates the houses according to their areas.
     * @param homeArrayList list of houses.
     * @return binary search tree.
     */
    public BinarySearchTree<Integer> generateBSTforArea(ArrayList<Home> homeArrayList) {

        for(int i=0; i<homeArrayList.size(); ++i) {
            areaOfHomes.insert(homeArrayList.get(i).getArea());
        }
        return areaOfHomes;
    }

    /**
     * Generates houses in descending order according to their areas. Traverses inorderly.
     * @return arraylist of houses in descending order.
     */
    public ArrayList<Integer> generateDescendingArea() {

        Node temp = areaOfHomes.root;
        helperDescending(temp);
        return descendingArea;
    }


    /**
     * Helper method for generateDescendingArea method.
     * @param root root node.
     */
    public void helperDescending(Node root) {

        if (root == null)
            return;

        helperDescending(root.left);
        descendingArea.add(root.data);
        helperDescending(root.right);
    }

    /**
     * Getter.
     * @return descending area.
     */
    public ArrayList<Integer> getDescendingArea() { return descendingArea; }


    /**
     * Generates houses in ascending order according to their areas.
     * @return ascending area.
     */
    public ArrayList<Integer> generateAscendingArea() {

        Node temp = areaOfHomes.root;
        helperAscending(temp);
        return ascendingArea;
    }

    /**
     * Helper method for generateAscendingArea method.
     * @param root root node.
     */
    public void helperAscending(Node root) {

        if (root == null)
            return;

        helperAscending(root.right);
        ascendingArea.add(root.data);
        helperAscending(root.left);
    }

    /**
     * Getter.
     * @return ascending area.
     */
    public ArrayList<Integer> getAscendingArea() { return ascendingArea; }


    /**
     * Generates all houses in given area.
     * @param homeArrayList list of houses.
     * @param neigh district in which the houses are to be generated.
     * @return all the houses in the desired area.
     */
    public Map<String,Integer> generateTotalHomesInPlace(ArrayList<Home> homeArrayList, String neigh) {

        int found = -1;

        for(int i = 0; i < homeArrayList.size(); ++i) {

            String tempNeighborhood = homeArrayList.get(i).getAddress().getNeighborhood();
            for (int j = 0; j < homesInPlace.size(); ++j) {
                if(homesInPlace.keySet().toArray()[j].equals(tempNeighborhood)) {
                    found = 1;
                }
            }
            if(found == -1) {
                homesInPlace.put(tempNeighborhood,1);
            }
            else if(found == 1) {
                homesInPlace.put(tempNeighborhood,homesInPlace.get(tempNeighborhood) + 1);
            }
            found = -1;
        }
        System.out.println("Total homes in " + neigh + ": " + homesInPlace.get(neigh));
        return homesInPlace;
    }

    /**
     * Compare method for comparator. Compares two house objects according to their price.
     * @param o1 first house object to be compared.
     * @param o2 second house object to be compared.
     * @return 0 if equal, -1 if first one is less, 1 if first one is more.
     */
    @Override
    public int compare(Home o1, Home o2) {
        if (o1.getPrice()<o2.getPrice())
            return -1;
        else if (o1.getPrice()==o2.getPrice())
            return 0;

        return 1;
    }


    /**
     * Removes house from the list.
     * @param adNumber advertisement number.
     * @param owner owner of the house.
     * @return final house object.
     */
    public Home removeHome(int adNumber, SystemUserAbstract owner)
    {
        for (int i=0; i<homeArrayList.size(); i++) {


            if (adNumber == homeArrayList.get(i).getAdNumber()) {
                if(owner.getUserType().equals("Admin") ||
                        homeArrayList.get(i).getOwner().getEmail().equals(owner.getEmail()))
                {
                    Home home = homeArrayList.remove(i);
                    FileOperations fileOperations = new FileOperations();
                    fileOperations.writeHomeList(homeArrayList);

                    return home;
                }
            }
        }

        return null;
    }

    /**
     * Updates graph.
     */
    private void updateGraph() {
        graph = new Graph();
        for (Home h : getHomeArrayList()) {
            graph.add(h);
        }
    }

    /**
     * Generates advertisement number.
     * @return advertisement number.
     */
    public int generateAdNumber(){
        return homeArrayList.get(homeArrayList.size()-1).getAdNumber() + 1;
    }

    public ArrayList<Home> addHome(Home home){
        homeArrayList.add(home);
        FileOperations fileOperations = new FileOperations();
        fileOperations.writeHomeList(homeArrayList);
        for (Home temp: homeArrayList
             ) {
            System.out.println(temp);
        }
        graph.add(home);
        return homeArrayList;
    }

    /**
     * Adds owner of the house.
     * @param member user.
     * @return new list of houses.
     */
    public ArrayList<Home> ownerHome(SystemUserAbstract member){
        ArrayList ownerList = new ArrayList();
        for (Home home: homeArrayList) {
            System.out.println(home.getOwner().getEmail());
            if (member.getEmail().compareTo(home.getOwner().getEmail()) == 0){
                ownerList.add(home);
            }
        }
        return  ownerList;
    }
    public static < T extends Comparable < T >> int[] sort(int[] table) {
        // A table with one element is sorted already.
        if (table.length > 1) {
            // Split table into halves.
            int halfSize = table.length / 2;
            int[] leftTable = new int[halfSize];
            int[] rightTable = new int[table.length - halfSize];
            System.arraycopy(table, 0, leftTable, 0, halfSize);
            System.arraycopy(table, halfSize, rightTable, 0,
                    table.length - halfSize);

            // Sort the halves.
            sort(leftTable);
            sort(rightTable);

            // Merge the halves.
            merge(table, leftTable, rightTable);

        }
        else
            return table;

        return table;
    }

    /** Merge two sequences.
     pre: leftSequence and rightSequence are sorted.
     post: outputSequence is the merged result and is sorted.
     * @param outputSequence The destination
     * @param leftSequence The left input
     * @param rightSequence The right input
     * @param <T> generic
     */
    private static < T extends Comparable < T >> void merge(int[] outputSequence,
                                                            int[] leftSequence,
                                                            int[] rightSequence) {
        int i = 0; // Index into the left input sequence.
        int j = 0; // Index into the right input sequence.
        int k = 0; // Index into the output sequence.

        // While there is data in both input sequences
        while (i < leftSequence.length && j < rightSequence.length) {
            // Find the smaller and
            // insert it into the output sequence.
            if (leftSequence[i] < (rightSequence[j])) {
                outputSequence[k++] = leftSequence[i++];
            }
            else {
                outputSequence[k++] = rightSequence[j++];
            }
        }
        // assert: one of the sequences has more items to copy.
        // Copy remaining input from left sequence into the output.
        while (i < leftSequence.length) {
            outputSequence[k++] = leftSequence[i++];
        }
        // Copy remaining input from right sequence into output.
        while (j < rightSequence.length) {
            outputSequence[k++] = rightSequence[j++];
        }
    }
}