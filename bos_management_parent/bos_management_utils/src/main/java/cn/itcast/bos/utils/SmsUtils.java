package cn.itcast.bos.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 调用吉信通 发短信工具类
 */
public class SmsUtils {
	/**
	 * 调用 WebService 协议方式发送短信
	 * 
	 * @param mobiles
	 * @param msg
	 * @return
	 */
	public static String sendSmsByWebService(String mobiles, String msg) {
		String result = "-12";
		try {
			Document doc;
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			loadUseridAndPassword();
			
			InputStream is = getSoapInputStream(userid, password, mobiles, msg, "");
			if (is != null) {
				doc = db.parse(is);
				NodeList nl = doc.getElementsByTagName("SendMessagesResult");
				Node n = nl.item(0);
				result = n.getFirstChild().getNodeValue();
				is.close();
			}
			return result;
		} catch (Exception e) {
			System.out.print("SmsSoap.sendSms error:" + e.getMessage());
			return "-12";
		}
	}

	private static String getSoapSmssend(String userid, String pass,
			String mobiles, String msg, String time) {
		try {
			String soap = "";
			soap = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
					+ "<soap:Body>"
					+ "<SendMessages xmlns=\"http://tempuri.org/\">" + "<uid>"
					+ userid + "</uid>" + "<pwd>" + pass + "</pwd>" + "<tos>"
					+ mobiles + "</tos>" + "<msg>" + msg + "</msg>" + "<otime>"
					+ time + "</otime>" + "</SendMessages>" + "</soap:Body>"
					+ "</soap:Envelope>";
			return soap;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private static InputStream getSoapInputStream(String userid, String pass,
			String mobiles, String msg, String time) throws Exception {
		URLConnection conn = null;
		InputStream is = null;
		try {
			String soap = getSoapSmssend(userid, pass, mobiles, msg, time);
			if (soap == null) {
				return null;
			}
			try {

				URL url = new URL("http://service2.winic.org:8003/Service.asmx");

				conn = url.openConnection();
				conn.setUseCaches(false);
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setRequestProperty("Content-Length",
						Integer.toString(soap.length()));
				conn.setRequestProperty("Content-Type",
						"text/xml; charset=utf-8");
				conn.setRequestProperty("HOST", "service2.winic.org");
				conn.setRequestProperty("SOAPAction",
						"\"http://tempuri.org/SendMessages\"");

				OutputStream os = conn.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
				osw.write(soap);
				osw.flush();
			} catch (Exception ex) {
				System.out.print("SmsSoap.openUrl error:" + ex.getMessage());
			}
			try {
				is = conn.getInputStream();
			} catch (Exception ex1) {
				System.out.print("SmsSoap.getUrl error:" + ex1.getMessage());
			}

			return is;
		} catch (Exception e) {
			System.out.print("SmsSoap.InputStream error:" + e.getMessage());
			return null;
		}
	}
	
	private static void loadUseridAndPassword(){
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(new File("E:/private/sms.properties")));
			userid = (String) properties.get("userid");
			password = (String) properties.get("password");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String userid;
	private static String password;
	
	public static void main(String[] args) {
		SmsUtils.sendSmsByWebService("13853155211", "尊敬的用户您好，本次获取的验证码为：4321，服务电话：4006184000【传智播客】");
	}
}
