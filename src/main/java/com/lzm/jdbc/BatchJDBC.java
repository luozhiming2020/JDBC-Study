package com.lzm.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

/**
 * JDBC批处理案例
 */
public class BatchJDBC {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //useCursorFetch=true表示使用游标
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cloud_study?useCursorFetch=true&characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    
    /**
     *	 分批插入数据
     * @throws Exception 
     */
    public static void insert(Set<String> users) {
    	Connection con = null;
    	Statement stmt = null;
    	ResultSet rs = null;
    	//1.加载数据库驱动
		try {
			Class.forName(JDBC_DRIVER);
			//2.获得数据库连接
			con = DriverManager.getConnection(DB_URL,USER,PASSWORD);
			//3.创建statement
			stmt = con.createStatement();
			//4.执行sql语句
			for (String user : users) {
				stmt.addBatch("insert into user(name) values ('" + user 
						+ "')");
			}
			stmt.executeBatch();
			stmt.clearBatch();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(con!=null) 
					con.close();
				if(stmt!=null) 
					stmt.close();
				if(rs!=null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
    }
    public static void main(String[] args) {
    	Set<String> users = new HashSet<String>();
    	users.add("zhangsan");
    	users.add("lisi");
    	users.add("wangwu");
    	insert(users);
    }
}
