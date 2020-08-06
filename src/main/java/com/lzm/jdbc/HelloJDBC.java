package com.lzm.jdbc;

import java.sql.*;

/**
 * jdbc入门案例
 * @author luozhiming
 *
 */
public class HelloJDBC {
	 	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	    //useCursorFetch=true表示使用游标
	    private static final String DB_URL = "jdbc:mysql://localhost:3306/cloud_study?useCursorFetch=true&characterEncoding=utf8";
	    private static final String USER = "root";
	    private static final String PASSWORD = "123456";
	    
	    public static void test() {
	    	Connection con = null;
	    	Statement stmt = null; //现在基本淘汰了，基本多用ps
//	    	PreparedStatement ps = null;
	    	ResultSet rs = null;
	    	try {
				//1.加载数据库驱动
	    		Class.forName(JDBC_DRIVER);
	    		//2.获得数据库连接
	    		con = DriverManager.getConnection(DB_URL,USER,PASSWORD);
	    		//3.创建statement
	    		stmt = con.createStatement();
	    		//4.执行sql语句
	    		rs = stmt.executeQuery("select name from user ");
//	    		ps = con.prepareStatement("select name from user ");
//	    		ps.setFetchSize(1);//设置从数据库取得的行数大小
//	    		rs = ps.executeQuery();
	    		//5.遍历结果
	    		while(rs.next()) {
	    			System.out.println("Hello,"+ rs.getString("name"));
	    		}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				//6.清理资源
				try {
					if(con!=null) 
						con.close();
					if(stmt!=null) 
						stmt.close();
//					if(ps!=null)
//						ps.close();
					if(rs!=null)
						rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
	    }
	    
	    public static void main(String[] args) {
			test();
		}
}
