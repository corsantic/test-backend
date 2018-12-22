package com.etiya.project.event;

import com.etiya.project.domain.Maintenance;

public class MaintenanceMail implements Runnable{

   private Maintenance maintenance;


    public MaintenanceMail(Maintenance maintenance) {
        this.maintenance = maintenance;
    }

    @Override
    public void run() {

            System.out.println("Sending Mail to " + maintenance.getTool().getUser().getFirstName());

    }
}