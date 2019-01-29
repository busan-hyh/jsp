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
		//������Ʈ�� ������ �ö����� ���ʽ���
		//�׷��Ƿ� ���⿡ �̸� �𵨰�ü�� �ؽ������� �����ΰ� �ʿ��Ҷ� ���� ����.
		
		//properties ���ϰ�� ����
		ServletContext ctx = config.getServletContext();//Ǯ��θ� ���ϱ� ���� �ʿ�
		String path = ctx.getRealPath("/WEB-INF")+"/commendURI.properties";
		
		//properties ������ ���� ��ü ����
		Properties prop = new Properties();//instances�� ���� Map �� �� �ϳ�
		FileInputStream fis = null;
		
		try {
			//properties �б�
			fis = new FileInputStream(path);
			prop.load(fis);
			fis.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//Model Ŭ���� ��ü �����ؼ� instances�� ����
		Iterator it = prop.keySet().iterator();//�ݺ��� ���� (Set�� Array�� �ٸ��� ������ ������ ���� ������ �ݺ��ؼ� Ű�� Ű������ ������ϹǷ�)
		while(it.hasNext()) {
			String k = it.next().toString();//�պκ��� k�� ��
			String v = prop.getProperty(k);//k�� prop�� v�� ��
			
			try {
				Class<?> obj = Class.forName(v);//model��Ű���� HelloŬ������ ã��
				Object instance = obj.newInstance();//�� Ŭ������ ��ü�� �ø�
				
				instances.put(k, instance);//instances�� ������
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
		//�� ������ �ȿ����� ȣ��ǹǷ� �ۺ��� �ƴ� �����̺�����
		String path = req.getContextPath();//������Ʈ���� ��Ʈ(���丮 ch18)���
		String uri = req.getRequestURI();//������Ʈ���� ��û��ξ��(url�� ������!)
		String action = uri.substring(path.length());//ch18 ���丮 �κ� ¥����
		
		//init���� Map�� ���� Ŭ�������� ������
		CommonAction instance = (CommonAction) instances.get(action);
		//������ ��� �θ�Ŭ������ Object�� ��������� �⺻������ Object�� Hello�� "�θ�Ŭ����"�̹Ƿ� �������̽��� Ȱ���ؾ��Ѵ�
		//"�θ�Ŭ����"�� �޼��� ������ �ȵ�?? @Override�ؾ� �ǳ�???
		//!!!�������̽��� �̷� ��쿡 ���δ�!!!
		String view = instance.requestProc(req, resp);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(view);
		dispatcher.forward(req, resp);
		
		
	}
}
