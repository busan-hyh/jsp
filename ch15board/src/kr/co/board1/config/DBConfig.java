package kr.co.board1.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfig {
	//:3306/hyh?noAccessToProcedureBodies=true 는 프로시저 실행을 위해 필요한듯?? 스프링에서 쓰임!
	private static final String HOST = "jdbc:mysql://192.168.0.126:3306/hyh";
	private static final String USER = "hyh";
	private static final String PASS = "1234";
	
	public static Connection getConnection() throws Exception {
		//try catch를 해도 되고 throws 선언을 해도 된다. ▲권장
		//static으로 하면 한번만 불러오면 메모리에 상주한다(클래스/힙중에 클래스에) HOST등도 static 선언해줘야함
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection(HOST, USER, PASS);
		
		return conn;
	}
}
