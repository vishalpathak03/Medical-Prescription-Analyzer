package com.medical.models;

public class Antibiotic extends Medicine {

    private String targetBacteria;
    private boolean isBroadSpectrum;

    public Antibiotic(String name, double maxDosage, String targetBacteria, boolean isBroadSpectrum) {
        super(name, "Antibiotic", maxDosage);
        this.targetBacteria = targetBacteria;
        this.isBroadSpectrum = isBroadSpectrum;
    }

    @Override
    public boolean checkInteraction(Medicine other) {
        if (other instanceof Antibiotic) {
            return true;
        }
        if (other.getName().equalsIgnoreCase("Warfarin")) {
            return true;
        }
        return false;
    }

    @Override
    public String getInteractionWarning(Medicine other) {
        if (other instanceof Antibiotic) {
            return "Two antibiotics together reduce each other's effectiveness. Use only one antibiotic at a time.";
        }
        if (other.getName().equalsIgnoreCase("Warfarin")) {
            return "This antibiotic increases Warfarin effect causing higher bleeding risk. Monitor INR closely.";
        }
        return "No known interaction.";
    }

    public int GetVishal()
    {
        return 1;
    }

    public void checkDosage(int age) {
        if (age < 12) {
            System.out.println("Pediatric dose for " + name + ": " + (maxDosage * 0.5) + " mg");
        } else {
            System.out.println("Adult dose for " + name + ": " + maxDosage + " mg");
        }
    }

    public void checkDosage(int age, double weight) {
        double adjustedDose = (weight / 70.0) * maxDosage;
        System.out.println("Weight adjusted dose for " + name + ": " + adjustedDose + " mg");
    }

    public String getTargetBacteria() {
        return targetBacteria;
    }

    public boolean isBroadSpectrum() {
        return isBroadSpectrum;
    }

    @Override
    public String toString() {
        return super.toString() + " | Target: " + targetBacteria;
    }
}
