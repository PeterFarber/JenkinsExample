package com.peterfarber.pojo;

public class User {


    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private int position;
    private int department;
    private int supervisor_ID;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", position='" + position + '\'' +
                ", department='" + department + '\'' +
                ", supervisor_ID=" + supervisor_ID +
                '}';
    }

    public User(){

    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(int id, String firstName, String lastName, String username, String password, String email, int position, int department, int supervisor_id) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.position = position;
        this.department = department;
        supervisor_ID = supervisor_id;
    }

    public int getSupervisor_ID() {
        return supervisor_ID;
    }

    public void setSupervisor_ID(int supervisor_ID) {
        this.supervisor_ID = supervisor_ID;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
