package com.etiya.project;

import com.etiya.project.domain.Maintenance;
import com.etiya.project.event.MaintenanceMail;
import com.etiya.project.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ProjectApplication {

    private final MaintenanceService maintenanceService;

    public ProjectApplication(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }


    /**
     *
     * Sending Mail (using console)
     *
     * @param executor
     * @return
     */
    @Bean
    public CommandLineRunner schedulingRunner(@Qualifier("taskExecutor") TaskExecutor executor) {
        return new CommandLineRunner() {
            public void run(String... args) throws Exception {
                Set<Maintenance> maintenanceSet = new HashSet<>();
                maintenanceService.findAll().stream()
                        .forEach(maintenanceSet::add);

                maintenanceSet.stream()
                        .forEach(maintenance -> {
                            if(maintenance.getDate().equals(LocalDate.now(ZoneId.systemDefault())))
                            executor.execute(new MaintenanceMail(maintenance));
                        });


            }
        };
    }
}

