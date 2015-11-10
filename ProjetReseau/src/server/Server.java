package server;

import java.net.ServerSocket;
import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import protocol.InvalidRequestException;
import protocol.Response;
import protocol.services.Service;


public class Server {

	public static void main(String[] args) {

		HashMap<String, Set<String>> serverData = initializeServerData();
		
		ServerSocket serverSocket;
		Socket clientSocket;

		System.out.println(" === SERVER SIDE === ");
		
		try {

			serverSocket = new ServerSocket(1337);
			clientSocket = serverSocket.accept();

			System.out.println("Msg:Serveur en ligne.");
			ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
			ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
			//TODO Fermer le serveur au bon moment
			for(int i = 1 ; i <= 10 ; i++){

				Service request = (Service)ois.readObject();
				System.out.println("Msg n°"+i+" :Réception d'un objet envoyé par le client.");
				Response response;
				try {
					serverData = request.exec(serverData);
					response = request.createResponse(true, "OK", serverData);
				} catch (InvalidRequestException e) {
					String message = request.getServiceName() + " : " + e.getMessageError();
					response = request.createResponse(false, message, null);

				}
				
				oos.writeObject(response);
				oos.reset();
				System.out.println("Msg n°" + i + " :Envoi d'une réponse au client.");
				oos.flush();
			}

			ois.close();
			oos.close();
			serverSocket.close();
			clientSocket.close();
			
			System.out.println("Msg:Serveur stoppé.");

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static HashMap<String, Set<String>> initializeServerData(){
		HashMap<String, Set<String>> map = new HashMap<>();
		
		for(int i = 1 ; i <= 3 ; i++){
			Set<String> set = new HashSet<>();
			set.add("Surname"+i+".1");
			set.add("Surname"+i+".2");
			set.add("Surname"+i+".3");
			map.put("Name"+i, set);
		}
		
		return map;
	}
}
