package com.example.tidsrejseagenturet.Model;

public class Guide {
    private int id;
    private String name;
    private String specialty;

    public Guide(int id, String name, String specialty) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
    }

    // Getters & Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getSpecialty() { return specialty; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
}
