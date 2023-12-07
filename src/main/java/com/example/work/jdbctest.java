package com.example.work;

import Dao.BaseDao;
import Dao.StringUtil;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;

public class jdbctest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        BaseDao baseDao=new BaseDao();
        Connection connection=baseDao.getCon();
        String sql="select * from Users;";
        Statement statement=connection.createStatement();
        ResultSet rs= statement.executeQuery(sql);
        while (rs.next())
        {
            System.out.println(rs.getString("id")+" "+rs.getString("password"));

        }
    }
}
