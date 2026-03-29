CREATE DATABASE IF NOT EXISTS medical_db;
USE medical_db;

CREATE TABLE IF NOT EXISTS drugs (
    drug_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50),
    max_dosage DOUBLE,
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS patients (
    patient_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    age INT,
    weight DOUBLE,
    allergies VARCHAR(300)
);

CREATE TABLE IF NOT EXISTS interactions (
    interaction_id INT PRIMARY KEY AUTO_INCREMENT,
    drug1_id INT,
    drug2_id INT,
    severity VARCHAR(20),
    description VARCHAR(500),
    alternative VARCHAR(200),
    FOREIGN KEY (drug1_id) REFERENCES drugs(drug_id),
    FOREIGN KEY (drug2_id) REFERENCES drugs(drug_id)
);

CREATE TABLE IF NOT EXISTS prescriptions (
    prescription_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT,
    doctor_name VARCHAR(100),
    date DATE,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id)
);

CREATE TABLE IF NOT EXISTS prescription_drugs (
    id INT PRIMARY KEY AUTO_INCREMENT,
    prescription_id INT,
    drug_id INT,
    dosage DOUBLE,
    duration_days INT,
    FOREIGN KEY (prescription_id) REFERENCES prescriptions(prescription_id),
    FOREIGN KEY (drug_id) REFERENCES drugs(drug_id)
);

INSERT INTO drugs(name, category, max_dosage, description) VALUES
('Aspirin', 'Painkiller', 4000, 'Pain relief and blood thinner'),
('Warfarin', 'Anticoagulant', 10, 'Prevents blood clots'),
('Amoxicillin', 'Antibiotic', 3000, 'Common bacterial infection treatment'),
('Ibuprofen', 'Painkiller', 2400, 'Anti-inflammatory pain relief'),
('Metformin', 'Antidiabetic', 2000, 'Controls blood sugar levels'),
('Lisinopril', 'Antihypertensive', 40, 'Controls high blood pressure'),
('Azithromycin', 'Antibiotic', 500, 'Respiratory infection treatment'),
('Ciprofloxacin', 'Antibiotic', 1500, 'Broad spectrum antibiotic'),
('Atorvastatin', 'Statin', 80, 'Lowers cholesterol'),
('Omeprazole', 'Antacid', 40, 'Reduces stomach acid'),
('Paracetamol', 'Painkiller', 4000, 'Fever and mild pain relief'),
('Diazepam', 'Sedative', 40, 'Anxiety and muscle relaxant'),
('Digoxin', 'Cardiac', 0.5, 'Heart failure treatment'),
('Furosemide', 'Diuretic', 600, 'Removes excess fluid'),
('Prednisone', 'Corticosteroid', 80, 'Anti-inflammatory steroid'),
('Clopidogrel', 'Antiplatelet', 75, 'Prevents blood clots in arteries'),
('Simvastatin', 'Statin', 40, 'Reduces bad cholesterol'),
('Losartan', 'Antihypertensive', 100, 'High blood pressure treatment'),
('Gabapentin', 'Anticonvulsant', 3600, 'Nerve pain and seizures'),
('Levothyroxine', 'Thyroid', 300, 'Thyroid hormone replacement'),
('Morphine', 'Opioid', 200, 'Severe pain management'),
('Codeine', 'Opioid', 240, 'Mild to moderate pain'),
('Rifampicin', 'Antibiotic', 600, 'Tuberculosis treatment'),
('Phenytoin', 'Anticonvulsant', 600, 'Epilepsy treatment'),
('Lithium', 'Mood Stabilizer', 1800, 'Bipolar disorder treatment'),
('Fluoxetine', 'Antidepressant', 80, 'Depression and anxiety'),
('Methotrexate', 'Immunosuppressant', 25, 'Arthritis and cancer treatment'),
('Clarithromycin', 'Antibiotic', 1000, 'Bacterial infection treatment'),
('Doxycycline', 'Antibiotic', 200, 'Broad spectrum antibiotic'),
('Naproxen', 'Painkiller', 1500, 'Anti-inflammatory pain relief');

INSERT INTO patients(name, age, weight, allergies) VALUES
('John Doe', 65, 70.5, 'Penicillin'),
('Priya Sharma', 45, 58.0, 'None'),
('Raj Kumar', 30, 75.0, 'Sulfa'),
('Anita Singh', 55, 62.0, 'Aspirin'),
('Michael Brown', 72, 80.0, 'None');

INSERT INTO interactions(drug1_id, drug2_id, severity, description, alternative) VALUES
(1, 2, 'SEVERE', 'Aspirin and Warfarin together cause serious internal bleeding risk', 'Replace Aspirin with Paracetamol'),
(1, 4, 'MODERATE', 'Aspirin and Ibuprofen together increase risk of stomach ulcers', 'Use only one painkiller at a time'),
(1, 16, 'SEVERE', 'Aspirin and Clopidogrel together cause excessive bleeding', 'Consult doctor for safer alternative'),
(2, 9, 'MODERATE', 'Warfarin effect is increased by Atorvastatin causing bleeding risk', 'Monitor INR levels closely'),
(2, 15, 'SEVERE', 'Warfarin and Prednisone together cause unpredictable bleeding', 'Use alternative anti-inflammatory'),
(3, 7, 'MILD', 'Two antibiotics together reduce individual effectiveness', 'Use antibiotics separately'),
(3, 8, 'MILD', 'Amoxicillin and Ciprofloxacin reduce each others effect', 'Use only one antibiotic'),
(4, 30, 'MODERATE', 'Ibuprofen and Naproxen together increase stomach bleeding risk', 'Use only one NSAID at a time'),
(5, 6, 'MILD', 'Metformin and Lisinopril together may lower blood pressure too much', 'Monitor blood pressure regularly'),
(12, 21, 'SEVERE', 'Diazepam and Morphine together cause dangerous respiratory depression', 'Avoid combination completely'),
(13, 14, 'MODERATE', 'Digoxin levels increase with Furosemide causing toxicity risk', 'Monitor digoxin blood levels'),
(21, 22, 'SEVERE', 'Two opioids together cause life threatening breathing problems', 'Use only one opioid at a time'),
(24, 26, 'MODERATE', 'Phenytoin reduces effectiveness of Fluoxetine', 'Increase Fluoxetine dose under supervision'),
(17, 2, 'MODERATE', 'Simvastatin increases Warfarin effect causing bleeding risk', 'Reduce Warfarin dose under supervision'),
(25, 26, 'MODERATE', 'Lithium and Fluoxetine together may cause serotonin syndrome', 'Monitor for serotonin syndrome symptoms');

