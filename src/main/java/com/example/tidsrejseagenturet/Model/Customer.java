package com.example.tidsrejseagenturet.Model;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String password; // Nyt felt til login

    // Constructor med password - bruges fx ved oprettelse
    public Customer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Alternativ constructor uden id (f.eks. før id'et er genereret)
    public Customer(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters og Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        // Bemærk: Du behøver typisk ikke at vise password i en toString()-metode
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}