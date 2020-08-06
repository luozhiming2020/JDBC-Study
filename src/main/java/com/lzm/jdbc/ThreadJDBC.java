package com.lzm.jdbc;

/**
 * 通过线程执行dbcp
 * @author luozhiming
 *
 */
public class ThreadJDBC {
	
	/**
	 * 只使用jdbc
	 */
	public static void jdbcTest() {
		long start = System.currentTimeMillis();
		//10秒内不断查询
		while(System.currentTimeMillis() - start < 10000) {
			try {
				HelloJDBC.test();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 用dbcp
	 */
	public static void dbcpTest() {
		long start = System.currentTimeMillis();
		//10秒内不断查询
		while(System.currentTimeMillis() - start < 10000) {
			try {
				DbcpJDBC.dbPoolTest();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		DbcpJDBC.dbToolInit();
        for(int i=0;i<10;i++){
            Thread thread = new Thread(() -> {
                jdbcTest();
//                dbcpTest();
            });
            thread.start();
        }
	}
}
