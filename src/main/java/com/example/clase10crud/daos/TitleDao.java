package com.example.clase10crud.daos;

import com.example.clase10crud.beans.Employee;
import com.example.clase10crud.beans.Title;

import java.sql.*;
import java.util.ArrayList;

public class TitleDao {
    private static final String username = "root";
    private static final String password = "root";

    public ArrayList<Title> list(int limit, int offset){

        ArrayList<Title> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/employees";

        // TODO: update query
        String sql = "select * from titles limit ? offset ?";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,limit);
            pstmt.setInt(2,offset);
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    Title title = new Title();
                    title.setEmpNo(rs.getInt(1));
                    title.setTitle(rs.getString(2));
                    title.setFromDate(rs.getString(3));
                    title.setToDate(rs.getString(4));

                    lista.add(title);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public void create(Title title){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/employees";

        String sql = "insert into titles (emp_no, title, from_date, to_date) values(?,?,?,?)";

        try(Connection conn = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1,title.getEmpNo());
            pstmt.setString(2,title.getTitle());
            pstmt.setString(3,title.getFromDate());
            pstmt.setString(4,title.getToDate());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Title buscarPorId(String empNo,String title1, String fromDate){

        Title title = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/employees";

        String sql = "select * from titles where emp_no = ? & title = ? & from_date=?";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,empNo);
            pstmt.setString(2,title1);
            pstmt.setString(3,fromDate);

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    title = new Title();
                    title.setEmpNo(rs.getInt(1));
                    title.setTitle(rs.getString(2));
                    title.setFromDate(rs.getString(3));
                    title.setToDate(rs.getString(4));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return title;
    }

    public void actualizar(Title title){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/employees";

        String sql = "update titles set to_date=? where emp_no = ? & title = ? & from_date=?";

        try(Connection conn = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1,title.getToDate());
            pstmt.setInt(2,title.getEmpNo());
            pstmt.setString(3,title.getTitle());
            pstmt.setString(4,title.getFromDate());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void borrar(String employeeNo, String title, String fromDate) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/employees";

        String sql = "delete from titles where emp_no = ? & title = ? & from_date=?";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,employeeNo);
            pstmt.setString(2,title);
            pstmt.setString(3,fromDate);
            pstmt.executeUpdate();
        }
    }
}
