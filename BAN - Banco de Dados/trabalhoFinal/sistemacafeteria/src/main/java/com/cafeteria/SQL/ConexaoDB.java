package com.cafeteria.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

    private static Connection conexao;
    
    private ConexaoDB() {}

    public static Connection getInstancia() {
        try {
            if (conexao == null || conexao.isClosed()) {
                conexao = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5433/cafeteria?characterEncoding=UTF-8",
                    "postgres", "1234"
                );
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return conexao;
    }
}