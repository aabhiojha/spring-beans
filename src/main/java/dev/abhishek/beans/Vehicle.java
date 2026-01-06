package dev.abhishek.beans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class Vehicle {

    private String name;

//    This would not work
//    as all the parameters should be resolved from application context

//    public Vehicle(String name) {
//        System.out.println("this is printed from constructor");
//        this.name = name;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PostConstruct
    public void initialize(){
        this.name = "Honda";
        System.out.println("this is printed from initialize method");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Destroying Vehicle Bean");
    }

    public void printHello() {
        System.out.println(
                "Printing Hello from Component Vehicle Bean");
    }
}
