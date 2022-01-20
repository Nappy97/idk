package com.quietjun.intellij.registerform.member.study;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NaverDAO {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public Connection getConn() {
        String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
        String user = "hanul";
        String password = "0000";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getConn() Exception!!!");
        }
        return conn;
    }

    public boolean joinMember(NaverDTO dto) {
        conn = getConn();
        String sql = "INSERT INTO boardMember VALUES (?, ?, ?, ?, ?, ?)";
        int succ = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getMember_id());
            ps.setString(2, dto.getMember_pw());
            ps.setString(3, dto.getMember_name());
            ps.setString(5, dto.getMember_gender());
            ps.setString(6, dto.getMember_email());
            succ = ps.executeUpdate();
            if (succ != 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("joinMember() Exception!!!");
        }
        return false;
    }

    public int isMember(NaverDTO dto) {
        conn = getConn();
        String sql = "SELECT member_pw FROM boardMember WHERE member_id = ?";
        int result = -1;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getMember_id());
            rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getString("member_pw").equals(dto.getMember_pw())) {
                    result = 1;
                } else {
                    result = 0;
                }
            } else {
                result = -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("isMember() Exception!!!");
        }
        return result;
    }
}


