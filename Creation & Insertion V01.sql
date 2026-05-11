create database Medical_Symptom_Decision

CREATE TABLE Patients (
    Id INT PRIMARY KEY IDENTITY,
    Name NVARCHAR(100) NOT NULL,
    Age INT,
    Gender NVARCHAR(10),
    CreatedAt DATETIME DEFAULT GETDATE()
);

CREATE TABLE Symptoms (
    Id INT PRIMARY KEY IDENTITY,
    Name NVARCHAR(100) NOT NULL UNIQUE,
    Description NVARCHAR(255)
);

CREATE TABLE Diseases (
    Id INT PRIMARY KEY IDENTITY,
    Name NVARCHAR(100) NOT NULL,
    Description NVARCHAR(255),
    SeverityLevel NVARCHAR(20) -- Low / Medium / High
);

CREATE TABLE DiseaseSymptoms (
    Id INT PRIMARY KEY IDENTITY,
    DiseaseId INT FOREIGN KEY REFERENCES Diseases(Id),
    SymptomId INT FOREIGN KEY REFERENCES Symptoms(Id),
    Weight INT NOT NULL CHECK (Weight BETWEEN 1 AND 5)
);

CREATE TABLE PatientSymptoms (
    Id INT PRIMARY KEY IDENTITY,
    PatientId INT FOREIGN KEY REFERENCES Patients(Id),
    SymptomId INT FOREIGN KEY REFERENCES Symptoms(Id),
    Value BIT NOT NULL -- 1 = has symptom
);

CREATE TABLE DiagnosisResults (
    Id INT PRIMARY KEY IDENTITY,
    PatientId INT FOREIGN KEY REFERENCES Patients(Id),
    DiseaseId INT FOREIGN KEY REFERENCES Diseases(Id),
    Score FLOAT,
    CreatedAt DATETIME DEFAULT GETDATE()
);

INSERT INTO Symptoms (Name, Description) VALUES
('Fever', 'High body temperature'),
('Cough', 'Persistent coughing'),
('Fatigue', 'Feeling tired or weak'),
('Loss of Appetite', 'Reduced desire to eat'),
('Headache', 'Pain in head'),
('Nausea', 'Feeling of vomiting'),
('Vomiting', 'Expelling stomach contents'),
('Diarrhea', 'Frequent loose stools'),
('Joint Pain', 'Pain in joints'),
('Swelling', 'Inflammation or enlargement'),
('Shortness of Breath', 'Difficulty breathing'),
('Chest Pain', 'Pain in chest area'),
('Dizziness', 'Feeling lightheaded'),
('Sore Throat', 'Pain in throat'),
('Runny Nose', 'Nasal discharge');

INSERT INTO Diseases (Name, Description, SeverityLevel) VALUES
('Common Cold', 'Viral respiratory infection', 'Low'),
('Flu', 'Influenza viral infection', 'Medium'),
('Food Poisoning', 'Infection from contaminated food', 'High'),
('Arthritis', 'Joint inflammation condition', 'Medium'),
('Bronchitis', 'Inflammation of airways', 'Medium'),
('Pneumonia', 'Lung infection', 'High'),
('Migraine', 'Severe headache disorder', 'Low');

-- Common Cold
INSERT INTO DiseaseSymptoms VALUES
(1, 2, 2), -- Cough
(1, 14, 3), -- Sore Throat
(1, 15, 2), -- Runny Nose
(1, 3, 1); -- Fatigue

-- Flu
INSERT INTO DiseaseSymptoms VALUES
(2, 1, 3), -- Fever
(2, 2, 2), -- Cough
(2, 3, 3), -- Fatigue
(2, 5, 2); -- Headache

-- Food Poisoning
INSERT INTO DiseaseSymptoms VALUES
(3, 6, 3), -- Nausea
(3, 7, 3), -- Vomiting
(3, 8, 3), -- Diarrhea
(3, 3, 2); -- Fatigue

-- Arthritis
INSERT INTO DiseaseSymptoms VALUES
(4, 9, 3), -- Joint Pain
(4, 10, 2); -- Swelling

-- Bronchitis
INSERT INTO DiseaseSymptoms VALUES
(5, 2, 3), -- Cough
(5, 11, 2), -- Shortness of Breath
(5, 3, 2); -- Fatigue

-- Pneumonia
INSERT INTO DiseaseSymptoms VALUES
(6, 1, 3), -- Fever
(6, 11, 3), -- Shortness of Breath
(6, 12, 3), -- Chest Pain
(6, 2, 2); -- Cough

-- Migraine
INSERT INTO DiseaseSymptoms VALUES
(7, 5, 3), -- Headache
(7, 13, 2), -- Dizziness
(7, 6, 1); -- Nausea

INSERT INTO Patients (Name, Age, Gender) VALUES
('Ahmed', 25, 'Male'),
('Sara', 30, 'Female'),
('Omar', 40, 'Male');

INSERT INTO DiseaseSymptoms (DiseaseId, SymptomId, Weight) VALUES (1, 2, 2)