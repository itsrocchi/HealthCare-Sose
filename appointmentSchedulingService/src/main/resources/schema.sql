-- schema.sql

CREATE TABLE appointments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_cf VARCHAR(255) NOT NULL,
    doctor_id BIGINT NOT NULL,
    appointment_datetime VARCHAR(255) NOT NULL,
    info VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL
);
