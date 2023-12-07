package com.example.work;

import Pojo.NewsItem;
import Pojo.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
//        Parent root=FXMLLoader.load(Register.class.getResource("Main.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 898, 621);
        //获取用户信息
        User user=User.getUser();
//        System.out.println(user.getJob());
        //获取Text标签
        Text name= (Text) root.lookup("#name");
        Text account=(Text)root.lookup("#account");
        Text age=(Text)root.lookup("#age");
        //设置标签值
        name.setText(user.getName());
        account.setText(user.getAccount());
        age.setText(String.valueOf(user.getAge()));

        //设置患者端 修改医生/药品/处方信息按钮不可见 不可修改这些信息

        Button manage_doctor= (Button) root.lookup("#manage_doctor");
        Button manage_patient= (Button) root.lookup("#manage_patient");
        Button manage_drug=(Button)root.lookup("#manage_drug");
        Button manage_prescription=(Button)root.lookup("#manage_prescription");
        Button add_reservation= (Button) root.lookup("#add_reservation");
        Button cancel_reservation= (Button) root.lookup("#cancel_reservation");
        Button manage_ward= (Button) root.lookup("#manage_ward");
        Button manage_department= (Button) root.lookup("#manage_department");
        Button show_patient= (Button) root.lookup("#show_patient");


        MainController mainController = loader.getController();
        mainController.show_table();
        //通过判断job属性来区分客户端显示类型   防止跳过登陆入口直接进入系统
        if ((user.getJob()).trim().equals("患者"))
        {
            stage.setTitle("智慧医疗管理系统(患者端)");
            manage_doctor.setVisible(false);
            manage_patient.setVisible(false);
            manage_drug.setVisible(false);
            manage_prescription.setVisible(false);
            manage_ward.setVisible(false);
            manage_department.setVisible(false);
            show_patient.setVisible(false);


        }else if ((user.getJob().trim()).equals("医生"))
        {

            manage_doctor.setVisible(false);
            cancel_reservation.setVisible(false);
            stage.setTitle("智慧医疗管理系统(医生端)");
        }else {

            add_reservation.setVisible(false);
            cancel_reservation.setVisible(false);
            stage.setTitle("智慧医疗管理系统(管理员端)");
        }
        //为退出按钮绑定事件 退回登陆界面
        Button Exit_System=(Button)root.lookup("#exit");
        Exit_System.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
                LoginApplication loginApplication=new LoginApplication();
                try {
                    loginApplication.start(new Stage());
                    System.out.println("退出成功");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


}
