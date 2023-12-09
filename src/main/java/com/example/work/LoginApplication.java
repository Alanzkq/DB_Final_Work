package com.example.work;

import Pojo.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;

//程序登陆入口端   直接从Main读取不到信息会被报错
public class LoginApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("login.fxml"));
        Parent root=FXMLLoader.load(Register.class.getResource("login.fxml"));
        Scene scene = new Scene(root, 617, 432);
        stage.setScene(scene);
        stage.setTitle("智慧医疗管理系统");
        //不可改变大小
        stage.setResizable(false);
        stage.show();
        //设置登录后关闭登陆窗口
        Button login=(Button)root.lookup("#login");
        login.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                System.out.println(LoginController.login);
             if (LoginController.login==true)
             {
                //关闭当前登陆窗口
                 stage.close();
             }
            }
        });

    }


    public static void main(String[] args) {
        launch();
    }
}