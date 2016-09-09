package com.pieceofquality.addressbook.model;

public class ContactData {

    private String firstName;
    private String lastName;
    private String homenumber;
    private String mobilenumber;
    private String worknumber;
    private String group;
    private int id = Integer.MAX_VALUE;
    private String allPhones;
    private String allEmails;
    private String email1;
    private String email2;
    private String email3;
    private String address;
    private String contactAddress;

    public String getContactAddress(){
        return contactAddress;
    }

    public ContactData withContactAddress(String contactAddress){
        this.contactAddress = contactAddress;
        return this;
    }

    public int getId() {
        return id;
    }

    public ContactData withId (int id) {
        this.id = id;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withHomePhone (String homenumber) {
        this.homenumber = homenumber;
        return this;
    }

    public ContactData withMobilePhone(String mobilenumber) {
        this.mobilenumber = mobilenumber;
        return this;
    }

    public ContactData withWorkPhone(String worknumber) {
        this.worknumber = worknumber;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getAddress() {
        return address;
    }

    public ContactData withEmail1(String email1) {
        this.email1 = email1;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public String getFirstName(){ return firstName;}

    public String getLastName(){ return lastName;}

    public String getGroup() {
        return group;
    }

    public String getHomePhone() {
        return homenumber;
    }

    public String getMobilePhone() {
        return mobilenumber;
    }

    public String getWorkPhone() {
        return worknumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
