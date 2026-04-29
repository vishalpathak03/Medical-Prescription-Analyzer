package com.medical.ui;

import com.medical.checker.InteractionChecker;
import com.medical.checker.SeverityClassifier;
import com.medical.database.DrugDAO;
import com.medical.database.PatientDAO;
import com.medical.exceptions.DangerousInteractionException;
import com.medical.exceptions.DrugNotFoundException;
import com.medical.exceptions.InvalidDosageException;
import com.medical.fileio.ReportWriter;
import com.medical.models.Drug;
import com.medical.models.Patient;
import com.medical.models.Prescription;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MainWindow extends JFrame {

    private JTextField patientNameField;
    private JTextField patientAgeField;
    private JTextField patientWeightField;
    private JTextField patientAllergyField;
    private JTextField doctorNameField;
    private JComboBox<String> drugComboBox;
    private JTextField dosageField;
    private JTextField durationField;
    private JTable drugTable;
    private DefaultTableModel tableModel;
    private JTextArea resultArea;
    private JLabel statusLabel;
    private JButton addDrugButton;
    private JButton checkButton;
    private JButton clearButton;
    private JButton saveReportButton;
    private JButton removeDrugButton;

    private DrugDAO drugDAO;
    // private PatientDAO patientDAO;
    private InteractionChecker checker;
    private SeverityClassifier classifier;
    private ReportWriter reportWriter;
    private ArrayList<Drug> allDrugs;

    private Prescription currentPrescription;
    private ArrayList<String[]> lastInteractionResults;

    public MainWindow() {
        drugDAO = new DrugDAO();
        // patientDAO = new PatientDAO();
        checker = new InteractionChecker();
        classifier = new SeverityClassifier();
        reportWriter = new ReportWriter("reports");
        lastInteractionResults = new ArrayList<>();
        allDrugs = drugDAO.getAllDrugs();
        buildUI();
        setupEventHandlers();
    }

    private void buildUI() {
        setTitle("Medical Prescription Analyzer & Drug Interaction Checker");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);
        add(buildTopPanel(), BorderLayout.NORTH);
        add(buildCenterPanel(), BorderLayout.CENTER);
        add(buildBottomPanel(), BorderLayout.SOUTH);
    }

    private JPanel buildTopPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        JPanel patientPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        patientPanel.setBorder(new TitledBorder("Patient Information"));
        patientNameField = new JTextField();
        patientAgeField = new JTextField();
        patientWeightField = new JTextField();
        patientAllergyField = new JTextField();
        patientPanel.add(new JLabel("Patient Name:"));
        patientPanel.add(patientNameField);
        patientPanel.add(new JLabel("Age:"));
        patientPanel.add(patientAgeField);
        patientPanel.add(new JLabel("Weight (kg):"));
        patientPanel.add(patientWeightField);
        patientPanel.add(new JLabel("Allergies:"));
        patientPanel.add(patientAllergyField);

        JPanel drugInputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        drugInputPanel.setBorder(new TitledBorder("Add Drug to Prescription"));
        String[] drugNames = new String[allDrugs.size()];
        for (int i = 0; i < allDrugs.size(); i++) {
            drugNames[i] = allDrugs.get(i).getName();
        }
        drugComboBox = new JComboBox<>(drugNames);
        dosageField = new JTextField();
        durationField = new JTextField();
        doctorNameField = new JTextField();
        drugInputPanel.add(new JLabel("Doctor Name:"));
        drugInputPanel.add(doctorNameField);
        drugInputPanel.add(new JLabel("Select Drug:"));
        drugInputPanel.add(drugComboBox);
        drugInputPanel.add(new JLabel("Dosage (mg):"));
        drugInputPanel.add(dosageField);
        drugInputPanel.add(new JLabel("Duration (days):"));
        drugInputPanel.add(durationField);

        panel.add(patientPanel);
        panel.add(drugInputPanel);
        return panel;
    }

    public boolean getwhy()
    {
        return true;
    }

    public boolean getwhy()
    {
        return true;
    }

    private JPanel buildCenterPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        String[] columns = {"Drug Name", "Category", "Dosage (mg)", "Duration (days)"};
        tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        drugTable = new JTable(tableModel);
        drugTable.setRowHeight(25);
        drugTable.getTableHeader().setBackground(new Color(70, 130, 180));
        drugTable.getTableHeader().setForeground(Color.WHITE);
        drugTable.setSelectionBackground(new Color(173, 216, 230));
        JScrollPane tableScrollPane = new JScrollPane(drugTable);
        tableScrollPane.setBorder(new TitledBorder("Prescription Drug List"));

        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBorder(new TitledBorder("Interaction Analysis Results"));
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        JScrollPane resultScrollPane = new JScrollPane(resultArea);
        resultPanel.add(resultScrollPane, BorderLayout.CENTER);

        panel.add(tableScrollPane);
        panel.add(resultPanel);
        return panel;
    }

    private JPanel buildBottomPanel() {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));

    addDrugButton = new JButton("Add Drug");
    checkButton = new JButton("Check Interactions");
    removeDrugButton = new JButton("Remove Selected");
    saveReportButton = new JButton("Save Report");
    clearButton = new JButton("Clear All");

    addDrugButton.setBackground(new Color(70, 130, 180));
    addDrugButton.setForeground(Color.WHITE);
    addDrugButton.setOpaque(true);
    addDrugButton.setBorderPainted(false);
    addDrugButton.setFocusPainted(false);

    checkButton.setBackground(new Color(34, 139, 34));
    checkButton.setForeground(Color.WHITE);
    checkButton.setOpaque(true);
    checkButton.setBorderPainted(false);
    checkButton.setFocusPainted(false);

    removeDrugButton.setBackground(new Color(255, 140, 0));
    removeDrugButton.setForeground(Color.WHITE);
    removeDrugButton.setOpaque(true);
    removeDrugButton.setBorderPainted(false);
    removeDrugButton.setFocusPainted(false);

    saveReportButton.setBackground(new Color(106, 90, 205));
    saveReportButton.setForeground(Color.WHITE);
    saveReportButton.setOpaque(true);
    saveReportButton.setBorderPainted(false);
    saveReportButton.setFocusPainted(false);

    clearButton.setBackground(new Color(178, 34, 34));
    clearButton.setForeground(Color.WHITE);
    clearButton.setOpaque(true);
    clearButton.setBorderPainted(false);
    clearButton.setFocusPainted(false);

    Dimension buttonSize = new Dimension(160, 35);
    addDrugButton.setPreferredSize(buttonSize);
    checkButton.setPreferredSize(buttonSize);
    removeDrugButton.setPreferredSize(buttonSize);
    saveReportButton.setPreferredSize(buttonSize);
    clearButton.setPreferredSize(buttonSize);

    buttonPanel.add(addDrugButton);
    buttonPanel.add(removeDrugButton);
    buttonPanel.add(checkButton);
    buttonPanel.add(saveReportButton);
    buttonPanel.add(clearButton);

    statusLabel = new JLabel("Ready. Please enter patient details and add drugs to prescription.");
    statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 0));
    statusLabel.setForeground(new Color(50, 50, 150));

    panel.add(buttonPanel, BorderLayout.CENTER);
    panel.add(statusLabel, BorderLayout.SOUTH);
    return panel;
}

    private void setupEventHandlers() {
        addDrugButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleAddDrug();
            }
        });
        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleCheckInteractions();
            }
        });
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleClear();
            }
        });
        saveReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleSaveReport();
            }
        });
        removeDrugButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleRemoveDrug();
            }
        });
        drugTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = drugTable.getSelectedRow();
                if (row >= 0) {
                    String drugName = tableModel.getValueAt(row, 0).toString();
                    Drug drug = drugDAO.getDrugByName(drugName);
                    if (drug != null) {
                        statusLabel.setText("Selected: " + drug.getName() + " | Max: " + drug.getMaxDosage() + " mg | " + drug.getDescription());
                        statusLabel.setForeground(new Color(50, 50, 150));
                    }
                }
            }
        });
        drugComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedDrug = (String) drugComboBox.getSelectedItem();
                    Drug drug = drugDAO.getDrugByName(selectedDrug);
                    if (drug != null) {
                        statusLabel.setText("Max allowed dosage for " + drug.getName() + ": " + drug.getMaxDosage() + " mg");
                        statusLabel.setForeground(new Color(50, 100, 50));
                    }
                }
            }
        });
    }

    private void handleAddDrug() {
        String patientName = patientNameField.getText().trim();
        String doctorName = doctorNameField.getText().trim();
        if (patientName.isEmpty() || doctorName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter patient name and doctor name first.", "Missing Information", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String selectedDrug = (String) drugComboBox.getSelectedItem();
        String dosageText = dosageField.getText().trim();
        String durationText = durationField.getText().trim();
        if (dosageText.isEmpty() || durationText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter dosage and duration.", "Missing Information", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            double dosage = Double.parseDouble(dosageText);
            int duration = Integer.parseInt(durationText);
            if (currentPrescription == null) {
                int age = Integer.parseInt(patientAgeField.getText().trim());
                double weight = Double.parseDouble(patientWeightField.getText().trim());
                String allergies = patientAllergyField.getText().trim();
                if (allergies.isEmpty()) allergies = "None";
                Patient patient = new Patient(patientName, age, weight, allergies);
                currentPrescription = new Prescription(patient, doctorName);
            }
            Drug drug = drugDAO.getDrugByName(selectedDrug);
            if (drug == null) {
                JOptionPane.showMessageDialog(this, "Drug not found in database.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            currentPrescription.addDrug(drug, dosage, duration);
            tableModel.addRow(new Object[]{drug.getName(), drug.getCategory(), dosage, duration});
            dosageField.setText("");
            durationField.setText("");
            statusLabel.setText("Drug added: " + drug.getName() + " | Total: " + currentPrescription.getTotalDrugs());
            statusLabel.setForeground(new Color(0, 128, 0));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for dosage and duration.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleCheckInteractions() {
        if (currentPrescription == null || currentPrescription.getTotalDrugs() == 0) {
            JOptionPane.showMessageDialog(this, "Please add at least one drug to the prescription first.", "No Drugs Added", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (currentPrescription.getTotalDrugs() < 2) {
            JOptionPane.showMessageDialog(this, "Please add at least two drugs to check for interactions.", "Not Enough Drugs", JOptionPane.WARNING_MESSAGE);
            return;
        }
        statusLabel.setText("Checking interactions...");
        statusLabel.setForeground(Color.BLUE);
        try {
            ArrayList<String[]> results = checker.checkPrescription(currentPrescription);
            lastInteractionResults = results;
            HashMap<String, String> allergyConflicts = checker.checkAllergyConflicts(currentPrescription);
            StringBuffer displayText = new StringBuffer();
            displayText.append("PATIENT: ").append(currentPrescription.getPatient().getName()).append("\n");
            displayText.append("AGE GROUP: ").append(currentPrescription.getPatient().getAgeGroup()).append("\n");
            displayText.append("DOCTOR: ").append(currentPrescription.getDoctorName()).append("\n");
            displayText.append("DATE: ").append(currentPrescription.getDate()).append("\n");
            displayText.append("----------------------------------------\n\n");
            if (!allergyConflicts.isEmpty()) {
                displayText.append("ALLERGY WARNINGS:\n");
                for (String conflict : allergyConflicts.values()) {
                    displayText.append("  !! ").append(conflict).append("\n");
                }
                displayText.append("\n");
            }
            if (results.isEmpty()) {
                displayText.append("RESULT: NO INTERACTIONS FOUND\n");
                displayText.append("All prescribed drugs are safe to use together.\n");
                resultArea.setBackground(new Color(240, 255, 240));
                statusLabel.setText("Check complete. No interactions found. Prescription is safe.");
                statusLabel.setForeground(new Color(0, 128, 0));
            } else {
                String highest = classifier.getHighestSeverity(results);
                displayText.append("HIGHEST SEVERITY: ").append(highest).append("\n");
                displayText.append(classifier.getSeverityMessage(highest)).append("\n\n");
                displayText.append("DETAILED INTERACTIONS:\n");
                displayText.append("----------------------------------------\n");
                for (String[] interaction : results) {
                    displayText.append("Drugs: ").append(interaction[0]).append(" + ").append(interaction[1]).append("\n");
                    displayText.append("Severity: ").append(interaction[2]).append("\n");
                    displayText.append("Risk: ").append(interaction[3]).append("\n");
                    displayText.append("Alternative: ").append(interaction[4]).append("\n");
                    displayText.append("----------------------------------------\n");
                }
                resultArea.setBackground(classifier.getSeverityColor(highest));
                statusLabel.setText("Check complete. Highest severity: " + highest);
                statusLabel.setForeground(Color.RED);
            }
            resultArea.setText(displayText.toString());
        } catch (DrugNotFoundException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Drug Not Found", JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Error: " + ex.getMessage());
        } catch (InvalidDosageException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Invalid Dosage", JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Dosage error: " + ex.getDrugName());
        } catch (DangerousInteractionException ex) {
            resultArea.setBackground(new Color(255, 77, 77));
            resultArea.setText("DANGEROUS INTERACTION DETECTED!\n\n" + ex.getMessage() + "\n\nSuggested Alternative: " + ex.getAlternative());
            JOptionPane.showMessageDialog(this,
                "SEVERE INTERACTION DETECTED!\n" + ex.getDrug1() + " + " + ex.getDrug2() +
                "\nSeverity: " + ex.getSeverity() + "\nAlternative: " + ex.getAlternative(),
                "DANGEROUS INTERACTION", JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("DANGEROUS INTERACTION: " + ex.getDrug1() + " + " + ex.getDrug2());
            statusLabel.setForeground(Color.RED);
        }
    }

    private void handleSaveReport() {
        if (currentPrescription == null) {
            JOptionPane.showMessageDialog(this, "No prescription to save.", "Nothing to Save", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String reportContent = resultArea.getText();
        if (reportContent.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please check interactions first before saving.", "No Results", JOptionPane.WARNING_MESSAGE);
            return;
        }
        reportWriter.writeReport(reportContent, currentPrescription.getPatient().getName());
        reportWriter.appendToLog("Report saved for: " + currentPrescription.getPatient().getName());
        JOptionPane.showMessageDialog(this, "Report saved successfully in the reports folder.", "Report Saved", JOptionPane.INFORMATION_MESSAGE);
        statusLabel.setText("Report saved for: " + currentPrescription.getPatient().getName());
        statusLabel.setForeground(new Color(106, 90, 205));
    }

    private void handleRemoveDrug() {
        int selectedRow = drugTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a drug from the table to remove.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String drugName = tableModel.getValueAt(selectedRow, 0).toString();
        tableModel.removeRow(selectedRow);
        if (currentPrescription != null) {
            currentPrescription.removeDrug(selectedRow);
        }
        statusLabel.setText("Removed: " + drugName + " from prescription.");
        statusLabel.setForeground(new Color(178, 34, 34));
    }

    private void handleClear() {
        patientNameField.setText("");
        patientAgeField.setText("");
        patientWeightField.setText("");
        patientAllergyField.setText("");
        doctorNameField.setText("");
        dosageField.setText("");
        durationField.setText("");
        tableModel.setRowCount(0);
        resultArea.setText("");
        resultArea.setBackground(Color.WHITE);
        currentPrescription = null;
        lastInteractionResults.clear();
        statusLabel.setText("Cleared. Ready for new prescription.");
        statusLabel.setForeground(new Color(50, 50, 150));
    }
}
