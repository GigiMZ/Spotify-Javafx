package com.example.spotify;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
public class DBInit {
    private String email;
    private String username;
    private String password_hash;
    private String date;

    public DBInit(String email, String username, String password, String date) {
        this.email = email;
        this.username = username;
        this.password_hash = password;
        this.date = date;
    }

    public DBInit() {}

    public void addToDB() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_jdbc", "root", "E=mc2");
            Statement stm = conn.createStatement();
            stm.executeUpdate("CREATE TABLE IF NOT EXISTS users(" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "email VARCHAR(80)," +
                    "username VARCHAR(30)," +
                    "password_hash VARCHAR(64)," +
                    "date VARCHAR(10));");
            stm.close();

            PreparedStatement prpstmt = conn.prepareStatement("INSERT INTO users (email, username, password_hash, date) VALUES(?, ?, ?, ?)");
            prpstmt.setString(1, this.email);
            prpstmt.setString(2, this.username);
            prpstmt.setString(3, this.password_hash);
            prpstmt.setString(4, this.date);
            prpstmt.executeUpdate();
            prpstmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean checkIfUserExists(String username) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_jdbc", "root", "E=mc2");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT username FROM users");
            while (rs.next()) {
                if (rs.getString(1).equals(username)) return true;
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage()+"1");
        }
        return false;
    }

    public static boolean PasswordMatch(String username, String password_hash) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_jdbc", "root", "E=mc2");
            PreparedStatement prpstmnt = conn.prepareStatement("SELECT password_hash FROM users WHERE username = ?");
            prpstmnt.setString(1, username);
            ResultSet rs = prpstmnt.executeQuery();
            if (rs.next() && rs.getString(1).equals(password_hash)) {
                rs.close();
                prpstmnt.close();
                conn.close();
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage()+"2");
        }
        return false;
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
