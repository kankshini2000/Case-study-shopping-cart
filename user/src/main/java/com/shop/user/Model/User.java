package com.shop.user.Model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
public class User {

    @NotBlank(message = "username is mandatory")
	private String userName;
    @NotBlank(message = "fullName is mandatory")
	private String fullName;

    @Id
    @NotBlank(message = "email is mandatory")
	private String email;

    
    @NotBlank(message = "gender is mandatory")
	private String gender;
    @NotBlank(message = "dob is mandatory")
	private String dob;
    @NotBlank(message = "role is mandatory")
	private String role;
    
    @NotBlank(message = "Mobile_no is mandatory")
	private String mobile_no;
    @NotBlank(message = "password is mandatory")
	private String password;
	private Address address;
    
    public User(){

    }
    
    public User(String userName, String fullName, String email, String gender, String dob, String role, String mobile_no,
            String password, Address address) {
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.role = role;
        this.mobile_no = mobile_no;
        this.password = password;
        this.address = address;
    }

    /* @Override
    public String toString() {
        return "User [address=" + address + ", dob=" + dob + ", email=" + email + ", fullName=" + fullName + ", gender="
                + gender + ", mobile_no=" + mobile_no + ", password=" + password + ", role=" + role + ", userName="
                + userName + "]";
    } */

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }



    
}
