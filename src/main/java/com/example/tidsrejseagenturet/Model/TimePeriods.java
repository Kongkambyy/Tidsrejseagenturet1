package com.example.tidsrejseagenturet.Model;

public class TimePeriods {
    private Integer id;
    private String name;
    private String description;

    public TimePeriods(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public TimePeriods(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Getters
    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }

    // Setters
    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "Tidsperiode [ID=" + id + ", navn=" + name + ", description=" + description + "]";
    }
}