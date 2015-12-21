package views;

import java.io.IOException;
import java.util.Scanner;

import controller.server.GrizzlySSLServer;
import controller.server.GrizzlyServer;

public class Main {
	public static void main(String[] args) throws IOException {		
		System.out.println("-x- PiMusicFest's Embedded Server -x-");
		GrizzlyServer.start();
		GrizzlySSLServer.start();
		int action = 0;
		Scanner read = new Scanner(System.in);
		do {
			try {
				System.out.println("[i] Commands: [0:exit]");
				action = read.nextInt();
			} catch (Exception e) {
				System.out.println("[e] " + e.getMessage());
			}
		} while(action!=0);
		read.close();
        GrizzlyServer.stop();
		GrizzlySSLServer.stop();
    }
}
