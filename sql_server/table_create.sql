USE hospital
GO
CREATE TABLE Users
                (
                    account VARCHAR(20) PRIMARY KEY,
                    user_name VARCHAR(20) NOT NULL,
                    user_age INT NOT NULL,
                    user_job VARCHAR(20) NOT NULL,
                    password VARCHAR(20) NOT NULL
                )
GO
CREATE TABLE Department
                    (
                        Department_number VARCHAR(20) PRIMARY KEY,
                        Doctor_number_dept_head VARCHAR(20) NOT NULL,
                        Department_name VARCHAR(20) NOT NULL,
                        Department_phone VARCHAR(20) NOT NULL,
                        Department_address VARCHAR(20) NOT NULL,
                        Department_scale INT NOT NULL
                    )
GO

CREATE TABLE Admin
                    (
                        Admin_login VARCHAR(20) PRIMARY KEY,
                        Admin_password VARCHAR(20) NOT NULL
                        FOREIGN KEY(Admin_login) REFERENCES Users(account)
                    )
GO

CREATE TABLE Doctor
            (
                Doctor_number VARCHAR(20) PRIMARY KEY,
                Doctor_name VARCHAR(20) NOT NULL,
                Doctor_age INT NOT NULL,
                Doctor_sex VARCHAR(20) NOT NULL,
                Doctor_phone VARCHAR(20) NOT NULL,
                Doctor_login VARCHAR(20) NOT NULL,
                Doctor_password VARCHAR(20) NOT NULL,
                Department_num_doctor_in VARCHAR(20) NOT NULL,
                FOREIGN KEY(Department_num_doctor_in) REFERENCES Department(Department_number),
                FOREIGN KEY(Doctor_login) REFERENCES Users(account)

            )
GO

CREATE TABLE Patient
            (
                Patient_number VARCHAR(20) PRIMARY KEY,
                Patient_name VARCHAR(20) NOT NULL,
                Patient_age INT NOT NULL,
                Patient_sex VARCHAR(20) NOT NULL,
                Patient_phone VARCHAR(20) NOT NULL,
                Patient_ID VARCHAR(20) NOT NULL,
                Patient_login VARCHAR(20) NOT NULL,
                Patient_password VARCHAR(20) NOT NULL,
                FOREIGN KEY(Patient_login) REFERENCES Users(account)

            )

GO

CREATE TABLE Ward
            (
                Ward_number VARCHAR(20) PRIMARY KEY,
                Ward_name VARCHAR(20) NOT NULL,
                Ward_scale VARCHAR(20) NOT NULL,
                Ward_sex VARCHAR(20) NOT NULL,
                Ward_phone VARCHAR(20) NOT NULL,
                Ward_address VARCHAR(20) NOT NULL,
            )

GO

CREATE TABLE Drug
            (
                Drug_number VARCHAR(20) PRIMARY KEY,
                Drug_name VARCHAR(20) NOT NULL,
                Drug_type VARCHAR(20) NOT NULL,
                Drug_price VARCHAR(20) NOT NULL,
                Drug_face VARCHAR(20) NOT NULL,
                Drug_dosage VARCHAR(20) NOT NULL
            )

GO

CREATE TABLE Prescription
            (
                Prescription_number VARCHAR(20) PRIMARY KEY,
                Prescription_time VARCHAR(20) NOT NULL
            )
GO

CREATE TABLE Reservation
                    (
                        Patient_number VARCHAR(20) PRIMARY KEY,
                        Patient_name VARCHAR(20) NOT NULL,
                        Patient_age INT NOT NULL,
                        Patient_phone VARCHAR(20) NOT NULL,
                        Doctor_number VARCHAR(20) NOT NULL,
                        Doctor_name VARCHAR(20) NOT NULL,
                        Doctor_phone VARCHAR(20) NOT NULL,
                        Time_appointment  VARCHAR(20) NOT NULL
                    )



