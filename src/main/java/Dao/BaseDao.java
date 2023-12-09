package Dao;

import Pojo.*;

import java.sql.*;

public class BaseDao {
    //数据库驱动
    public final String Driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    //数据库用户密码
    private final String username = "susan";
    private final String password = "12345678@zkqZKQ";
    //数据库名  login是数据库名称
    public String URL="jdbc:sqlserver://localhost:1433;databaseName=hospital;encrypt=false";
    //修改数据库连接url
    public void setURL(String URL) {
        this.URL = URL;
    }

    private User user=User.getUser();
    //建立数据库连接 getConnection
    public Connection getCon() throws ClassNotFoundException, SQLException {
        System.out.println();
        Class.forName(Driver);
//        System.out.println("hello world");

        Connection connection = DriverManager.getConnection(URL, username, password);
        return connection;
    }

    //查询所有用户
    public ResultSet searchUsers() throws SQLException, ClassNotFoundException {
        String sql="select * from Users;"; //Users为用户表
        Connection con = this.getCon();
        Statement statement=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs= statement.executeQuery(sql);
        //返回Rs对象
        return rs;
    }
    //查询所有病人信息
    public ResultSet searchPatient(String searchTerm) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Patient WHERE Patient_number LIKE ?";
        Connection con = this.getCon();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, "%" + searchTerm + "%"); // 使用通配符来进行模糊查询

        ResultSet rs = ps.executeQuery(); // 执行查询并获取结果集
        return rs;
    }
    //查询所有医生信息
    public ResultSet searchDoctor(String searchTerm) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Doctor WHERE Doctor_number LIKE ?";
        Connection con = this.getCon();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, "%" + searchTerm + "%"); // 使用通配符来进行模糊查询

        ResultSet rs = ps.executeQuery(); // 执行查询并获取结果集
        return rs;
    }

    // 查询病房信息
    public ResultSet searchWard(String searchTerm) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Ward WHERE Ward_number LIKE ?";
        Connection con = this.getCon();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, "%" + searchTerm + "%"); // 使用通配符来进行模糊查询

        ResultSet rs = ps.executeQuery(); // 执行查询并获取结果集
        return rs;
    }

    // 查询科室信息
    public ResultSet searchDepartment(String searchTerm) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Department WHERE Department_number LIKE ?";
        Connection con = this.getCon();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, "%" + searchTerm + "%"); // 使用通配符来进行模糊查询

        ResultSet rs = ps.executeQuery(); // 执行查询并获取结果集
        return rs;
    }

    //查询所有药品信息
    public ResultSet searchDrug(String searchTerm) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Drug WHERE Drug_number LIKE ?";
        Connection con = this.getCon();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, "%" + searchTerm + "%"); // 使用通配符来进行模糊查询

        ResultSet rs = ps.executeQuery(); // 执行查询并获取结果集
        return rs;
    }
    //查询所有处方信息
    public ResultSet searchPrescription() throws SQLException, ClassNotFoundException {
        String sql="select * from Prescription;";
        Connection con = this.getCon();
        Statement statement=con.createStatement();
        ResultSet rs=statement.executeQuery(sql);
        return rs;
    }

    //创建新用户 注册按钮 添加新用户信息（三个都包含了）
    // 现在所有的用户都存储到users中，因为多个表不好查找
    public boolean addNewUser(String account,String name,String password,int age,String job) throws SQLException, ClassNotFoundException {

        //先判断职业
        if (job.equals("患者")) {
            String sql = "insert into Users(account,user_name,user_age,user_job,password) values(?,?,?,?,?);";
            Connection con = this.getCon();
            PreparedStatement ps = con.prepareStatement(sql);
                ps.setObject(1, account);
                ps.setObject(2, name);
                ps.setObject(3, age);
                ps.setObject(4, "患者");
                ps.setObject(5, password);
                int count = ps.executeUpdate();
                if (count > 0) {
                    System.out.println("您现在已经注册成功，欢迎使用");
                    //释放资源
                    ps.close();
                    con.close();
                    return true;
                } else {
                    System.out.println("非常抱歉，您注册失败，请重试...");
                    return false;
                }
        }else if (job.equals("医生"))
        {
            String sql = "insert into Users(account,user_name,user_age,user_job,password) values(?,?,?,?,?);";
            Connection con = this.getCon();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, account);
            ps.setObject(2, name);
            ps.setObject(3, age);
            ps.setObject(4, "医生");
            ps.setObject(5, password);
            int count=ps.executeUpdate();
            if (count>0)
            {
                System.out.println("您现在已经注册成功，欢迎使用");
                //释放资源
                ps.close();
                con.close();
                return true;
            }else {
                System.out.println("非常抱歉，您注册失败，请重试...");
                return false;
            }
        }else if (job.equals("管理员"))
        {
            String sql = "insert into Users(account,user_name,user_age,user_job,password) values(?,?,?,?,?);";
            Connection con = this.getCon();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, account);
            ps.setObject(2, name);
            ps.setObject(3, age);
            ps.setObject(4, "管理员");
            ps.setObject(5, password);
            int count=ps.executeUpdate();
            if (count>0)
            {
                System.out.println("您现在已经注册成功，欢迎使用");
                //释放资源
                ps.close();
                con.close();
                return true;
            }else {
                System.out.println("非常抱歉，您注册失败，请重试...");
                return false;
            }
        }else {
            throw new RuntimeException("职业选择错误");
        }

    }
    //修改用户个人信息
    public void modifyuser() throws SQLException, ClassNotFoundException {
        User user=User.getUser();
        System.out.println(user.getPassword());
        String sql="update Users set user_name=?,password=?,user_age=? where account=?;";
        Connection con = this.getCon();
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setObject(1,user.getName());
        ps.setObject(2,user.getNewpassword());
        ps.setObject(3,user.getAge());
        ps.setObject(4,user.getAccount());

        int count=ps.executeUpdate();
        if (count>0)
        {
            System.out.println("修改成功");
            //释放资源
            ps.close();
            con.close();
        }else {
            System.out.println("修改失败");

        }

    }
    //删除指定医生记录
    public void delDoctor(Doctor doctor) throws SQLException, ClassNotFoundException {
        Connection con = this.getCon();
        //医生编号是唯一的 所以根据医生编号删除
        String sql="delete from Doctor where Doctor_number=?";
        PreparedStatement ps= con.prepareStatement(sql);
        ps.setInt(1,Integer.parseInt(doctor.getDoctorNumber()));
        int count=ps.executeUpdate();
        if (count>0)
        {
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }


    // 添加医生信息到数据库
    public void addDoctor(Doctor doctor, String login, String password, String departmentNumber) throws SQLException, ClassNotFoundException {
        Connection con = this.getCon();

        // 在Doctor表中插入医生信息
        String sql = "INSERT INTO Doctor (Doctor_number, Doctor_name, Doctor_age, Doctor_sex, Doctor_phone, Doctor_login, Doctor_password, Department_num_doctor_in) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, doctor.getDoctorNumber());
        ps.setString(2, doctor.getDoctorName());
        ps.setInt(3, doctor.getDoctorAge());
        ps.setString(4, doctor.getDoctorSex());
        ps.setString(5, doctor.getDoctorPhone());
        ps.setString(6, login); // 医生登录信息
        ps.setString(7, password); // 医生密码
        ps.setString(8, departmentNumber); // 医生所属部门编号

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("医生信息添加成功");
        } else {
            System.out.println("医生信息添加失败");
        }
    }

    // 删除患者信息
    public void delPatient(Patient1 patient) throws SQLException, ClassNotFoundException {
        Connection con = this.getCon();
        //患者编号是唯一的 所以根据患者编号删除
        String sql="delete from Doctor where Doctor_number=?";
        PreparedStatement ps= con.prepareStatement(sql);
        ps.setInt(1,Integer.parseInt(patient.getPatientId()));
        int count=ps.executeUpdate();
        if (count>0)
        {
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }


    // 添加患者信息到数据库
    public void addPatient(Patient1 patient, String patientID, String patientLogin, String patientPassword, String Patient_take_number) throws SQLException, ClassNotFoundException {
        Connection con = this.getCon();

        // 在Patient表中插入患者信息
        String sql = "INSERT INTO Patient (Patient_number, Patient_name, Patient_age, Patient_sex, Patient_phone, Patient_ID, Patient_login, Patient_password, Patient_take_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, patient.getPatientId());
        ps.setString(2, patient.getPatientName());
        ps.setInt(3, patient.getPatientAge());
        ps.setString(4, patient.getPatientSex());
        ps.setString(5, patient.getPatientPhone());
        ps.setString(6, patientID);
        ps.setString(7, patientLogin);
        ps.setString(8, patientPassword);
        ps.setString(9, Patient_take_number);


        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("患者信息添加成功");
        } else {
            System.out.println("患者信息添加失败");
        }
    }

    // 增加药品信息
    public void addDrug(Drug drug) throws SQLException, ClassNotFoundException {
        Connection con = getCon();
        String sql = "INSERT INTO Drug (Drug_number, Drug_name, Drug_type, Drug_price, Drug_face, Drug_dosage) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, drug.getDrugNumber());
        ps.setString(2, drug.getDrugName());
        ps.setString(3, drug.getDrugType());
        ps.setDouble(4, drug.getDrugPrice());
        ps.setString(5, drug.getDrugFace());
        ps.setString(6, drug.getDrugDosage());

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("药品添加成功");
        } else {
            System.out.println("药品添加失败");
        }
    }

    // 删除药品信息
    public void delDrug(Drug drug) throws SQLException, ClassNotFoundException {
        Connection con = getCon();
        String sql = "DELETE FROM Drug WHERE Drug_number = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, drug.getDrugNumber());

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("药品删除成功");
        } else {
            System.out.println("未找到药品或删除失败");
        }
    }

    // 增加科室信息
    public void addDepartment(Department department) throws SQLException, ClassNotFoundException {
        Connection con = getCon();
        String sql = "INSERT INTO Department (Department_number, Doctor_number_dept_head, Department_name, Department_phone, Department_address, Department_scale) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, department.getDepartmentNumber());
        ps.setString(2, department.getDoctorNumberDeptHead());
        ps.setString(3, department.getDepartmentName());
        ps.setString(4, department.getDepartmentPhone());
        ps.setString(5, department.getDepartmentAddress());
        ps.setInt(6, department.getDepartmentScale());

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("科室添加成功");
        } else {
            System.out.println("科室添加失败");
        }
    }

    // 删除科室信息
    public void delDepartment(Department department) throws SQLException, ClassNotFoundException {
        Connection con = getCon();
        String sql = "DELETE FROM Department WHERE Department_number = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, department.getDepartmentNumber());

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("科室删除成功");
        } else {
            System.out.println("未找到科室或删除失败");
        }
    }


    // 增加病房信息
    public void addWard(Ward ward) throws SQLException, ClassNotFoundException {
        Connection con = getCon();
        String sql = "INSERT INTO Ward (wardNumber, wardName, wardScale, wardPhone, wardAddress) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, ward.getWardNumber());
        ps.setString(2, ward.getWardName());
        ps.setString(3, ward.getWardScale());
        ps.setString(4, ward.getWardPhone());
        ps.setString(5, ward.getWardAddress());

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("病房添加成功");
        } else {
            System.out.println("病房添加失败");
        }
    }

    // 删除病房信息
    public void delWard(Ward ward) throws SQLException, ClassNotFoundException {
        Connection con = getCon();
        String sql = "DELETE FROM Ward WHERE Ward_number = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, ward.getWardNumber());

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("病房删除成功");
        } else {
            System.out.println("未找到病房或删除失败");
        }
    }



    // 增加处方信息
    public void addPrescription(String prescriptionNumber, String prescriptionDate) throws SQLException, ClassNotFoundException {
        Connection con = getCon();
        String sql = "INSERT INTO Prescription (Prescription_number, Prescription_time) " + "VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, prescriptionNumber);
        ps.setString(2, prescriptionDate);
        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("处方添加成功");
        } else {
            System.out.println("处方添加失败");
        }
    }

    // 删除处方信息
    public void delPrescription(String prescriptionNumber) throws SQLException, ClassNotFoundException {
        Connection con = getCon();
        String sql = "DELETE FROM Prescription WHERE Prescription_number = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, prescriptionNumber);

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("处方删除成功");
        } else {
            System.out.println("未找到处方或删除失败");
        }
    }

    // 预约挂号
    public void addReservation(Reservation reservation) throws SQLException, ClassNotFoundException {
        Connection con = getCon();
        String sql = "INSERT INTO Reservation (Patient_number, Patient_name, Patient_age, Patient_phone, " +
                "Doctor_number, Doctor_name, Doctor_phone, Time_appointment) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, reservation.getPatientNumber());
        ps.setString(2, reservation.getPatientName());
        ps.setInt(3, reservation.getPatientAge());
        ps.setString(4, reservation.getPatientPhone());
        ps.setString(5, reservation.getDoctorNumber());
        ps.setString(6, reservation.getDoctorName());
        ps.setString(7, reservation.getDoctorPhone());
        ps.setString(8, reservation.getTimeAppointment());

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("预约挂号成功");
        } else {
            System.out.println("预约挂号失败");
        }
    }

    //查询自己的所有预约记录
    public ResultSet searchReservationByPatientName(String searchTerm) throws SQLException, ClassNotFoundException {
        String sql="select * from Reservation where Patient_name like ?;";  // 此处需要再建一个预约信息表
        PreparedStatement ps=this.getCon().prepareStatement(sql);
        ps.setObject(1,searchTerm);
        ResultSet rs= ps.executeQuery();
        //返回Rs对象
        return rs;
    }

    //删除预约记录
// 删除预约记录
    public void delReservation(String searchTerm) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Reservation WHERE Patient_name LIKE ?";
        PreparedStatement ps = this.getCon().prepareStatement(sql);
        ps.setObject(1, searchTerm);
        int deletedRows = ps.executeUpdate();

        if (deletedRows > 0) {
            System.out.println("预约已成功取消！");
        } else {
            System.out.println("没有找到匹配的预约信息，请检查姓名是否正确。");
        }
    }

    // 管理员档案更新
    public void updateAdminInformation(Admin admin) throws SQLException, ClassNotFoundException {
        Connection con = getCon();
        String sql = "INSERT INTO Admin (Admin_login, Admin_password) VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, admin.getAdminLogin());
        ps.setString(2, admin.getAdminPassword());

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("管理员信息修改成功");
        } else {
            System.out.println("管理员信息修改失败");
        }
    }

    public void updatePatientInformation(Patient patient) throws SQLException, ClassNotFoundException {
        Connection con = getCon();
        String sql = "INSERT INTO Patient (Patient_name, Patient_age, Patient_sex, Patient_phone, Patient_ID, Patient_login, Patient_password) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, patient.getPatientName());
        ps.setInt(2, patient.getPatientAge());
        ps.setString(3, patient.getPatientSex());
        ps.setString(4, patient.getPatientPhone());
        ps.setString(5, patient.getPatientID());
        ps.setString(6, patient.getPatientLogin());
        ps.setString(7, patient.getPatientPassword());

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("患者信息修改成功");
        } else {
            System.out.println("患者信息修改失败");
        }
    }

    // 添加病人安排
    public void addSchedule(String number_P, String number_D, String number_W) throws SQLException, ClassNotFoundException {
        Connection con = this.getCon();

        // 在Patient表中插入患者信息
        String sql = "INSERT INTO PatientSchedule (Patient_number, Doctor_number, Ward_number) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, number_P);
        ps.setString(2, number_D);
        ps.setString(3, number_W);

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("患者病房安排成功");
        } else {
            System.out.println("患者病房安排失败");
        }
    }


    public void updateDoctorInformation(Doctor_file doctor) throws SQLException, ClassNotFoundException {
        Connection con = getCon();
        String sql = "INSERT INTO Doctor (Doctor_number, Doctor_name, Doctor_age, Doctor_sex, Doctor_phone, Doctor_login, Doctor_password, Department_num_doctor_in) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, doctor.getDoctorNumber());
        ps.setString(2, doctor.getDoctorName());
        ps.setInt(3, doctor.getDoctorAge());
        ps.setString(4, doctor.getDoctorSex());
        ps.setString(5, doctor.getDoctorPhone());
        ps.setString(6, doctor.getDoctorLogin());
        ps.setString(7, doctor.getDoctorPassword());
        ps.setString(8, doctor.getDepartmentNumDoctorIn());

        int count = ps.executeUpdate();
        if (count > 0) {
            System.out.println("医生信息修改成功");
        } else {
            System.out.println("医生信息修改失败");
        }
    }

}

