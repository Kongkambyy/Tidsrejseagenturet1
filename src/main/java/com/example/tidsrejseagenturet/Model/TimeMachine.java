package com.example.tidsrejseagenturet.Model;

public class TimeMachine {
    private Integer id;
    private String name;
    private int capacity;
    private boolean available;

    public TimeMachine(String name, int capacity, boolean available) {
        this.name = name;
        this.capacity = capacity;
        this.available = available;
    }

    public TimeMachine(Integer id, String name, int capacity, boolean available) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.available = available;
    }

    // Getters
    public Integer getId() { return id; }
    public String getName() { return name; }
    public int getCapacity() { return capacity; }
    public boolean isAvailable() { return available; }

    // Setters
    public void setId(Integer id) {this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return "Tidsrejsemaskine [ID=" + id + ", navn=" + name +
                ", kapacitet=" + capacity + ", status=" + (available ? "Ledig" : "I brug") + "]";
    }
}