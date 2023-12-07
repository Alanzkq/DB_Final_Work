package com.example.work;

import Dao.BaseDao;
import Dao.StringUtil;
import Pojo.User;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {

    @FXML  //账号输入文本框
    protected TextField account;
    @FXML //密码输入文本框
    protected PasswordField password;
    @FXML //密码输入错误提示
    protected Label errorlabel;
    private BaseDao baseDao = new BaseDao();

    @FXML
    //登陆按钮事件
   protected void onloginclick() throws Exception {
        //将错误信息清除
        errorlabel.setText("");
        boolean loginstaus=false;
        //先判断输入是否为空
        if (StringUtil.isEmpty(account.getText())||StringUtil.isEmpty(password.getText()))
        {
            //如果有一个输入为空 就返回false
            //关闭登陆窗口
            Dialog<ButtonType> warning = new Dialog<>();
            warning.getDialogPane().getButtonTypes().add(new ButtonType("确认", ButtonBar.ButtonData.OK_DONE));
            warning.setTitle("账号或密码不能为空");
            warning.setContentText("账号或密码不能为空");
            warning.show();
            return;
        }
       //查询数据库
        ResultSet rs = baseDao.searchUsers();
        while (rs.next())
        {
//            System.out.println(rs.getString(1).equals(account.getText())&&rs.getString(2).equals(password.getText()));
//            String accountValue = rs.getString(1);
//            String passwordValue = rs.getString(2);
//            System.out.println("Account: " + accountValue.length());
//            System.out.println("Passwd: " + passwordValue.length());
//            System.out.println("Account_put: " + account.getText().length());
//            System.out.println("Passwd_put: " + password.getText().length());

            // 注意这里的判断相等需要保证数据库里的变量类型与这里实际一致，有可能会有空格，调了6.5h的bug
            if ((rs.getString(1).trim()).equals((account.getText()).trim()))
            {
                if ((rs.getString(5).trim()).equals(password.getText().trim()))
                {
                    //如果输入均正确
                    System.out.println("登陆成功");
                    loginstaus=true;
                    login=true;
                    User user=User.getUser();
                    user.setAccount(rs.getString(1));
                    user.setName(rs.getString(2));
                    user.setAge((Integer) rs.getObject(3));
                    user.setJob(rs.getString(4));
                    user.setPassword(rs.getString(5));
                    Main main=new Main();
                    main.start(new Stage());
                    break;
                }

            }
        }
        if (loginstaus==false)
        {
            errorlabel.setText("账号或密码输入错误,请重新输入");
            System.out.println("登陆失败");
        }
    }
    @FXML
    void onregisterclick() throws Exception {
        Register register=new Register();
        register.start(new Stage());
    }

    public static boolean login=false;
}