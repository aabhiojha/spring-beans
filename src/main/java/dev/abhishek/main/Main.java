package dev.abhishek.main;


import dev.abhishek.beans.Vehicle;
import dev.abhishek.config.ProjectConfig;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Vehicle volkswagen = new Vehicle();
        volkswagen.setName("Volkswagen");
        Supplier<Vehicle> volkswagenSupplier = () -> volkswagen;

        Supplier<Vehicle> audiSupplier = () -> {
            Vehicle audi = new Vehicle();
            audi.setName("Audi");
            return audi;
        };

        Random random = new Random();
        int randomNumber = random.nextInt(10);
        System.out.println("RandomNumber : " + randomNumber);

        if (randomNumber % 2 == 0) {
            context.registerBean("volkswagen",
                    Vehicle.class,
                    volkswagenSupplier);
        } else {
            context.registerBean("audi", Vehicle.class, audiSupplier);
        }

        Vehicle volkVehicle = null;
        Vehicle audiVehicle = null;

        try {
            audiVehicle = context.getBean("audi", Vehicle.class);
        } catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {
            System.out.println("error while creating Audi Vehicle bean");
        }

        try {
            volkVehicle = context.getBean("volkswagen", Vehicle.class);
        } catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {
            System.out.println("error while creating Volkswagen Vehicle bean");
        }

        if (volkVehicle != null) {
            System.out.println("programming vehicle name from spring context is: " + volkVehicle.getName());
        } else {
            System.out.println("programming vehicle name from spring context is: " + audiVehicle.getName());
        }

    }
}
