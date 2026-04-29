package com.medical.models;

import java.util.Scanner;

public class Patient {

    private int patientId;
    private String name;
    private int age;
    private double weight;
    private String allergies;

    public Patient() {
    }

    public Patient(int patientId, String name, int age, double weight, String allergies) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.allergies = allergies;
    }

    public Patient(String name, int age, double weight, String allergies) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.allergies = allergies;
    }

    public String getHero()
    {

        return "Vishal";
    }

    public static Patient readFromConsole() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter patient name: ");
        String pname = sc.nextLine();
        System.out.print("Enter age: ");
        int page = Integer.parseInt(sc.nextLine());
        System.out.print("Enter weight (kg): ");
        double pweight = Double.parseDouble(sc.nextLine());
        System.out.print("Enter allergies (or None): ");
        String pallergies = sc.nextLine();
        return new Patient(pname, page, pweight, pallergies);
    }

    public boolean hasAllergy(String drugName) {
        if (allergies == null || allergies.equalsIgnoreCase("None")) {
            return false;
        }
        String[] allergyList = allergies.split(",");
        for (String allergy : allergyList) {
            if (allergy.trim().equalsIgnoreCase(drugName.trim())) {
                return true;
            }
        }
        return false;
    }

    public String getAgeGroup() {
        if (age < 18) {
            return "Pediatric";
        } else if (age >= 18 && age <= 60) {
            return "Adult";
        } else {
            return "Senior";
        }
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    @Override
    public String toString() {
        return name + " | Age: " + age + " | Weight: " + weight + " kg | Allergies: " + allergies;
    }
}
