package org.tain.echo01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	private ServerSocket serverSocket;
	private BufferedReader bufferedReader;
	private PrintWriter printWriter;
	private Socket socket;
	
	public static void main(String[] args) {
		new EchoServer();
	}
	
	public EchoServer() {
		process();
	}
	
	public void process() {
		try {
			this.serverSocket = new ServerSocket(12345);
			System.out.println("Server is ready");
			System.out.println("connect client.....");
			
			this.socket = this.serverSocket.accept();
			System.out.println("client has accepted...");
			
			this.bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.printWriter = new PrintWriter(this.socket.getOutputStream());
			
			String line = "";
			
			while ((line = this.bufferedReader.readLine()) != null) {
				System.out.println("From_Client> " + line);
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
