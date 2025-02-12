package com.example.tidsrejseagenturet.Model;

public class TimeMachine {
    private Integer id;
    private String name;
    private int capacity;
    private boolean status;

    public TimeMachine(String name, int capacity, boolean status) {
        this.name = name;
        this.capacity = capacity;
        this.status = status;
    }

    public TimeMachine(Integer id, String name, int capacity, boolean status) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.status = status;
    }

    // Getters
    public Integer getId() { return id; }
    public String getName() { return name; }
    public int getCapacity() { return capacity; }
    public boolean getStatus() { return status; }

    // Setters
    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public void setStatus(boolean status) { this.status = status; }

    @Override
    public String toString() {
        return "Tidsrejsemaskine [ID=" + id + ", navn=" + name +
                ", kapacitet=" + capacity + ", status=" + (status ? "Ledig" : "I brug") + "]";
    }
}