package com.bootstrappers.backend;

public class Address {

    private String province;

    private String country; /* gerek yok */

    private String neighborhood; /* semt */

    /**
     * Constructor.
     * @param province city
     * @param country country
     * @param neighborhood district
     */
    public Address(String province, String country, String neighborhood) {
        this.province = province;
        this.country = country;
        this.neighborhood = neighborhood;
    }

    /**
     * Getter.
     * @return province.
     */
    public String getProvince() {
        return province;
    }

    /**
     * Getter.
     * @return country.
     */
    public String getCountry() {
        return country;
    }


    /**
     * Getter.
     * @return neighborhood.
     */
    public String getNeighborhood() {
        return neighborhood;
    }


    /**
     * To string method.
     * @return string.
     */
    @Override
    public String toString() {
        return neighborhood + "/" + province;
    }
}
