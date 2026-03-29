package com.medical.models;

public class Painkiller extends Medicine {

    private boolean isOpioid;
    private String painType;

    public Painkiller(String name, double maxDosage, boolean isOpioid, String painType) {
        super(name, "Painkiller", maxDosage);
        this.isOpioid = isOpioid;
        this.painType = painType;
    }

    @Override
    public boolean checkInteraction(Medicine other) {
        if (isOpioid && other instanceof Painkiller) {
            Painkiller otherPainkiller = (Painkiller) other;
            if (otherPainkiller.isOpioid()) {
                return true;
            }
        }
        if (other instanceof Painkiller) {
            return true;
        }
        if (other.getName().equalsIgnoreCase("Warfarin") && name.equalsIgnoreCase("Aspirin")) {
            return true;
        }
        return false;
    }

    @Override
    public String getInteractionWarning(Medicine other) {
        if (isOpioid && other instanceof Painkiller) {
            Painkiller otherPainkiller = (Painkiller) other;
            if (otherPainkiller.isOpioid()) {
                return "Two opioid painkillers together cause life threatening respiratory depression. Avoid completely.";
            }
        }
        if (other instanceof Painkiller) {
            return "Two painkillers together increase risk of stomach ulcers and kidney damage. Use only one.";
        }
        if (other.getName().equalsIgnoreCase("Warfarin") && name.equalsIgnoreCase("Aspirin")) {
            return "Aspirin and Warfarin together cause severe internal bleeding risk. Replace Aspirin with Paracetamol.";
        }
        return "No known interaction.";
    }

    public void checkDosage(int age) {
        if (age > 60) {
            System.out.println("Senior reduced dose for " + name + ": " + (maxDosage * 0.75) + " mg");
        } else {
            System.out.println("Standard dose for " + name + ": " + maxDosage + " mg");
        }
    }

    public void checkDosage(int age, double weight) {
        double adjustedDose = (weight / 70.0) * maxDosage;
        if (age > 60) {
            adjustedDose = adjustedDose * 0.75;
        }
        System.out.println("Adjusted dose for " + name + ": " + adjustedDose + " mg");
    }

    public boolean isOpioid() {
        return isOpioid;
    }

    public String getPainType() {
        return painType;
    }

    @Override
    public String toString() {
        return super.toString() + " | Opioid: " + isOpioid + " | Pain Type: " + painType;
    }
}