package org.zerock.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectTests {

    private Connection conn;

    @Test
    public void test1(){
        int v1=10;
        int v2=10;

        Assertions.assertEquals(v1,v2);
    }


    @Test
    public void testConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");

        conn=DriverManager.getConnection("jdbc:mariadb://localhost:3307/webdb", "webuser", "webuser");

        System.out.println("연결 객체 : " +conn);
        Assertions.assertNotNull(conn);
        conn.close();
    }



    @Test
    public void testHikariCP() throws Exception{

        HikariConfig config = new HikariConfig();

        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://localhost:3307/webdb");
        config.setUsername("webuser");
        config.setPassword("webuser");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource ds = new HikariDataSource(config);

        Connection connection = ds.getConnection();

        System.out.println("커넥션풀 연결 객체 : " + connection);

//        ds.close();
        connection.close();
    }











}
