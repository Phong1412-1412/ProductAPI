package com.example.day4_productmvc.config;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class CheckConfig {
    private DataSource dataSource;

    public CheckConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean checkConnectDB(DataSource dataSource) throws SQLException {
        boolean isConnect = false;
        try(Connection connection = dataSource.getConnection()) {
            isConnect = true;
        }
        catch (Exception e) {
            throw new SQLException("Lỗi khi kết nối đến database: "+e);
        }
        return isConnect;
    }
}
