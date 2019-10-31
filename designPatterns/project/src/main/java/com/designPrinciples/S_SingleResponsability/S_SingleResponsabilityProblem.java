package com.designPrinciples.S_SingleResponsability;

import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SuppressWarnings({"SqlNoDataSourceInspection", "SqlDialectInspection"})
public class S_SingleResponsabilityProblem {

    @Getter
    @Setter
    private static class Employee{
        private Integer id;
        private String nome;
        private Double salario;
        private Connection connection;

        public Double calculaSalario() {
            return this.salario - (this.salario * 0.225);
        }

        public void salva() throws SQLException {
            String url = "jdbc:mysql://localhost:3306/empresa?useSSL=false";

            this.connection = DriverManager.getConnection(url, "root", "");
            Statement stmt = this.connection.createStatement();
            String sql = "insert into funcionario (id, nome, salario) values (" + this.id + "," +
                    this.nome + "," + this.salario + ")";
            int rs = stmt.executeUpdate(sql);

            if (rs == 1){
                System.out.println("Employee saved with success.");
            }else if (rs == 0){
                System.out.println("None employee saved.");
            }
        }
    }
}
