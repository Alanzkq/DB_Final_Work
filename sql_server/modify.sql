USE hospital
GO

INSERT INTO Users (account, user_name, user_age, user_job, password)
VALUES 
    ('2000', '小明', 18, '患者', '123456'),
    ('2001', '小红', 18, '患者', '123456'),
    ('2002', '小花', 18, '患者', '123456'),
    ('2003', '小曾', 18, '患者', '123456'),
    ('2004', '小李', 18, '患者', '123456'),
    ('1', '王医生', 40, '医生', '123456'),
    ('2', '曾医生', 38, '医生', '123456'),
    ('3', '张医生', 38, '医生', '123456'),
    ('4', '郝医生', 38, '医生', '123456'),
    ('5', '肖医生', 38, '医生', '123456'),
    ('6', '马医生', 38, '医生', '123456'),
    ('3001', '管理员1', 38, '管理员', '123456'),
    ('3002', '管理员2', 38, '管理员', '123456'),
    ('3003', '管理员3', 38, '管理员', '123456');
GO
INSERT INTO Doctor (Doctor_number, Doctor_name, Doctor_age, Doctor_sex, Doctor_phone, Doctor_login, Doctor_password, Department_num_doctor_in)
VALUES 
    ('1', '王医生', 38, '男', '12345', '1', '123456', '1'),
    ('2', '曾医生', 38, '男', '12345', '2', '123456', '1'),
    ('3', '张医生', 38, '男', '12345', '3', '123456', '2'),
    ('4', '郝医生', 38, '男', '12345', '4', '123456', '2'),
    ('5', '肖医生', 38, '女', '12345', '5', '123456', '1'),
    ('6', '马医生', 38, '男', '12345', '6', '123456', '1');

GO
INSERT INTO Admin (Admin_login, Admin_password)
VALUES 
    ('3001', '123456'),
    ('3002', '123456'),
    ('3003', '123456');

GO
INSERT INTO Ward (Ward_number, Ward_name, Ward_scale, Ward_sex, Ward_phone, Ward_address)
VALUES
    ('W001', 'Pediatrics Ward', 'Medium', 'Mixed', '1234567890', '123 Main St'),
    ('W002', 'Surgical Ward', 'Large', 'Mixed', '9876543210', '456 Oak St'),
    ('W003', 'Maternity Ward', 'Small', 'Female', '5678901234', '789 Elm St');

GO
INSERT INTO Drug (Drug_number, Drug_name, Drug_type, Drug_price, Drug_face, Drug_dosage)
VALUES
    ('D001', 'Paracetamol', 'Painkiller', '$5.00', 'Tablet', '500mg'),
    ('D002', 'Amoxicillin', 'Antibiotic', '$8.50', 'Capsule', '250mg'),
    ('D003', 'Lisinopril', 'Hypertension', '$12.75', 'Tablet', '10mg');

GO
INSERT INTO Prescription (Prescription_number, Prescription_time)
VALUES
    ('P001', '2023-01-15 08:30:00'),
    ('P002', '2023-02-20 14:45:00'),
    ('P003', '2023-03-10 11:00:00');

GO
INSERT INTO Reservation (Patient_number, Patient_name, Patient_age, Patient_phone, Doctor_number, Doctor_name, Doctor_phone, Time_appointment)
VALUES
    ('Pat001', '小明', 30, '1112223333', 1, '王医生', 12345, '2023-04-05 09:00:00'),
    ('Pat002', '小花', 45, '4445556666', 1, '曾医生', 12345, '2023-05-10 10:30:00'),
    ('Pat003', '小李', 25, '7778889999', 2, '肖医生', 12345, '2023-06-15 13:15:00');

GO
INSERT INTO Department (Department_number, Doctor_number_dept_head, Department_name, Department_phone, Department_address, Department_scale)
VALUES
    ('1', '王医生', 'Pediatrics', '1112223333', '123 Hospital Ave', 50),
    ('2', '王医生', 'Surgery', '4445556666', '456 Medical Blvd', 75),
    ('3', '王医生', 'Gynecology', '7778889999', '789 Health St', 30);
