package com.example.work;

import Dao.BaseDao;
import Dao.StringUtil;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterController {
    private BaseDao baseDao=new BaseDao();
    @FXML
    private ComboBox job=new ComboBox<>();
    @FXML
    private TextField account;
    @FXML
    private TextField name;
    @FXML
    private PasswordField password;
    @FXML
    private TextField age;
    @FXML
    void onregisterclick() throws SQLException, ClassNotFoundException {
        //获取身份列表
//        ObservableList items = job.getItems();  //返回一个列表
        String jobstr= (String) job.getSelectionModel().selectedItemProperty().getValue();
//        System.out.println(job.getSelectionModel().selectedItemProperty().getValue());
        //获取各个信息
        //先判断是否有空
        if (StringUtil.isEmpty(account.getText())||StringUtil.isEmpty(name.getText())||StringUtil.isEmpty(password.getText())||StringUtil.isEmpty(age.getText())||StringUtil.isEmpty(jobstr))
        {
//            System.out.println("有空值");
            Dialog<ButtonType> warning = new Dialog<>();
            warning.getDialogPane().getButtonTypes().add(new ButtonType("确认", ButtonBar.ButtonData.OK_DONE));
            warning.setTitle("注册失败");
            warning.setContentText("请完整填写信息");
            warning.show();
            return;
        }
        String idstr=account.getText();
        String namestr=name.getText();
        String passwordstr=password.getText();
        String agestr=age.getText();
        //先判断账号是否被注册
        ResultSet rs = baseDao.searchUsers();
        while (rs.next())
        {
            if ((idstr.trim()).equals((rs.getString("account").trim())))
            {
                //如果有重复的账号
                Dialog<ButtonType> warning = new Dialog<>();
                warning.getDialogPane().getButtonTypes().add(new ButtonType("确认", ButtonBar.ButtonData.OK_DONE));
                warning.setTitle("注册失败");
                warning.setContentText("该账号已被注册");
                warning.show();
                return;
            }
        }
        //调用dao中方法创建新用户
        // 这里应该还需要进行添加用户到数据库中
        boolean status=baseDao.addNewUser(idstr,namestr,passwordstr,Integer.parseInt(agestr),jobstr);
        if (status==true)
        {
            Dialog<ButtonType> warning = new Dialog<>();
            warning.getDialogPane().getButtonTypes().add(new ButtonType("确认", ButtonBar.ButtonData.OK_DONE));
            warning.setTitle("注册成功");
            warning.setContentText("注册成功");
            warning.show();
            //清空所有文本框 防止重复注册
            account.clear();
            name.clear();
            password.clear();
            age.clear();
        }

    }
}
