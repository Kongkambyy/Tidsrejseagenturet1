package com.example.tidsrejseagenturet.Model;

public class Customer {
    private Integer id;  // Changed to Integer to allow null for new customers
    private String name;
    private String email;

    // Constructor for new customers (before database insertion)
    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Constructor for existing customers (after database retrieval)
    public Customer(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters
    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    // Setters
    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Kunde [ID=" + id + ", navn=" + name + ", email=" + email + "]";
    }
}
