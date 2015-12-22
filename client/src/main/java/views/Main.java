package views;

import java.util.Scanner;

import controller.service.ArtistAPI;
import controller.service.AuthenticationAPI;
import controller.service.LineUpAPI;
import controller.service.SponsorAPI;
import controller.service.UserAPI;
import model.Artist;
import model.LineUp;
import model.Sponsor;
import model.User;

public class Main {

	public static void main(String[] args) {
        System.out.println("-x- PiMusicFest's Test Client -x-");
		//Login
		String username = "Admin";
		String password = "PiPass";
		
		//Selection
		int action = 0;
		int path = 0;
		String _id = "";
		
		//"Currently working with" objects
		Artist currentArtist = null;
		Sponsor currentSponsor = null;
		User currentUser = null;
		LineUp currentLineUp = null;
		
		Scanner read = new Scanner(System.in);
		do {
			try {
				System.out.println("[i] Commands: [1:authentication] [2:artist] [3:sponsor] [4:user] [5:lineup] --- [0:exit]");
				path = read.nextInt();
				
				_id = "";
				
				if (path==1) {
					System.out.println("[i] Authentication: [1:signup] [2:signin] [3:signout]");
					action = read.nextInt();
					if (action==1) {
						System.out.println("[?] Username:"); username = read.next();
						System.out.println("[?] Password:"); password = read.next();
						AuthenticationAPI.signup(username, password);
					} //Register
					if (action==2) { 
						if (AuthenticationAPI.signin(username, password)) { System.out.println("[i] Signed In"); }
						else { System.out.println("[i] Access Denied"); }
					} // Login
					if (action==3) { 
						AuthenticationAPI.signout();
						System.out.println("[i] Signed Out");
					} //Logout
				}
				if (path==2) {
					System.out.println("[i] Artist: [1:list] [2:create] [3:read] [4:update] [5:delete]");
					action = read.nextInt();
					if (action==1) { 
						Artist[] artists = ArtistAPI.list();
						for(int i=0; i<artists.length; i++) { System.out.println("[<] " + artists[i].toString()); }	
					}
					if (action==2) { 
						currentArtist = new Artist("Of Monsters And Men", "", 1, "Main Stage", "12/07/2015", "00:10", "02:00");
						ArtistAPI.create(currentArtist);
					}
					if (action==3) { 
						System.out.println("[?] _id:"); _id = read.next();
						currentArtist = ArtistAPI.read(_id);
						System.out.println("[<] " + currentArtist.toString());
					}
					if (action==4) { 
						System.out.println("[i] **Work un progress**"); 
						ArtistAPI.update(currentArtist);
					}
					if (action==5) {
						System.out.println("[?] _id:"); _id = read.next();
						ArtistAPI.delete(_id);
					}
				}
				if (path==3) {
					System.out.println("[i] Sponsor: [1:list] [2:create] [3:read] [4:update] [5:delete]");
					action = read.nextInt();
					if (action==1) { 
						Sponsor[] sponsors = SponsorAPI.list();
						for(int i=0; i<sponsors.length; i++) { System.out.println("[<] " + sponsors[i].toString()); }	
					}
					if (action==2) { 
						currentSponsor = new Sponsor("Spotify", "");
						SponsorAPI.create(currentSponsor);
					}
					if (action==3) { 
						System.out.println("[?] _id:"); _id = read.next();
						currentSponsor = SponsorAPI.read(_id);
						System.out.println("[<] " + currentSponsor.toString());
					}
					if (action==4) { 
						System.out.println("[i] **Work un progress**"); 
						SponsorAPI.update(currentSponsor);
					}
					if (action==5) {
						System.out.println("[?] _id:"); _id = read.next();
						SponsorAPI.delete(_id);
					}
				}
				if (path==4) {
					System.out.println("[i] User: [1:list] [2:create] [3:read] [4:update] [5:delete]");
					action = read.nextInt();
					if (action==1) { 
						User[] users = UserAPI.list();
						for(int i=0; i<users.length; i++) { System.out.println("[<] " + users[i].toString()); }	
					}
					if (action==2) { 
						currentUser = new User("Antonio","ANTONIOOO!","user");
						UserAPI.create(currentUser);
					}
					if (action==3) { 
						System.out.println("[?] _id:"); _id = read.next();
						currentUser = UserAPI.read(_id);
						System.out.println("[<] " + currentUser.toString());
					}
					if (action==4) { 
						System.out.println("[i] **Work un progress**"); 
						UserAPI.update(currentUser);
					}
					if (action==5) {
						System.out.println("[?] _id:"); _id = read.next();
						UserAPI.delete(_id);
					}
				}
				if (path==5) {
					System.out.println("[i] LineUp: [1:list] [2:create] [3:read] [4:update] [5:delete]");
					action = read.nextInt();
					if (action==1) { 
						LineUp[] lineups = LineUpAPI.list();
						for(int i=0; i<lineups.length; i++) { System.out.println("[<] " + lineups[i].toString()); }	
					}
					if (action==2) { 
						currentLineUp = new LineUp();
						LineUpAPI.create(currentLineUp);
					}
					if (action==3) { 
						System.out.println("[?] _id:"); _id = read.next();
						currentLineUp = LineUpAPI.read(_id);
						System.out.println("[<] " + currentLineUp.toString());
					}
					if (action==4) { 
						System.out.println("[i] **Work un progress**"); 
						LineUpAPI.update(currentLineUp);
					}
					if (action==5) {
						System.out.println("[?] _id:"); _id = read.next();
						LineUpAPI.delete(_id);
					}
				}

			} catch (Exception e) {
				System.out.println("[e] " + e.getMessage());
			}
			
		} while(path!=0);
		read.close();
		System.out.println("[i] Client stopped");
	}

}
