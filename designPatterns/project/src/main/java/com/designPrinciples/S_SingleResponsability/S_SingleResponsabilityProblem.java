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
        private String name;
        private Double salary;
        private Connection connection;

        public Double calculateSalary() {
            return this.salary - (this.salary * 0.225);
        }

        public void save() throws SQLException {
            String url = "jdbc:mysql://localhost:3306/company?useSSL=false";

            this.connection = DriverManager.getConnection(url, "root", "");
            Statement stmt = this.connection.createStatement();
            String sql = "insert into employee (id, name, salary) values (" + this.id + "," +
                    this.name + "," + this.salary + ")";
            int rs = stmt.executeUpdate(sql);

            if (rs == 1){
                System.out.println("Employee saved with success.");
            }else if (rs == 0){
                System.out.println("None employee saved.");
            }
        }
    }
}
