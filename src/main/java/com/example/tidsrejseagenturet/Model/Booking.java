package com.example.tidsrejseagenturet.Model;

public class Booking {
    private int id;
    private int customerId;
    private int timeMachineId;
    private int timePeriodId;
    private int guideId;

    public Booking(int id, int customerId, int timeMachineId, int timePeriodId, int guideId) {
        this.id = id;
        this.customerId = customerId;
        this.timeMachineId = timeMachineId;
        this.timePeriodId = timePeriodId;
        this.guideId = guideId;
    }

    // Getters
    public int getId() { return id; }
    public int getCustomerId() { return customerId; }
    public int getTimeMachineId() { return timeMachineId; }
    public int getTimePeriodId() { return timePeriodId; }
    public int getGuideId() { return guideId; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public void setTimeMachineId(int timeMachineId) { this.timeMachineId = timeMachineId; }
    public void setTimePeriodId(int timePeriodId) { this.timePeriodId = timePeriodId; }
    public void setGuideId(int guideId) { this.guideId = guideId; }

    @Override
    public String toString() {
        return "Booking [ID=" + id +
                ", KundeID=" + customerId +
                ", MaskineID=" + timeMachineId +
                ", PeriodeID=" + timePeriodId +
                ", GuideID=" + guideId + "]";
    }
}