package kr.co.board1.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfig {
	//:3306/hyh?noAccessToProcedureBodies=true �� ���ν��� ������ ���� �ʿ��ѵ�?? ���������� ����!
	private static final String HOST = "jdbc:mysql://192.168.0.126:3306/hyh";
	private static final String USER = "hyh";
	private static final String PASS = "1234";
	
	public static Connection getConnection() throws Exception {
		//try catch�� �ص� �ǰ� throws ������ �ص� �ȴ�. �����
		//static���� �ϸ� �ѹ��� �ҷ����� �޸𸮿� �����Ѵ�(Ŭ����/���߿� Ŭ������) HOST� static �����������
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection(HOST, USER, PASS);
		
		return conn;
	}
}
