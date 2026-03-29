package com.medical.checker;

import java.awt.Color;
import java.util.ArrayList;

public class SeverityClassifier {

    public static final String MILD = "MILD";
    public static final String MODERATE = "MODERATE";
    public static final String SEVERE = "SEVERE";
    public static final String LIFE_THREATENING = "LIFE-THREATENING";

    public String classifySeverity(String severity) {
        switch (severity.toUpperCase()) {
            case "MILD":
                return MILD;
            case "MODERATE":
                return MODERATE;
            case "SEVERE":
                return SEVERE;
            case "LIFE-THREATENING":
                return LIFE_THREATENING;
            default:
                return "UNKNOWN";
        }
    }

    public Color getSeverityColor(String severity) {
        switch (severity.toUpperCase()) {
            case "MILD":
                return new Color(255, 255, 153);
            case "MODERATE":
                return new Color(255, 178, 102);
            case "SEVERE":
                return new Color(255, 77, 77);
            case "LIFE-THREATENING":
                return new Color(180, 0, 0);
            default:
                return Color.WHITE;
        }
    }

    public String getSeverityMessage(String severity) {
        switch (severity.toUpperCase()) {
            case "MILD":
                return "Minor interaction detected. Monitor patient for side effects.";
            case "MODERATE":
                return "Moderate interaction detected. Consider dosage adjustment or alternative.";
            case "SEVERE":
                return "Severe interaction detected. Strongly consider changing medication.";
            case "LIFE-THREATENING":
                return "LIFE-THREATENING interaction. Do NOT prescribe together under any circumstances.";
            default:
                return "Interaction severity unknown.";
        }
    }

    public int getSeverityRank(String severity) {
        switch (severity.toUpperCase()) {
            case "MILD": return 1;
            case "MODERATE": return 2;
            case "SEVERE": return 3;
            case "LIFE-THREATENING": return 4;
            default: return 0;
        }
    }

    public String getHighestSeverity(ArrayList<String[]> interactions) {
        int highest = 0;
        String highestSeverity = "NONE";
        for (String[] interaction : interactions) {
            int rank = getSeverityRank(interaction[2]);
            if (rank > highest) {
                highest = rank;
                highestSeverity = interaction[2];
            }
        }
        return highestSeverity;
    }
}
