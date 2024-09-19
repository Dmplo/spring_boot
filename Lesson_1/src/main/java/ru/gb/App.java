package ru.gb;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class App {

    public static void main(String[] args) {

        List<String> names = List.of("Will", "Jim", "Arnold");
        List<String> lastNames = List.of("Smith", "Carry", "Schwarzenegger");

        Person personOne = new Person(getRandom(names), getRandom(lastNames), ThreadLocalRandom.current().nextInt(20, 50));
        Person personTwo = new Person(getRandom(names), getRandom(lastNames), ThreadLocalRandom.current().nextInt(20, 50));
        Person personThree = new Person(getRandom(names), getRandom(lastNames), ThreadLocalRandom.current().nextInt(20, 50));

        Gson gsonOne = new Gson();
        Gson gsonTwo = new GsonBuilder().setPrettyPrinting().create();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting();
        Gson gsonThree = gsonBuilder.create();

        // Serialize
        String personJsonOne = gsonOne.toJson(personOne);
        String personJsonTwo = gsonTwo.toJson(personTwo);
        String personJsonThree = gsonThree.toJson(personThree);

        // Deserialize
        Person jsonPersonOne = gsonOne.fromJson(personJsonOne, Person.class);

        System.out.printf("Simple: %s\n", personJsonOne);
        System.out.printf("Pretty: %s\n", personJsonTwo);
        System.out.printf("Expose field: %s\n", personJsonThree);

        System.out.printf("personOne equals jsonPersonOne: %s\n", personOne.equals(jsonPersonOne));

        System.out.println(jsonPersonOne);
    }


    public static <T> T getRandom(List<? extends T> items) {
        int randomIndex = ThreadLocalRandom.current().nextInt(0, items.size());
        return items.get(randomIndex);
    }

}
