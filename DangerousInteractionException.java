package com.medical.exceptions;

public class DangerousInteractionException extends Exception {

    private String drug1;
    private String drug2;
    private String severity;
    private String alternative;

    public DangerousInteractionException(String drug1, String drug2, String severity, String alternative) {
        super("DANGEROUS INTERACTION DETECTED between " + drug1 + " and " + drug2 + ". Severity: " + severity);
        this.drug1 = drug1;
        this.drug2 = drug2;
        this.severity = severity;
        this.alternative = alternative;
    }

    public String getDrug1() {
        return drug1;
    }

    public String getDrug2() {
        return drug2;
    }

    public String getSeverity() {
        return severity;
    }

    public String getAlternative() {
        return alternative;
    }
}