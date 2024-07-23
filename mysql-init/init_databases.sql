-- Create databases if they do not already exist
CREATE DATABASE IF NOT EXISTS appointments_db;
CREATE DATABASE IF NOT EXISTS doctors_db;
CREATE DATABASE IF NOT EXISTS medicalhistory_db;
CREATE DATABASE IF NOT EXISTS patient_db;

-- Use doctors_db and create doctor table
USE doctors_db;
CREATE TABLE IF NOT EXISTS Doctor (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    surname VARCHAR(255),
    specialization VARCHAR(255)
);

-- Insert data into doctor table
INSERT INTO Doctor (id, name, surname, specialization) VALUES 
('D00', 'John', 'Doe', 'Cardiology'), 
('D01', 'Jane', 'Smith', 'Neurology'), 
('D02', 'Emily', 'Johnson', 'Pediatrics'), 
('D03', 'Michael', 'Brown', 'Orthopedics'), 
('D04', 'Sarah', 'Williams', 'Dermatology');

-- Use appointments_db and create appointments table
USE appointments_db;
CREATE TABLE IF NOT EXISTS appointments (
    app_id VARCHAR(255) PRIMARY KEY,
    patient_cf VARCHAR(255) NOT NULL,
    doctor_id VARCHAR(255) NOT NULL,
    appointment_datetime VARCHAR(255) NOT NULL,
    info VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL
);

-- Insert data into appointments table
INSERT INTO appointments (app_id, patient_cf, doctor_id, appointment_datetime, info, status) VALUES 
('AP01', 'ABC123', 'D01', '2024-07-15T10:00:00', 'Regular check-up', 'Scheduled'), 
('AP02', 'DEF456', 'D02', '2024-07-16T11:00:00', 'Follow-up visit', 'Scheduled'), 
('AP03', 'GHI789', 'D03', '2024-07-17T12:00:00', 'Consultation', 'Scheduled');

-- Use medicalhistory_db and create medical_record table
USE medicalhistory_db;
CREATE TABLE IF NOT EXISTS medical_record (
    CF VARCHAR(10) PRIMARY KEY,
    diseases VARCHAR(255),
    allergies TEXT,
    vaccinations TEXT,
    medications TEXT,
    notes TEXT
);

-- Insert data into medical_record table
INSERT INTO medical_record (CF, diseases, allergies, vaccinations, medications, notes) VALUES
('ABC123', 'Hypertension', '["peanuts", "oil"]', '["HPV"]', '["Aspirin"]', 'Patient has a family history of heart disease.'),
('DEF456', 'Asthma', '["Penicillin"]', NULL, '["Paracetamol"]', 'Patient requires annual check-ups for asthma management.'),
('GHI789', NULL, '["Software engineering"]', '["Hepatitis B"]', '["Ibuprofen"]', 'Patient is in good health, no known conditions.');

-- Use patient_db and create patients table
USE patient_db;
CREATE TABLE IF NOT EXISTS patients (
    CF VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    gender VARCHAR(50) NOT NULL,
    age INT NOT NULL,
    address VARCHAR(255),
    email VARCHAR(255) NOT NULL
);

-- Insert data into patients table
INSERT INTO patients (CF, name, surname, gender, age, address, email) VALUES
('ABC123', 'Mario', 'Rossi', 'MALE', 18, 'Via Roma 1', 'mario.rossi@example.com'),
('DEF456', 'Luigi', 'Bianchi', 'MALE', 36, 'Via Milano 10', 'luigi.bianchi@example.com'),
('GHI789', 'Anna', 'Verdi', 'FEMALE', 68, 'Via Firenze 5', 'anna.verdi@example.com');
