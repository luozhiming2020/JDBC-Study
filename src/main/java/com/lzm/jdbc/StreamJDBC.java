package com.lzm.jdbc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  jdbc流方式读取案例
 * @author luozhiming
 *
 */
public class StreamJDBC {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //useCursorFetch=true表示使用游标
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cloud_study?useCursorFetch=true&characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
//    private static final String FILE_URL = "d:\\info.txt";
    private static final String FILE_PREFIX = "D:\\info";
    private static final String FILE_SUFFIX = ".txt";
    
    public static void test() {
    	Connection con = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	InputStream in = null;
    	OutputStream out = null;
    	try {
    		//1.加载数据库驱动
			Class.forName(JDBC_DRIVER);
			//2.获得数据库连接
			con = DriverManager.getConnection(DB_URL,USER,PASSWORD);
			//3.执行SQL语句
			ps = con.prepareStatement("select info from user ");
			rs = ps.executeQuery();
			int i = 1;
			//4.获取执行结果
			while(rs.next()) {
				//5.获取流对象
				in = rs.getBinaryStream("info");
				//6.将流对象写入文件
//				File f = new File(FILE_URL);
				File f = new File(FILE_PREFIX + i + FILE_SUFFIX);
				out = new FileOutputStream(f);
				int temp = 0;
				while ((temp = in.read()) != -1) {//边读边写
                    out.write(temp);
                }
				i++;
			}
    	
    	} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			//清理资源
				try {
					if(con!=null)
						con.close();
					if(ps!=null)
						ps.close();
					if(rs!=null)
						rs.close();
					if(in!=null)
						in.close();
					if(out!=null)
						out.close();
				} catch (SQLException | IOException e) {
					e.printStackTrace();
				}
		}
    }
    
    public static void main(String[] args) {
		test();
	}
}
