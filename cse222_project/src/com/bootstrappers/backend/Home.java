package com.bootstrappers.backend;

public class Home implements Comparable<Home>{

    private String countOfRoom; // Count of room (2+1, 3+1 etc.)

    private String status; // For rent or for sale

    private String type; // Müstakil, Apartman, Villa

    private Address address; // com.bootstrappers.backend.Address detail

    private int adNumber;

    private SystemUserAbstract owner;

    private int price;

    private int area; // evin metrekaresi

    private int distance;

    /*              HATALI
    public Home(String countOfRoom, String status, String type, Address address, int adNumber, Member owner, int area, int price) {
        this.countOfRoom = countOfRoom;
        this.status = status;
        this.type = type;
        this.address = address;
        this.adNumber = adNumber;
        this.owner = owner;
        this.area = area;
        this.price=price;
    }*/



    /**
     * Constructor.
     * @param countOfRoom number of rooms in the house.
     * @param status status of the house; for rent or for sale.
     * @param type type of the houses; apartment, house, residence, etc.
     * @param address address of the house.
     * @param adNumber advertisement number of the house.
     * @param owner owner of the house.
     * @param area m^2 of the house.
     * @param price price of the house.
     * @param distance distance to the center of district.
     */
    public Home(String countOfRoom, String status, String type, Address address, int adNumber,SystemUserAbstract owner, int area, int price,int distance) {
        this.countOfRoom = countOfRoom;
        this.status = status;
        this.type = type;
        this.address = address;
        this.adNumber = adNumber;
        this.owner = owner;
        this.area = area;
        this.price=price;
        this.distance = distance;
    }


    /**
     * To string method.
     * @return string.
     */
    @Override
    public String toString() {
        return  "countOfRoom='" + countOfRoom + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", address=" + address +
                ", adNumber=" + adNumber +
                ", owner=" + owner +
                ", price=" + price +
                ", area=" + area +
                ", distance=" + distance;
    }

    /**
     * Getter.
     * @return number of the rooms of the house.
     */
    public String getCountOfRoom() {
        return countOfRoom;
    }

    /**
     * Getter.
     * @return status of the house.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Getter.
     * @return type of the house.
     */
    public String getType() {
        return type;
    }

    /**
     * Getter.
     * @return address of the house.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Getter.
     * @return number of the advertisement.
     */
    public int  getAdNumber() {
        return adNumber;
    }

    /**
     * Getter.
     * @return owner of the house.
     */
    public SystemUserAbstract getOwner() {
        return owner;
    }

    /**
     * Getter.
     * @return area of the house.
     */
    public int getArea() { return area; }

    /**
     * Getter.
     * @return price of the house.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Getter.
     * @return distance to the district's center.
     */
    public int getDistance() {
        return distance;
    }
    public void setDistance(int distance){
        this.distance = distance;
    }

    /**
     * Compare to method. Compares house according to the price.
     * @param temp house to be compared.
     * @return 0 if equal, -1 if less, 1 if more.
     */
    @Override
    public int compareTo(Home temp) {
        if (this.price<temp.price)
            return -1;

        else if (this.price>temp.price)
            return 1;


        return 0;
    }

    /**
     * Compares two houses according to their distances to the district's center.
     * @param temp house to be compared.
     * @return 0 if equal, -1 if less, 1 if more.
     */
    public int compareToWithDistance(Home temp) {
        if (this.distance<temp.distance)
            return -1;

        else if (this.distance>temp.distance)
            return 1;


        return 0;
    }


}
