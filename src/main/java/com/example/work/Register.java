package com.example.work;

import Dao.StringUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Register extends Application {
    @Override
    public void start(Stage stage) throws Exception {
//        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("register.fxml"));
        Parent root=FXMLLoader.load(Register.class.getResource("register.fxml"));
        ComboBox job=(ComboBox)root.lookup("#job");
        Label label=(Label)root.lookup("#account1");
        job.getItems().addAll("患者","医生","管理员");
        Scene scene = new Scene(root, 615, 514);
        stage.setTitle("用户注册");
        stage.setScene(scene);
        //设置不可改变大小
        stage.setResizable(false);
        stage.show();
    }
}
