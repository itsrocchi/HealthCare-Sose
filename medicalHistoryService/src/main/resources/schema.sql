
CREATE TABLE IF NOT EXISTS medical_record (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
CF VARCHAR(50),
diseases VARCHAR(255),
allergies VARCHAR(255),
vaccinations VARCHAR(255),
medications VARCHAR(255),
notes TEXT
);