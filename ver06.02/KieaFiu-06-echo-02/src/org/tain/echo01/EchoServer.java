package org.tain.echo01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class EchoServer {

	private ServerSocket serverSocket;
	private BufferedReader bufferedReader;
	private PrintWriter printWriter;
	private Socket socket;
	
	private Properties properties;
	
	public static void main(String[] args) throws Exception {
		new EchoServer();
	}
	
	public EchoServer() throws Exception {
		this.properties = new Properties();
		this.properties.load(new FileInputStream("./echo.properties"));
		process();
	}
	
	public void process() {
		try {
			this.serverSocket = new ServerSocket(12345);
			System.out.println("Server is ready");
			System.out.println("connect client....." + String.valueOf(this.properties.getProperty("name")));
			
			this.socket = this.serverSocket.accept();
			System.out.println("client has accepted...");
			
			this.bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.printWriter = new PrintWriter(this.socket.getOutputStream());
			
			String line = "";
			
			while ((line = this.bufferedReader.readLine()) != null) {
				System.out.println(String.valueOf(this.properties.get("server.prompt")) + " " + line);
				this.printWriter.println(line);
				this.printWriter.flush();
				if (line.equalsIgnoreCase("EXIT"))
					break;
			}
			this.socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
