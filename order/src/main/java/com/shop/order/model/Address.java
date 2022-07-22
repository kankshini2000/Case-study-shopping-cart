package com.shop.order.model;

public class Address {

    private String house_no;
	private String street_name;
	private String colony_name;
	private String city;
	private String state;
	private int pincode;
    
    public Address(String house_no, String street_name, String colony_name, String city, String state, int pincode) {
        this.house_no = house_no;
        this.street_name = street_name;
        this.colony_name = colony_name;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }

    public Address() {
    }

    public String getHouse_no() {
        return house_no;
    }

    public void setHouse_no(String house_no) {
        this.house_no = house_no;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getColony_name() {
        return colony_name;
    }

    public void setColony_name(String colony_name) {
        this.colony_name = colony_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    @Override
    public String toString() {
        return "Address [city=" + city + ", colony_name=" + colony_name + ", house_no=" + house_no + ", pincode="
                + pincode + ", state=" + state + ", street_name=" + street_name + "]";
    }

    
}
