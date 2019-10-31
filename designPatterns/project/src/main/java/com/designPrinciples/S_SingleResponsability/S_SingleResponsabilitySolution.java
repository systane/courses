package com.designPrinciples.S_SingleResponsability;

import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@SuppressWarnings({"SqlNoDataSourceInspection", "SqlDialectInspection"})
public class S_SingleResponsabilitySolution {

    @Getter
    @Setter
    private static class ConnectionDAO{
        private Properties connectionProps;
        private Connection conn;
        private String dbms;
        private String dbName;
        private String serverName;
        private String portNumber;

        private static final String JDBC = "jdbc:";

        private ConnectionDAO (String username, String password){
            super();
            connectionProps = new Properties();
            connectionProps.put("user", username);
            connectionProps.put("password", password);
        }

        private Connection createConnection() {
            Connection newConnection = null;
            try {

                if (getDbms().equals("mysql")) {
                    newConnection = DriverManager.getConnection(JDBC + getDbms() + "://" + getServerName() + ":" + getPortNumber()
                            + "/" + getDbName() + "?useSSL=false", getConnectionProps());
                }
                setConn(newConnection);
            } catch (SQLException e) {
                System.err.println(e);
            }

            return newConnection;
        }
    }

    private static class EmployeeDAO {

        public void save(Employee funcionario) throws SQLException {
            ConnectionDAO connectionDAO = new ConnectionDAO("root", "");
            connectionDAO.setDbms("mysql");
            connectionDAO.setServerName("localhost");
            connectionDAO.setPortNumber("8080");
            connectionDAO.setDbName("mock");

            try (Connection connection = connectionDAO.createConnection();
                 Statement stmt = connection.createStatement()) {

                String sql = "insert into employee (id, name, salary) values (" + funcionario.getId() + "," +
                        funcionario.getName() + "," + funcionario.getSalary() + ")";
                int rs = stmt.executeUpdate(sql);

                if (rs == 1) {
                    System.out.println("Employee saved with success.");
                }
            } catch (SQLException e) {
                System.err.println("one employee saved." + e);
            }
        }
    }

    @Getter
    private enum Role {
        DEVELOPER_SENIOR(new RegraVinteDoisEMeioPorcento()),
        DEVELOPER_JUNIOR(new RegraOnzePorcento());

        private RegraDeCalculo rule;

        Role(RegraDeCalculo rule){
            this.rule = rule;
        }
    }

    private interface RegraDeCalculo {
        double calculate (Employee employee);
    }

    private static class RegraVinteDoisEMeioPorcento implements RegraDeCalculo{
        @Override
        public double calculate(Employee employee) {
            return employee.getSalary() - (employee.getSalary() * 0.225);
        }
    }

    private static class RegraOnzePorcento implements RegraDeCalculo{
        @Override
        public double calculate(Employee employee) {
            return employee.getSalary() - (employee.getSalary() * 0.11);
        }
    }

    @Getter
    @Setter
    private static class Employee{
        private Integer id;
        private String name;
        private Double salary;
        private Role role;

        public double calculateSalary() {
            return role.getRule().calculate(this);
        }
    }
}
