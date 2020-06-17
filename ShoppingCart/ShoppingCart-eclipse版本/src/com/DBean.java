package com;

import java.io.PrintWriter;
import java.sql.*;

public class DBean {

    PrintWriter out;
//    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String dbURL = "jdbc:sqlserver://127.0.0.1; DatabaseName=ShoppingCart";
    String dataBaseName = "pwc"; // ���ݿ��û���
    String dataBasePwd = "7023"; // ���ݿ�����

    static Connection conn = null;
    static Statement sqlState = null;   // ������
    static ResultSet sqlRes = null;     // ���������

//    public DBean() {
//        try {
//            Class.forName(driver);   // jdk6֮ǰ�汾��Ҫ����
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    public Connection getconn() {
        try {
            conn = DriverManager.getConnection(dbURL, dataBaseName, dataBasePwd);
        } catch (Exception e) {
            out.println(e.toString());
        }
        return conn;
    }
    
    //�����������������䣺��ѯ��SELSECT
    public ResultSet Query(String sql) {
	try {
            DBean db = new DBean();
            conn = db.getconn();
            sqlState = conn.createStatement();
            sqlRes = sqlState.executeQuery(sql);
        } catch (SQLException e) {
            out.println(e.toString());
        } 
        return sqlRes;
    }
    
    //INSERT��UPDATE��DELETE�����ݶ���
    public int Update(String sql) {
	int result = 0;
	try {
            result = sqlState.executeUpdate(sql);
        } catch (SQLException e) {
            out.println(e.toString());
	}
	return result;
    }
    
    public void closeDB(ResultSet sqlRes) {
        if (sqlRes != null) {
            try {
                if (sqlRes != null) {
                    sqlRes.close();
                }
                if (sqlState != null) {
                    sqlState.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                out.println(e.toString());
            }
        }
    }
}
