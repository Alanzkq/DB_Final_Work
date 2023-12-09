package com.example.work;

import Dao.BaseDao;
import Dao.StringUtil;
import Pojo.*;
import Dao.BaseDao.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MainController {
    //获取用户信息
    User user=User.getUser();
    @FXML
    Text name;
    @FXML
    Text account;
    @FXML
    Text age;

    @FXML
    TableView<NewsItem> News;

    @FXML
    TableColumn<NewsItem, String> date;

    @FXML
    TableColumn<NewsItem, String> writer;

    @FXML
    TableColumn<NewsItem, String> headline;

    @FXML
    TableColumn<NewsItem, Integer> readCount;

    @FXML
    TableColumn<NewsItem, Integer> likes;

    @FXML
    TableColumn<NewsItem, Integer> comments;

    @FXML
    TableColumn<NewsItem, String> url;

    //数据库连接
    private BaseDao baseDao=new BaseDao();

    public void show_table()
    {
        ObservableList<NewsItem> data = FXCollections.observableArrayList(

                new NewsItem("2023.11.19", "戚许", "治疗儿童多动症", 22345, 1265, 453, "health.a"),
                new NewsItem("2023.11.17", "游思珉", "显著减缓蛋白尿", 34555, 3443, 433, "health.b"),
                new NewsItem("2023.11.21", "陶稚元", "阿尔茨海默病新药", 75452, 3235, 253, "health.c"),
                new NewsItem("2023.11.25", "陈晃", "肿瘤创新口服抑制剂", 25254, 23542, 4346, "health.d"),
                new NewsItem("2023.11.28", "方一鸣", "美国干细胞治疗", 25465, 2544, 967, "health.e"),
                new NewsItem("2023.12.04", "俞硕", "乳腺外科学生患癌", 73624, 3422, 475, "health.f"),
                new NewsItem("2023.12.06", "纪予舟", "医院贩卖出生证明", 53537, 4576, 544, "health.g"),
                new NewsItem("2022.09.08", "陆景和", "CRISPR疗法问世", 554754, 42643, 3464, "health.h"),
                new NewsItem("2022.09.28", "左然", "开发细胞基因疗法", 636436, 64363, 7353, "health.i"),
                new NewsItem("2022.10.16", "夏鸣星", "预防骨质疏松", 346360, 34633, 3643, "health.j")
        );
        date.setCellValueFactory(new PropertyValueFactory("date"));//映射
        writer.setCellValueFactory(new PropertyValueFactory("writer"));
        headline.setCellValueFactory(new PropertyValueFactory("headline"));
        readCount.setCellValueFactory(new PropertyValueFactory("readCount"));
        likes.setCellValueFactory(new PropertyValueFactory("likes"));
        comments.setCellValueFactory(new PropertyValueFactory("comments"));
        url.setCellValueFactory(new PropertyValueFactory("url"));

        News.setItems(data); //tableview添加list

    }
    /**
     * 医生信息管理部分
     * */
    // 管理医生信息界面
    @FXML
    public void onManageDoctorClick() {
        List<String> choices = Arrays.asList("删除医生信息", "增加医生信息");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("删除医生信息", choices);
        dialog.setTitle("管理医生信息");
        dialog.setHeaderText(null);
        dialog.setContentText("请选择要执行的操作:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(choice -> {
            if (choice.equals("删除医生信息")) {
                // 弹出删除医生信息的输入对话框
                showDeleteDoctorDialog();
            } else if (choice.equals("增加医生信息")) {
                // 弹出增加医生信息的输入界面
                showAddDoctorDialog();
            }
        });
    }

    // 删除医生信息
    private void showDeleteDoctorDialog() {
        TextInputDialog deleteDialog = new TextInputDialog();
        deleteDialog.setTitle("删除医生信息");
        deleteDialog.setHeaderText(null);
        deleteDialog.setContentText("请输入要删除的医生编号:");

        Optional<String> result = deleteDialog.showAndWait();
        result.ifPresent(doctorNumber -> {
            try {
                Doctor doctorToDelete = new Doctor(doctorNumber, "", 0, "", ""); // 这里只需提供医生编号即可
                baseDao.delDoctor(doctorToDelete);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    // 增加医生信息
    private void showAddDoctorDialog() {
        Stage stage = new Stage();
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        Label titleLabel = new Label("新增医生信息");
        titleLabel.setFont(Font.font("黑体", 24));
        titleLabel.setTextFill(Color.color(1.0, 0.1, 0.1));

        // Create text fields for doctor information
        TextField numberField = new TextField();
        numberField.setPromptText("医生编号");

        TextField nameField = new TextField();
        nameField.setPromptText("医生姓名");

        TextField ageField = new TextField();
        ageField.setPromptText("医生年龄");

        TextField sexField = new TextField();
        sexField.setPromptText("医生性别");

        TextField phoneField = new TextField();
        phoneField.setPromptText("医生电话");

        TextField loginField = new TextField();
        loginField.setPromptText("医生登录名");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("医生密码");

        TextField departmentField = new TextField();
        departmentField.setPromptText("所属部门编号");

        // Create a button for adding the doctor
        Button addButton = new Button("确认添加");
        addButton.setOnAction(e -> {
            // Retrieve doctor information from the text fields
            String doctorNumber = numberField.getText();
            String doctorName = nameField.getText();
            int doctorAge = Integer.parseInt(ageField.getText());
            String doctorSex = sexField.getText();
            String doctorPhone = phoneField.getText();
            String doctorLogin = loginField.getText();
            String doctorPassword = passwordField.getText();
            String departmentNumber = departmentField.getText();


            try {
                Doctor newDoctor = new Doctor(doctorNumber, doctorName, doctorAge, doctorSex, doctorPhone);
                baseDao.addDoctor(newDoctor, doctorLogin, doctorPassword, departmentNumber);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            // Close the dialog after adding the doctor
            stage.close();
        });

        // Add components to the VBox
        vbox.getChildren().addAll(
                titleLabel, numberField, nameField, ageField, sexField, phoneField,
                loginField, passwordField, departmentField, addButton
        );

        Scene scene = new Scene(vbox, 400, 400);
        stage.setScene(scene);
        stage.setTitle("新增医生信息");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * 患者信息管理部分
     * */
    // 管理患者信息
    @FXML
    public void onManagePatientClick() {
        List<String> choices = Arrays.asList("删除患者信息", "增加患者信息");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("删除患者信息", choices);
        dialog.setTitle("管理患者信息");
        dialog.setHeaderText(null);
        dialog.setContentText("请选择要执行的操作:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(choice -> {
            if (choice.equals("删除患者信息")) {
                showDeletePatientDialog();
            } else if (choice.equals("增加患者信息")) {
                showAddPatientDialog();
            }
        });
    }

    // 删除患者信息
    private void showDeletePatientDialog() {
        TextInputDialog deleteDialog = new TextInputDialog();
        deleteDialog.setTitle("删除患者信息");
        deleteDialog.setHeaderText(null);
        deleteDialog.setContentText("请输入要删除的患者编号:");

        Optional<String> result = deleteDialog.showAndWait();
        result.ifPresent(patientNumber -> {
            try {
                Patient1 patientToDelete = new Patient1(patientNumber, "", 0, "", ""); // 提供患者编号
                baseDao.delPatient(patientToDelete);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    // 增加患者信息
    private void showAddPatientDialog() {
        Stage stage = new Stage();
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        Label titleLabel = new Label("新增患者信息");
        titleLabel.setFont(Font.font("黑体", 24));
        titleLabel.setTextFill(Color.color(1.0, 0.1, 0.1));

        TextField numberField = new TextField();
        numberField.setPromptText("患者编号");

        TextField nameField = new TextField();
        nameField.setPromptText("患者姓名");

        TextField ageField = new TextField();
        ageField.setPromptText("患者年龄");

        TextField sexField = new TextField();
        sexField.setPromptText("患者性别");

        TextField phoneField = new TextField();
        phoneField.setPromptText("患者电话");

        TextField idField = new TextField();
        idField.setPromptText("患者ID");

        TextField loginField = new TextField();
        loginField.setPromptText("患者登录名");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("患者密码");

        TextField drugTakeNumberField = new TextField();
        drugTakeNumberField.setPromptText("药物编号");

        Button addButton = new Button("确认添加");
        addButton.setOnAction(e -> {
            String patientNumber = numberField.getText();
            String patientName = nameField.getText();
            int patientAge = Integer.parseInt(ageField.getText());
            String patientSex = sexField.getText();
            String patientPhone = phoneField.getText();
            String patientID = idField.getText();
            String patientLogin = loginField.getText();
            String patientPassword = passwordField.getText();
            String drugTakeNumber = drugTakeNumberField.getText();

            try {
                Patient1 newPatient = new Patient1(patientNumber, patientName, patientAge, patientSex, patientPhone);
                baseDao.addPatient(newPatient, patientID, patientLogin, patientPassword, drugTakeNumber);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            stage.close();
        });

        vbox.getChildren().addAll(
                titleLabel, numberField, nameField, ageField, sexField, phoneField,
                idField, loginField, passwordField, drugTakeNumberField, addButton
        );

        Scene scene = new Scene(vbox, 400, 400);
        stage.setScene(scene);
        stage.setTitle("新增患者信息");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * 药品信息管理部分
     * */

    // 管理药品信息
    @FXML
    public void onManageDrugClick() {
        List<String> choices = Arrays.asList("删除药品信息", "增加药品信息");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("删除药品信息", choices);
        dialog.setTitle("管理药品信息");
        dialog.setHeaderText(null);
        dialog.setContentText("请选择要执行的操作:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(choice -> {
            if (choice.equals("删除药品信息")) {
                showDeleteDrugDialog();
            } else if (choice.equals("增加药品信息")) {
                showAddDrugDialog();
            }
        });
    }

    // 增加药品信息
    private void showAddDrugDialog() {
        Stage stage = new Stage();
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        Label titleLabel = new Label("新增药品信息");
        titleLabel.setFont(Font.font("黑体", 24));
        titleLabel.setTextFill(Color.color(1.0, 0.1, 0.1));

        TextField numberField = new TextField();
        numberField.setPromptText("药品编号");

        TextField nameField = new TextField();
        nameField.setPromptText("药品名称");

        TextField typeField = new TextField();
        typeField.setPromptText("药品类型");

        TextField priceField = new TextField();
        priceField.setPromptText("药品价格");

        TextField faceField = new TextField();
        faceField.setPromptText("药品产地");

        TextField dosageField = new TextField();
        dosageField.setPromptText("药品用量");

        Button addButton = new Button("确认添加");
        addButton.setOnAction(e -> {
            String drugNumber = numberField.getText();
            String drugName = nameField.getText();
            String drugType = typeField.getText();
            double drugPrice = Double.parseDouble(priceField.getText());
            String drugFace = faceField.getText();
            String drugDosage = dosageField.getText();

            try {
                Drug newDrug = new Drug(drugNumber, drugName, drugType, drugPrice, drugFace, drugDosage);
                baseDao.addDrug(newDrug);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            stage.close();
        });

        vbox.getChildren().addAll(
                titleLabel, numberField, nameField, typeField, priceField, faceField, dosageField, addButton
        );

        Scene scene = new Scene(vbox, 400, 400);
        stage.setScene(scene);
        stage.setTitle("新增药品信息");
        stage.setResizable(false);
        stage.show();
    }

    // 删除药品信息
    private void showDeleteDrugDialog() {
        TextInputDialog deleteDialog = new TextInputDialog();
        deleteDialog.setTitle("删除药品信息");
        deleteDialog.setHeaderText(null);
        deleteDialog.setContentText("请输入要删除的药品编号:");

        Optional<String> result = deleteDialog.showAndWait();
        result.ifPresent(drugNumber -> {
            try {
                Drug drugToDelete = new Drug(drugNumber, "", "", 0.0, "", ""); // 这里只需提供药品编号即可
                baseDao.delDrug(drugToDelete);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 处方信息管理部分
     * */

    // 管理处方界面
    @FXML
    public void onManagePrescriptionClick() {
        List<String> choices = Arrays.asList("删除处方信息", "增加处方信息");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("删除处方信息", choices);
        dialog.setTitle("管理处方信息");
        dialog.setHeaderText(null);
        dialog.setContentText("请选择要执行的操作:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(choice -> {
            if (choice.equals("删除处方信息")) {
                showDeletePrescriptionDialog();
            } else if (choice.equals("增加处方信息")) {
                showAddPrescriptionDialog();
            }
        });
    }

    // 增加处方信息
    private void showAddPrescriptionDialog() {
        Stage stage = new Stage();
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        Label titleLabel = new Label("新增处方信息");
        titleLabel.setFont(Font.font("黑体", 24));
        titleLabel.setTextFill(Color.color(1.0, 0.1, 0.1));

        TextField numberField = new TextField();
        numberField.setPromptText("处方编号");

        TextField dateField = new TextField();
        dateField.setPromptText("处方开设时间");


        Button addButton = new Button("确认添加");
        addButton.setOnAction(e -> {
            String prescriptionNumber = numberField.getText();
            String prescriptionDate= dateField.getText();


            try {
                baseDao.addPrescription(prescriptionNumber, prescriptionDate);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            stage.close();
        });

        vbox.getChildren().addAll(
                titleLabel, numberField, dateField, addButton
        );

        Scene scene = new Scene(vbox, 400, 400);
        stage.setScene(scene);
        stage.setTitle("新增处方信息");
        stage.setResizable(false);
        stage.show();
    }

    // 删除处方信息
    private void showDeletePrescriptionDialog() {
        TextInputDialog deleteDialog = new TextInputDialog();
        deleteDialog.setTitle("删除处方信息");
        deleteDialog.setHeaderText(null);
        deleteDialog.setContentText("请输入要删除的处方编号:");

        Optional<String> result = deleteDialog.showAndWait();
        result.ifPresent(drugNumber -> {
            try {
                baseDao.delPrescription(drugNumber);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }


    /**
     * 这部分是科室信息管理部分
     * */
    // 管理科室信息
    @FXML
    public void onManageDepartmentClick() {
        List<String> choices = Arrays.asList("删除科室信息", "增加科室信息");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("删除科室信息", choices);
        dialog.setTitle("管理科室信息");
        dialog.setHeaderText(null);
        dialog.setContentText("请选择要执行的操作:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(choice -> {
            if (choice.equals("删除科室信息")) {
                showDeleteDepartmentDialog();
            } else if (choice.equals("增加科室信息")) {
                showAddDepartmentDialog();
            }
        });
    }

    // 增加科室信息
    private void showAddDepartmentDialog() {
        Stage stage = new Stage();
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        Label titleLabel = new Label("新增科室信息");
        titleLabel.setFont(Font.font("黑体", 24));
        titleLabel.setTextFill(Color.color(1.0, 0.1, 0.1));

        TextField numberField = new TextField();
        numberField.setPromptText("科室编号");

        TextField headDoctorField = new TextField();
        headDoctorField.setPromptText("科室负责医生编号");

        TextField nameField = new TextField();
        nameField.setPromptText("科室名称");

        TextField phoneField = new TextField();
        phoneField.setPromptText("科室电话");

        TextField addressField = new TextField();
        addressField.setPromptText("科室地址");

        TextField scaleField = new TextField();
        scaleField.setPromptText("科室规模");

        Button addButton = new Button("确认添加");
        addButton.setOnAction(e -> {
            String departmentNumber = numberField.getText();
            String doctorNumber = headDoctorField.getText();
            String departmentName = nameField.getText();
            String departmentPhone = phoneField.getText();
            String departmentAddress = addressField.getText();
            int departmentScale = Integer.parseInt(scaleField.getText());

            try {
                Department newDepartment = new Department(departmentNumber, doctorNumber, departmentName, departmentPhone, departmentAddress, departmentScale);
                baseDao.addDepartment(newDepartment);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            stage.close();
        });

        vbox.getChildren().addAll(
                titleLabel, numberField, headDoctorField, nameField, phoneField, addressField, scaleField, addButton
        );

        Scene scene = new Scene(vbox, 400, 400);
        stage.setScene(scene);
        stage.setTitle("新增科室信息");
        stage.setResizable(false);
        stage.show();
    }


    // 删除科室信息
    private void showDeleteDepartmentDialog() {
        TextInputDialog deleteDialog = new TextInputDialog();
        deleteDialog.setTitle("删除科室信息");
        deleteDialog.setHeaderText(null);
        deleteDialog.setContentText("请输入要删除的科室编号:");

        Optional<String> result = deleteDialog.showAndWait();
        result.ifPresent(departmentNumber -> {
            try {
                Department departmentToDelete = new Department(departmentNumber, "", "", "", "", 0); // 这里只需提供药品编号即可
                baseDao.delDepartment(departmentToDelete);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }


    /**
     * 这部分是病房信息管理部分
     * */
    // 管理病房信息
    @FXML
    public void onManageWardClick() {
        List<String> choices = Arrays.asList("删除病房信息", "增加病房信息");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("删除病房信息", choices);
        dialog.setTitle("管理病房信息");
        dialog.setHeaderText(null);
        dialog.setContentText("请选择要执行的操作:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(choice -> {
            if (choice.equals("删除病房信息")) {
                showDeleteWardDialog();
            } else if (choice.equals("增加病房信息")) {
                showAddWardDialog();
            }
        });
    }

    // 增加病房信息
    private void showAddWardDialog() {
        Stage stage = new Stage();
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        Label titleLabel = new Label("新增病房信息");
        titleLabel.setFont(Font.font("黑体", 24));
        titleLabel.setTextFill(Color.color(1.0, 0.1, 0.1));

        TextField numberField = new TextField();
        numberField.setPromptText("病房编号");

        TextField nameField = new TextField();
        nameField.setPromptText("病房名称");

        TextField scaleField = new TextField();
        scaleField.setPromptText("病房规模");

        TextField phoneField = new TextField();
        phoneField.setPromptText("病房电话");

        TextField addressField = new TextField();
        addressField.setPromptText("病房地址");

        Button addButton = new Button("确认添加");
        addButton.setOnAction(e -> {
            String wardNumber = numberField.getText();
            String wardName = nameField.getText();
            String wardScale = scaleField.getText();
            String wardPhone = phoneField.getText();
            String wardAddress = addressField.getText();

            try {
                Ward newWard = new Ward(wardNumber, wardName, wardScale, wardPhone, wardAddress);
                baseDao.addWard(newWard);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            stage.close();
        });

        vbox.getChildren().addAll(
                titleLabel, numberField, nameField, scaleField, phoneField, addressField, addButton
        );

        Scene scene = new Scene(vbox, 400, 400);
        stage.setScene(scene);
        stage.setTitle("新增病房信息");
        stage.setResizable(false);
        stage.show();
    }

    // 删除病房信息
    private void showDeleteWardDialog() {
        TextInputDialog deleteDialog = new TextInputDialog();
        deleteDialog.setTitle("删除病房信息");
        deleteDialog.setHeaderText(null);
        deleteDialog.setContentText("请输入要删除的病房编号:");

        Optional<String> result = deleteDialog.showAndWait();
        result.ifPresent(wardNumber -> {
            try {
                Ward wardToDelete = new Ward(wardNumber, "", "", "", ""); // 这里只需提供病房编号即可
                baseDao.delWard(wardToDelete);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 这部分是预约部分的相关处理逻辑，暂时包括
     * 进行预约
     * 查询预约信息
     * 取消预约信息
     * */

    // 预约挂号界面
    @FXML
    void onAddReservationClick() {
        Stage stage = new Stage();
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        Label titleLabel = new Label("预约挂号");
        titleLabel.setFont(Font.font("黑体", 24));
        titleLabel.setTextFill(Color.color(1.0, 0.1, 0.1));

        TextField patientNumberField = new TextField();
        patientNumberField.setPromptText("患者编号");

        TextField patientNameField = new TextField();
        patientNameField.setPromptText("患者姓名");

        TextField patientAgeField = new TextField();
        patientAgeField.setPromptText("患者年龄");

        TextField patientPhoneField = new TextField();
        patientPhoneField.setPromptText("患者电话");

        TextField doctorNumberField = new TextField();
        doctorNumberField.setPromptText("医生编号");

        TextField doctorNameField = new TextField();
        doctorNameField.setPromptText("医生姓名");

        TextField doctorPhoneField = new TextField();
        doctorPhoneField.setPromptText("医生电话");

        TextField appointmentTimeField = new TextField();
        appointmentTimeField.setPromptText("预约时间");

        Button confirmButton = new Button("确认预约");
        confirmButton.setOnAction(e -> {
            String patientNumber = patientNumberField.getText();
            String patientName = patientNameField.getText();
            int patientAge = Integer.parseInt(patientAgeField.getText());
            String patientPhone = patientPhoneField.getText();
            String doctorNumber = doctorNumberField.getText();
            String doctorName = doctorNameField.getText();
            String doctorPhone = doctorPhoneField.getText();
            String appointmentTime = appointmentTimeField.getText();

            try {
                Reservation reservation = new Reservation(patientNumber, patientName, patientAge, patientPhone, doctorNumber, doctorName, doctorPhone, appointmentTime);
                baseDao.addReservation(reservation);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            stage.close();
        });

        vbox.getChildren().addAll(
                titleLabel, patientNumberField, patientNameField, patientAgeField, patientPhoneField,
                doctorNumberField, doctorNameField, doctorPhoneField, appointmentTimeField, confirmButton
        );

        Scene scene = new Scene(vbox, 400, 500);
        stage.setScene(scene);
        stage.setTitle("预约挂号");
        stage.setResizable(false);
        stage.show();
    }

    // 预约信息查询界面
    @FXML
    void onShowReservationClick() {
        Stage stage = new Stage();
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        Label titleLabel = new Label("预约记录查询");
        titleLabel.setFont(Font.font("黑体", 24));
        titleLabel.setTextFill(Color.color(1.0, 0.1, 0.1));

        TextField searchField = new TextField();
        searchField.setPromptText("输入预约人姓名进行查询");

        Button searchButton = new Button("查询");
        searchButton.setOnAction(e -> {
            String searchTerm = searchField.getText();
            try {
                ResultSet result = baseDao.searchReservationByPatientName(searchTerm);

                TableView<Reservation> tableView = new TableView<>();

                TableColumn<Reservation, String> colPatientNumber = new TableColumn<>("患者编号");
                colPatientNumber.setCellValueFactory(new PropertyValueFactory<>("patientNumber"));

                TableColumn<Reservation, String> colPatientName = new TableColumn<>("患者姓名");
                colPatientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));

                TableColumn<Reservation, Integer> colPatientAge = new TableColumn<>("患者年龄");
                colPatientAge.setCellValueFactory(new PropertyValueFactory<>("patientAge"));

                TableColumn<Reservation, String> colPatientPhone = new TableColumn<>("患者电话");
                colPatientPhone.setCellValueFactory(new PropertyValueFactory<>("patientPhone"));

                TableColumn<Reservation, String> colDoctorNumber = new TableColumn<>("医生编号");
                colDoctorNumber.setCellValueFactory(new PropertyValueFactory<>("doctorNumber"));

                TableColumn<Reservation, String> colDoctorName = new TableColumn<>("医生姓名");
                colDoctorName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));

                TableColumn<Reservation, String> colDoctorPhone = new TableColumn<>("医生电话");
                colDoctorPhone.setCellValueFactory(new PropertyValueFactory<>("doctorPhone"));

                TableColumn<Reservation, String> colTimeAppointment = new TableColumn<>("预约时间");
                colTimeAppointment.setCellValueFactory(new PropertyValueFactory<>("timeAppointment"));

                tableView.getColumns().addAll(
                        colPatientNumber, colPatientName, colPatientAge, colPatientPhone,
                        colDoctorNumber, colDoctorName, colDoctorPhone, colTimeAppointment
                );

                while (result.next()) {
                    Reservation reservation = new Reservation(
                            result.getString("Patient_number"),
                            result.getString("Patient_name"),
                            result.getInt("Patient_age"),
                            result.getString("Patient_phone"),
                            result.getString("Doctor_number"),
                            result.getString("Doctor_name"),
                            result.getString("Doctor_phone"),
                            result.getString("Time_appointment")
                    );
                    tableView.getItems().add(reservation);
                }

                vbox.getChildren().add(tableView);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        vbox.getChildren().addAll(titleLabel, searchField, searchButton);

        Scene scene = new Scene(vbox, 700, 450);
        stage.setScene(scene);
        stage.setTitle("预约记录查询");
        stage.setResizable(false);
        stage.show();

    }

    // 取消预约信息界面
    @FXML
    void onCancelReservationClick(){
        TextInputDialog deleteDialog = new TextInputDialog();
        deleteDialog.setTitle("删除预约信息");
        deleteDialog.setHeaderText(null);
        deleteDialog.setContentText("请输入要删除的预约人姓名:");

        Optional<String> result = deleteDialog.showAndWait();
        result.ifPresent(reservationNumber -> {
            try {
                baseDao.delReservation(reservationNumber);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }


    /**
     * 这部分是信息查询部分，主要包括
     * 患者信息查询
     * 病房信息查询
     * 医生信息查询
     * 药品信息查询
     * 科室信息查询
     **/

    // 患者信息查询
    public void onsearchallpatientclick() throws SQLException, ClassNotFoundException{
        Stage stage = new Stage();
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        Label titleLabel = new Label("患者信息查询");
        titleLabel.setFont(Font.font("黑体", 24));
        titleLabel.setTextFill(Color.color(1.0, 0.1, 0.1));

        TextField searchField = new TextField();
        searchField.setPromptText("输入患者编号进行查询");

        Button searchButton = new Button("查询");
        searchButton.setOnAction(e -> {
            String searchTerm = searchField.getText();
            try {
                ResultSet result = baseDao.searchPatient(searchTerm);

                TableView<Patient1> tableView = new TableView<>();

                TableColumn<Patient1, String> colNumber = new TableColumn<>("患者编号");
                colNumber.setCellValueFactory(new PropertyValueFactory<>("patientId"));

                TableColumn<Patient1, String> colName = new TableColumn<>("患者姓名");
                colName.setCellValueFactory(new PropertyValueFactory<>("patientName"));

                TableColumn<Patient1, Integer> colAge = new TableColumn<>("患者年龄");
                colAge.setCellValueFactory(new PropertyValueFactory<>("patientAge"));

                TableColumn<Patient1, String> colSex = new TableColumn<>("患者性别");
                colSex.setCellValueFactory(new PropertyValueFactory<>("patientSex"));

                TableColumn<Patient1, String> colPhone = new TableColumn<>("患者电话");
                colPhone.setCellValueFactory(new PropertyValueFactory<>("patientPhone"));

                tableView.getColumns().addAll(colNumber, colName, colAge, colSex, colPhone);

                while (result.next()) {
                    Patient1 patient = new Patient1(
                            result.getString("Patient_number"),
                            result.getString("Patient_name"),
                            result.getInt("Patient_age"),
                            result.getString("Patient_sex"),
                            result.getString("Patient_phone")
                    );
                    tableView.getItems().add(patient);
                }

                vbox.getChildren().add(tableView);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        vbox.getChildren().addAll(titleLabel, searchField, searchButton);

        Scene scene = new Scene(vbox, 700, 450);
        stage.setScene(scene);
        stage.setTitle("患者信息查询");
        stage.setResizable(false);
        stage.show();
    }

    // 病房信息查询
    public void onshowshowroomclick() throws SQLException, ClassNotFoundException{
        Stage stage = new Stage();
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        Label titleLabel = new Label("病房信息查询");
        titleLabel.setFont(Font.font("黑体", 24));
        titleLabel.setTextFill(Color.color(1.0, 0.1, 0.1));

        TextField searchField = new TextField();
        searchField.setPromptText("输入病房编号进行查询");

        Button searchButton = new Button("查询");
        searchButton.setOnAction(e -> {
            String searchTerm = searchField.getText();
            try {
                ResultSet result = baseDao.searchWard(searchTerm);

                TableView<Ward> tableView = new TableView<>();

                TableColumn<Ward, String> colNumber = new TableColumn<>("病房编号");
                colNumber.setCellValueFactory(new PropertyValueFactory<>("wardNumber"));

                TableColumn<Ward, String> colName = new TableColumn<>("病房名称");
                colName.setCellValueFactory(new PropertyValueFactory<>("wardName"));

                TableColumn<Ward, String> colScale = new TableColumn<>("病房规模");
                colScale.setCellValueFactory(new PropertyValueFactory<>("wardScale"));

                TableColumn<Ward, String> colPhone = new TableColumn<>("病房电话");
                colPhone.setCellValueFactory(new PropertyValueFactory<>("wardPhone"));

                TableColumn<Ward, String> colAddress = new TableColumn<>("病房地址");
                colAddress.setCellValueFactory(new PropertyValueFactory<>("wardAddress"));

                tableView.getColumns().addAll(colNumber, colName, colScale, colPhone, colAddress);

                while (result.next()) {
                    Ward ward = new Ward(
                            result.getString(1),
                            result.getString(2),
                            result.getString(3),
                            result.getString(4),
                            result.getString(5)
                    );
                    tableView.getItems().add(ward);
                }

                vbox.getChildren().add(tableView);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        vbox.getChildren().addAll(titleLabel, searchField, searchButton);

        Scene scene = new Scene(vbox, 700, 450);
        stage.setScene(scene);
        stage.setTitle("患者信息查询");
        stage.setResizable(false);
        stage.show();
    }

    // 显示医生信息
    @FXML
    public void onshowdoctorclick() throws SQLException, ClassNotFoundException {
        Stage stage = new Stage();
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        Label titleLabel = new Label("医生信息查询");
        titleLabel.setFont(Font.font("黑体", 24));
        titleLabel.setTextFill(Color.color(1.0, 0.1, 0.1));

        TextField searchField = new TextField();
        searchField.setPromptText("输入医生姓名进行查询");

        Button searchButton = new Button("查询");
        searchButton.setOnAction(e -> {
            String searchTerm = searchField.getText();
            try {
                ResultSet result = baseDao.searchDoctor(searchTerm);

                TableView<Doctor> tableView = new TableView<>();

                TableColumn<Doctor, String> colNumber = new TableColumn<>("医生编号");
                colNumber.setCellValueFactory(new PropertyValueFactory<>("doctorNumber"));

                TableColumn<Doctor, String> colName = new TableColumn<>("医生姓名");
                colName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));

                TableColumn<Doctor, Integer> colAge = new TableColumn<>("医生年龄");
                colAge.setCellValueFactory(new PropertyValueFactory<>("doctorAge"));

                TableColumn<Doctor, String> colSex = new TableColumn<>("医生性别");
                colSex.setCellValueFactory(new PropertyValueFactory<>("doctorSex"));

                TableColumn<Doctor, String> colPhone = new TableColumn<>("医生电话");
                colPhone.setCellValueFactory(new PropertyValueFactory<>("doctorPhone"));

                tableView.getColumns().addAll(colNumber, colName, colAge, colSex, colPhone);

                while (result.next()) {
                    Doctor doctor = new Doctor(
                            result.getString(1),
                            result.getString(2),
                            result.getInt(3),
                            result.getString(4),
                            result.getString(5)
                    );
                    tableView.getItems().add(doctor);
                }

                vbox.getChildren().add(tableView);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        vbox.getChildren().addAll(titleLabel, searchField, searchButton);

        Scene scene = new Scene(vbox, 700, 450);
        stage.setScene(scene);
        stage.setTitle("医生信息查询");
        stage.setResizable(false);
        stage.show();

    }

    // 显示药品信息
    @FXML
    public void onShowDrugClick() throws SQLException, ClassNotFoundException {
        Stage stage = new Stage();
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        Label titleLabel = new Label("药品信息查询");
        titleLabel.setFont(Font.font("黑体", 24));
        titleLabel.setTextFill(Color.color(1.0, 0.1, 0.1));

        TextField searchField = new TextField();
        searchField.setPromptText("输入药品编号进行查询");

        Button searchButton = new Button("查询");
        searchButton.setOnAction(e -> {
            String searchTerm = searchField.getText();
            try {
                ResultSet result = baseDao.searchDrug(searchTerm);

                TableView<Drug> tableView = new TableView<>();

                TableColumn<Drug, String> colNumber = new TableColumn<>("药品编号");
                colNumber.setCellValueFactory(new PropertyValueFactory<>("drugNumber"));

                TableColumn<Drug, String> colName = new TableColumn<>("药品名称");
                colName.setCellValueFactory(new PropertyValueFactory<>("drugName"));

                TableColumn<Drug, String> colType = new TableColumn<>("药品类型");
                colType.setCellValueFactory(new PropertyValueFactory<>("drugType"));

                TableColumn<Drug, Double> colPrice = new TableColumn<>("药品价格");
                colPrice.setCellValueFactory(new PropertyValueFactory<>("drugPrice"));

                TableColumn<Drug, String> colFace = new TableColumn<>("药品效果");
                colFace.setCellValueFactory(new PropertyValueFactory<>("drugFace"));

                TableColumn<Drug, String> colDosage = new TableColumn<>("药品用量");
                colDosage.setCellValueFactory(new PropertyValueFactory<>("drugDosage"));

                tableView.getColumns().addAll(colNumber, colName, colType, colPrice, colFace, colDosage);

                while (result.next()) {
                    Drug drug = new Drug(
                            result.getString("Drug_number"),
                            result.getString("Drug_name"),
                            result.getString("Drug_type"),
                            result.getDouble("Drug_price"),
                            result.getString("Drug_face"),
                            result.getString("Drug_dosage")
                    );
                    tableView.getItems().add(drug);
                }

                vbox.getChildren().add(tableView);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        vbox.getChildren().addAll(titleLabel, searchField, searchButton);

        Scene scene = new Scene(vbox, 700, 450);
        stage.setScene(scene);
        stage.setTitle("药品信息查询");
        stage.setResizable(false);
        stage.show();
    }

    // 显示科室信息
    @FXML
    public void onShowDepartmentClick() throws SQLException, ClassNotFoundException {
        Stage stage = new Stage();
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        Label titleLabel = new Label("科室信息查询");
        titleLabel.setFont(Font.font("黑体", 24));
        titleLabel.setTextFill(Color.color(1.0, 0.1, 0.1));

        TextField searchField = new TextField();
        searchField.setPromptText("输入科室编号进行查询");

        Button searchButton = new Button("查询");
        searchButton.setOnAction(e -> {
            String searchTerm = searchField.getText();
            try {
                ResultSet result = baseDao.searchDepartment(searchTerm);

                TableView<Department> tableView = new TableView<>();

                TableColumn<Department, String> colNumber = new TableColumn<>("科室编号");
                colNumber.setCellValueFactory(new PropertyValueFactory<>("departmentNumber"));

                TableColumn<Department, String> colHeadDoctor = new TableColumn<>("科室负责医生编号");
                colHeadDoctor.setCellValueFactory(new PropertyValueFactory<>("doctorNumberDeptHead"));

                TableColumn<Department, String> colName = new TableColumn<>("科室名称");
                colName.setCellValueFactory(new PropertyValueFactory<>("departmentName"));

                TableColumn<Department, String> colPhone = new TableColumn<>("科室电话");
                colPhone.setCellValueFactory(new PropertyValueFactory<>("departmentPhone"));

                TableColumn<Department, String> colAddress = new TableColumn<>("科室地址");
                colAddress.setCellValueFactory(new PropertyValueFactory<>("departmentAddress"));

                TableColumn<Department, Integer> colScale = new TableColumn<>("科室规模");
                colScale.setCellValueFactory(new PropertyValueFactory<>("departmentScale"));

                tableView.getColumns().addAll(colNumber, colHeadDoctor, colName, colPhone, colAddress, colScale);

                while (result.next()) {
                    Department department = new Department(
                            result.getString("Department_number"),
                            result.getString("Doctor_number_dept_head"),
                            result.getString("Department_name"),
                            result.getString("Department_phone"),
                            result.getString("Department_address"),
                            result.getInt("Department_scale")
                    );
                    tableView.getItems().add(department);
                }

                vbox.getChildren().add(tableView);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        vbox.getChildren().addAll(titleLabel, searchField, searchButton);

        Scene scene = new Scene(vbox, 700, 450);
        stage.setScene(scene);
        stage.setTitle("科室信息查询");
        stage.setResizable(false);
        stage.show();
    }


    /**
     * 这部分是个人信息修改部分
     * */

    // 个人信息修改
    @FXML
    void onmodifyinfclick()
    {
        //新建一个窗口
        Stage stage=new Stage();
        Pane pane=new Pane();
        pane.setPrefWidth(700); pane.setPrefHeight(450);
        Label title=new Label("个人信息修改");
        //设置字体
        Font font=new Font("黑体",24);
        title.setFont(font);
        title.setTextFill(Color.color(1.0,0.1,0.1)); //设置填充颜色
        title.setLayoutX(202);  title.setLayoutY(14);
        //信息标签组件
        Label id=new Label("账号:");
        id.setFont(font);   id.setLayoutX(21);  id.setLayoutY(67);
        Label name=new Label("姓名:");
        name.setFont(font); name.setLayoutX(21);    name.setLayoutY(128);
        Label age=new Label("年龄:");
        age.setFont(font);  age.setLayoutX(21);     age.setLayoutY(191);
        Label password=new Label("原密码:");
        password.setFont(font); password.setLayoutX(21);    password.setLayoutY(254);


        Label warnings=new Label("不修改的内容请勿填写");
        warnings.setFont(font); warnings.setTextFill(Color.color(1,0,0));
        warnings.setLayoutX(21);    warnings.setLayoutY(340);

        //添加标签显示信息
        Label sid=new Label(user.getAccount());
        Label sname=new Label(user.getName());
        Label sage=new Label(String.valueOf(user.getAge()));
        sid.setFont(font);  sname.setFont(font);    sage.setFont(font);
        sid.setLayoutX(111);    sname.setLayoutX(111);  sage.setLayoutX(114);
        sid.setLayoutY(67);     sname.setLayoutY(128);  sage.setLayoutY(191);
        //文本域添加
        PasswordField oldpassword=new PasswordField();
        oldpassword.setLayoutX(111);    oldpassword.setLayoutY(255);
        oldpassword.setPrefWidth(144);  oldpassword.setPrefHeight(30);

        //按钮添加 绑定事件
        Button button=new Button("确认修改");
        button.setLayoutX(498); button.setLayoutY(329);
        button.setFont(font);
        //新标签
        Label label1=new Label("新账号:");
        Label label2=new Label("姓名:");
        Label label3=new Label("年龄:");
        Label label4=new Label("新密码:");
        label1.setFont(font);   label2.setFont(font);   label3.setFont(font);   label4.setFont(font);
        label1.setLayoutX(280);label2.setLayoutX(292);label3.setLayoutX(292);label4.setLayoutX(280);
        label1.setLayoutY(67); label2.setLayoutY(128);label3.setLayoutY(191);label4.setLayoutY(254);
        TextField newid=new TextField("账号暂不支持修改");
        newid.setEditable(false);
        TextField newname=new TextField();
        newname.setPromptText("请输入姓名");
        TextField newage=new TextField();
        newage.setPromptText("请输入年龄");
        PasswordField newpassword=new PasswordField();
        newid.setLayoutX(370);newname.setLayoutX(370);
        newage.setLayoutX(370);newpassword.setLayoutX(370);
        newid.setLayoutY(68);newname.setLayoutY(129);
        newage.setLayoutY(192);newpassword.setLayoutY(255);
        newid.setPrefHeight(30);newname.setPrefHeight(30);
        newage.setPrefHeight(30);newpassword.setPrefHeight(30);
        //设置文本域点击时选中修改

        //按钮绑定事件
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //修改个人信息
                //先检测需要修改哪些内容 account 作为唯一值暂时不可改
                if (!StringUtil.isEmpty(newname.getText())){
                    user.setName(newname.getText());
                }else if (!StringUtil.isEmpty(newage.getText()))
                {
                    user.setAge(Integer.parseInt(newage.getText()));
                }else if (!StringUtil.isEmpty(newpassword.getText()))
                {
                    user.setNewpassword(newpassword.getText());
                }
                else {
                    System.out.println("没有修改的内容");
                    return;
                }//如果新密码为null则默认延续旧密码
                if (StringUtil.isEmpty(user.getNewpassword()))
                {
                    user.setNewpassword(user.getPassword());
                }
                //验证旧密码是否正确
                //如果正确 则执行
                if (oldpassword.getText().equals(user.getPassword()))
                {
                    try {
                        baseDao.modifyuser();
                        //窗口提示
                        Dialog<ButtonType> warning = new Dialog<>();
                        warning.getDialogPane().getButtonTypes().add(new ButtonType("确认", ButtonBar.ButtonData.OK_DONE));
                        warning.setTitle("修改成功");
                        warning.setContentText("个人信息修改成功");
                        warning.show();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    Dialog<ButtonType> warning = new Dialog<>();
                    warning.getDialogPane().getButtonTypes().add(new ButtonType("确认", ButtonBar.ButtonData.OK_DONE));
                    warning.setTitle("密码错误");
                    warning.setContentText("密码错误,请重新输入");
                    warning.show();
                }

            }
        });

        //添加到布局上
        pane.getChildren().addAll(title,warnings,id,name,password,age,button,sid,sname,sage,oldpassword);
        pane.getChildren().addAll(label1,label2,label3,label4,newid,newname,newage,newpassword);
        Scene scene=new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("个人信息修改");
        stage.setResizable(false);
        stage.show();

    }


    // 档案信息管理界面
    @FXML
    void onManageFilesClick()
    {
        Stage stage = new Stage();
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        vbox.setMinWidth(700);
        vbox.setMinHeight(450);
        Label titleLabel = new Label("更新个人信息");
        titleLabel.setFont(Font.font("黑体",12));
        titleLabel.setTextFill(Color.color(1.0, 0.1, 0.1));

        // 根据不同的用户类型显示不同的界面
        if ((user.getJob().trim()).equals("患者")) {
            TextField patientNameField = new TextField();
            patientNameField.setPromptText("患者姓名");

            TextField patientAgeField = new TextField();
            patientAgeField.setPromptText("患者年龄");

            TextField patientSexField = new TextField();
            patientSexField.setPromptText("患者性别");

            TextField patientPhoneField = new TextField();
            patientPhoneField.setPromptText("患者电话");

            TextField patientIDField = new TextField();
            patientIDField.setPromptText("患者ID");

            TextField patientLoginField = new TextField();
            patientLoginField.setPromptText("患者登录名");

            PasswordField patientPasswordField = new PasswordField();
            patientPasswordField.setPromptText("患者密码");

            Button savePatientButton = new Button("保存");
            savePatientButton.setOnAction(e -> {
                String patientName = patientNameField.getText();
                int patientAge = Integer.parseInt(patientAgeField.getText());
                String patientSex = patientSexField.getText();
                String patientPhone = patientPhoneField.getText();
                String patientID = patientIDField.getText();
                String patientLogin = patientLoginField.getText();
                String patientPassword = patientPasswordField.getText();

                try {
                    Patient updatedPatient = new Patient(patientName, patientAge, patientSex, patientPhone, patientID, patientLogin, patientPassword);
                    baseDao.updatePatientInformation(updatedPatient);
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

                stage.close();
            });

            VBox patientVBox = new VBox(10);
            patientVBox.setPadding(new Insets(20));
            patientVBox.getChildren().addAll(
                    titleLabel, patientNameField, patientAgeField, patientSexField,
                    patientPhoneField, patientIDField, patientLoginField, patientPasswordField, savePatientButton
            );

            vbox.getChildren().add(patientVBox);
        } else if ((user.getJob().trim()).equals("医生")) {
            TextField doctorNumberField = new TextField();
            doctorNumberField.setPromptText("医生编号");

            TextField doctorNameField = new TextField();
            doctorNameField.setPromptText("医生姓名");

            TextField doctorAgeField = new TextField();
            doctorAgeField.setPromptText("医生年龄");

            TextField doctorSexField = new TextField();
            doctorSexField.setPromptText("医生性别");

            TextField doctorPhoneField = new TextField();
            doctorPhoneField.setPromptText("医生电话");

            TextField doctorLoginField = new TextField();
            doctorLoginField.setPromptText("医生登录名");

            PasswordField doctorPasswordField = new PasswordField();
            doctorPasswordField.setPromptText("医生密码");

            TextField departmentNumberField = new TextField();
            departmentNumberField.setPromptText("所属部门编号");

            Button saveDoctorButton = new Button("保存");
            saveDoctorButton.setOnAction(e -> {
                String doctorNumber = doctorNumberField.getText();
                String doctorName = doctorNameField.getText();
                int doctorAge = Integer.parseInt(doctorAgeField.getText());
                String doctorSex = doctorSexField.getText();
                String doctorPhone = doctorPhoneField.getText();
                String doctorLogin = doctorLoginField.getText();
                String doctorPassword = doctorPasswordField.getText();
                String departmentNumber = departmentNumberField.getText();

                try {
                    Doctor_file updatedDoctor = new Doctor_file(doctorNumber, doctorName, doctorAge, doctorSex, doctorPhone, doctorLogin, doctorPassword, departmentNumber);
                    baseDao.updateDoctorInformation(updatedDoctor);
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

                stage.close();
            });

            VBox doctorVBox = new VBox(10);
            doctorVBox.setPadding(new Insets(20));
            doctorVBox.getChildren().addAll(
                    titleLabel, doctorNumberField, doctorNameField, doctorAgeField, doctorSexField,
                    doctorPhoneField, doctorLoginField, doctorPasswordField, departmentNumberField, saveDoctorButton
            );

            vbox.getChildren().add(doctorVBox);
        } else if ((user.getJob().trim()).equals("管理员")) {
            // 管理员界面
            TextField loginField = new TextField();
            loginField.setPromptText("管理员登录名");

            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("管理员密码");

            // 添加保存按钮
            Button saveButton = new Button("保存");
            saveButton.setOnAction(e -> {
                String adminLogin = loginField.getText();
                String adminPassword = passwordField.getText();

                try {
                    Admin updatedAdmin = new Admin(adminLogin, adminPassword);
                    baseDao.updateAdminInformation(updatedAdmin);
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

                stage.close();
            });

            VBox adminVBox = new VBox(10);
            adminVBox.setPadding(new Insets(20));
            adminVBox.getChildren().addAll(
                    titleLabel, loginField, passwordField, saveButton
            );
            vbox.getChildren().add(adminVBox);
        }


        vbox.getChildren().addAll(titleLabel);

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setTitle("更新个人信息");
        stage.setResizable(false);
        stage.show();

    }

    // 增加医生信息
    @FXML
    void onAddScheduleClick() {
        Stage stage = new Stage();
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        Label titleLabel = new Label("患者病房安排");
        titleLabel.setFont(Font.font("黑体", 24));
        titleLabel.setTextFill(Color.color(1.0, 0.1, 0.1));

        TextField number_PField = new TextField();
        number_PField.setPromptText("患者编号");

        TextField number_DField = new TextField();
        number_DField.setPromptText("医生编号");

        TextField number_WField = new TextField();
        number_WField.setPromptText("病房编号");

        // Create a button for adding the doctor
        Button addButton = new Button("确认添加");
        addButton.setOnAction(e -> {
            // Retrieve doctor information from the text fields
            String number_P = number_PField.getText();
            String number_D = number_DField.getText();
            String number_W = number_WField.getText();


            try {
                baseDao.addSchedule(number_P, number_D, number_W);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            // Close the dialog after adding the doctor
            stage.close();
        });

        // Add components to the VBox
        vbox.getChildren().addAll(
                titleLabel, number_PField,number_DField,number_WField, addButton
        );

        Scene scene = new Scene(vbox, 400, 400);
        stage.setScene(scene);
        stage.setTitle("新增医生信息");
        stage.setResizable(false);
        stage.show();
    }

}

