package org.tain.echo01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {

	private Socket socket;
	private BufferedReader bufferedReader;
	private PrintWriter printWriter;
	private Scanner scanner;
	
	public static void main(String[] args) {
		new EchoClient();
	}
	
	public EchoClient() {
		process();
	}
	
	public void process() {
		try {
			this.socket = new Socket("localhost", 12345);
			System.out.println("Server connect");
			
			this.bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.printWriter = new PrintWriter(this.socket.getOutputStream());
			this.scanner = new Scanner(System.in);
			
			String line = "";
			
			while (!line.equalsIgnoreCase("EXIT")) {
				System.out.print("TO_Server: ");
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
