package com.example.tidsrejseagenturet.Controller;

import com.example.tidsrejseagenturet.Model.TimeMachine;
import com.example.tidsrejseagenturet.Model.TimeMachineDatabaseHandler;

import java.util.List;

public class TimeMachineController {
    private final TimeMachineDatabaseHandler dbHandler;

    public TimeMachineController() {
        this.dbHandler = new TimeMachineDatabaseHandler();
    }

    public TimeMachine createTimeMachine(String name, int capacity, boolean available) {
        return dbHandler.createTimeMachineInDB(name, capacity, available);
    }

    public boolean updateTimeMachine(int id, String name, int capacity, boolean available) {
        return dbHandler.updateTimeMachineInDB(id, name, capacity, available);
    }

    public List<TimeMachine> getAllTimeMachines() {
        return dbHandler.getAllTimeMachinesFromDB();
    }

    public TimeMachine getTimeMachine(int id) {
        return dbHandler.getTimeMachineFromDB(id);
    }

    public boolean updateAvailability(int id, boolean available) {
        return dbHandler.updateAvailabilityInDB(id, available);
    }
}