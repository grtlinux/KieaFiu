package org.tain.echo01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

public class EchoClient {

	private Socket socket;
	private BufferedReader bufferedReader;
	private PrintWriter printWriter;
	private Scanner scanner;
	
	private Properties properties;
	
	public static void main(String[] args) throws Exception {
		new EchoClient();
	}
	
	public EchoClient() throws Exception {
		this.properties = new Properties();
		this.properties.load(new FileInputStream("./echo.properties"));
		process();
	}
	
	public void process() {
		try {
			this.socket = new Socket("localhost", 12345);
			System.out.println("Server connect..." + String.valueOf(this.properties.getProperty("name")));
			
			this.bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.printWriter = new PrintWriter(this.socket.getOutputStream());
			this.scanner = new Scanner(System.in);
			
			String line = "";
			
			while (!line.equalsIgnoreCase("EXIT")) {
				System.out.print(String.valueOf(this.properties.get("client.prompt")) + " ");
				line = this.scanner.next();
				this.printWriter.println(line);
				this.printWriter.flush();
				
				System.out.println("From_Server: " + this.bufferedReader.readLine());
			}
			this.socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
