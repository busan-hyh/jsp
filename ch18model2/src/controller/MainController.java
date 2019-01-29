package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Greeting;
import model.Hello;
import model.Introduce;
import model.Welcome;

public class MainController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6702672335405420505L;
	private Map<String, Object> instances = new HashMap<>();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		//프로젝트가 서버에 올라갔을떄 최초실행
		//그러므로 여기에 미리 모델객체를 해쉬맵으로 만들어두고 필요할때 꺼내 쓴다.
		
		//properties 파일경로 추출
		ServletContext ctx = config.getServletContext();//풀경로를 구하기 위해 필요
		String path = ctx.getRealPath("/WEB-INF")+"/commendURI.properties";
		
		//properties 정보를 통한 객체 생성
		Properties prop = new Properties();//instances에 들어가는 Map 속 값 하나
		FileInputStream fis = null;
		
		try {
			//properties 읽기
			fis = new FileInputStream(path);
			prop.load(fis);
			fis.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//Model 클래스 객체 생성해서 instances에 저장
		Iterator it = prop.keySet().iterator();//반복자 생성 (Set은 Array와 다르게 꺼내는 순서가 없고 무조건 반복해서 키는 키셋으로 만들어하므로)
		while(it.hasNext()) {
			String k = it.next().toString();//앞부분이 k가 됨
			String v = prop.getProperty(k);//k의 prop이 v가 됨
			
			try {
				Class<?> obj = Class.forName(v);//model패키지의 Hello클래스를 찾음
				Object instance = obj.newInstance();//이 클래스를 객체로 올림
				
				instances.put(k, instance);//instances에 저장함
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		requestProc(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		requestProc(req, resp);
	}
	
	private void requestProc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//이 페이지 안에서만 호출되므로 퍼블릭이 아닌 프라이빗으로
		String path = req.getContextPath();//리퀘스트에서 루트(디렉토리 ch18)얻기
		String uri = req.getRequestURI();//리퀘스트에서 요청경로얻기(url은 도메인!)
		String action = uri.substring(path.length());//ch18 디렉토리 부분 짜르기
		
		//init에서 Map에 담은 클래스파일 꺼내기
		CommonAction instance = (CommonAction) instances.get(action);
		//위에서 모든 부모클래스인 Object로 만들었지만 기본적으로 Object는 Hello의 "부모클래스"이므로 인터페이스를 활용해야한다
		//"부모클래스"는 메서드 공유가 안됨?? @Override해야 되나???
		//!!!인터페이스는 이런 경우에 쓰인다!!!
		String view = instance.requestProc(req, resp);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(view);
		dispatcher.forward(req, resp);
		
		
	}
}
