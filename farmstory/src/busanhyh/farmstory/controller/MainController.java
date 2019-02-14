package busanhyh.farmstory.controller;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
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

public class MainController extends HttpServlet {
	
	
	private Map<String, Object> instances = new HashMap<>();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		//init�� �ִ¾ֵ��� �����ϸ� init�� ����Ǿ�� ������ �ݿ��ǹǷ� ������ �����Ѿ���
		ServletContext ctx = config.getServletContext();
		String path = ctx.getRealPath("/WEB-INF")+"/commandURI.properties";
		
		Properties prop = new Properties();
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(path);
			prop.load(fis);
			fis.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Iterator it = prop.keySet().iterator();
		
		while(it.hasNext()) {
			
			String k = it.next().toString();
			String v = prop.getProperty(k);
			
			try {
				Class<?> obj = Class.forName(v);
				Object instance = obj.newInstance();
				
				instances.put(k, instance);
				
			}catch(Exception e) {
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
		String path = req.getContextPath();
		String uri  = req.getRequestURI();
		String action = uri.substring(path.length());
		
		CommonAction instance = (CommonAction) instances.get(action);
		String result = null;
		try {
			result = instance.requestProc(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result.startsWith("redirect:")) {
			//�����̷�Ʈ ���ڿ��� result�� ������ ���
			String redirectAddr = result.substring(9);//redirect: �κ� �ڸ���
			resp.sendRedirect(redirectAddr);
		} else if(result.startsWith("{")) {
			PrintWriter out = resp.getWriter();
			out.print(result);
		} else {
			//�Ϲ����� ~~.do�� ���� ~~.jsp�� result�� ���� ���
			RequestDispatcher dispatcher = req.getRequestDispatcher(result);
			dispatcher.forward(req, resp);
		}
	}
}