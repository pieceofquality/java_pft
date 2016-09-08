package com.pieceofquality.addressbook.model;

public class ContactData {

    private String firstName;
    private String lastName;
    private String homenumber;
    private String mobilenumber;
    private String worknumber;
    private String group;
    private int id = Integer.MAX_VALUE;

    public int getId() {
        return id;
    }

    public ContactData withId (int id) {
        this.id = id;
        return this;
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
