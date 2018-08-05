package com.bootstrappers.backend;

import java.util.Comparator;

class MyComparator implements Comparator<Home> {

    /**
     * Compare method for comparator. Compares houses according to their prices.
     * @param x first house object to be compared.
     * @param y second house object to be compared.
     * @return 0 if houses are equal, -1 if first is less, 1 if first is more.
     */
    public int compare(Home x, Home y) {
        if (x.getPrice() < y.getPrice()) {
            return -1;
        }
        if (x.getPrice() > y.getPrice()) {
            return 1;
        }
        return 0;
    }
}