package com.medical.models;

public class Antiviral extends Medicine {

    private String targetVirus;
    private boolean requiresMonitoring;

    public Antiviral(String name, double maxDosage, String targetVirus, boolean requiresMonitoring) {
        super(name, "Antiviral", maxDosage);
        this.targetVirus = targetVirus;
        this.requiresMonitoring = requiresMonitoring;
    }

    @Override
    public boolean checkInteraction(Medicine other) {
        if (other instanceof Antiviral) {
            return true;
        }
        if (other.getName().equalsIgnoreCase("Warfarin")) {
            return true;
        }
        return false;
    }

    @Override
    public String getInteractionWarning(Medicine other) {
        if (other instanceof Antiviral) {
            return "Two antiviral medications together may cause increased toxicity. Use under strict supervision.";
        }
        if (other.getName().equalsIgnoreCase("Warfarin")) {
            return "This antiviral alters Warfarin metabolism causing unpredictable bleeding. Monitor INR levels.";
        }
        return "No known interaction.";
    }

    public boolean isRequiresMonitoring() {
        return requiresMonitoring;
    }

    public String getTargetVirus() {
        return targetVirus;
    }

    @Override
    public String toString() {
        return super.toString() + " | Target Virus: " + targetVirus;
    }
}