package rmi.server;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Set;

/**
 * Response est une classe contenant la réponse du serveur. Elle est constituée : 
 * - d'un booléen status indiquant si la requête a pu être effectuée ou non ; 
 * - d'un String message contenant un éventuel message d'erreur ;
 * - de data, la donnée retournée par le serveur de type HashMap<String, Set<String>>
 * Chacun de ces attributs dispose d'un accesseur.
 */
public class ResponseRMI implements Serializable{
	private static final long serialVersionUID = 1227367288332606275L;
	private boolean status;
	private Hashtable<String, Set<String>> data;
	private String message;

	/**
	 * Constructeur de Response
	 *
	 * Permet la création d'un réponse
	 *
	 * @param status
	 * 				Informe sur l'état de la requête
	 * @param message
	 * 				Détail sur l'état de la requête
	 */
	public ResponseRMI(boolean status, String message) {
		this.status = status;
		this.message = message;
		data = new Hashtable<>();
	}

	/**
	 * Constructeur de Response
	 *
	 * Permet la création d'un réponse
	 *
	 * @param status
	 * 				Informe sur l'état de la requête
	 * @param message
	 *  			Détail sur l'état de la requête
	 * @param data
	 * 				Les données résultantes de l'éxécution de la requête
	 */
	public ResponseRMI(boolean status, String message, Hashtable<String, Set<String>> data) {
		this(status, message);
		this.data = data;
	}

	/**
	 * Accesseur du status de la réponse
	 *
	 * @return le status de la réponse
	 */
	public boolean getStatus(){
		return status;
	}

	/**
	 * Accesseur des données de la réponse
	 *
	 * @return une HashMap contenant les données
	 */
	public Hashtable<String, Set<String>> getData(){
		return data;
	}

	/**
	 * Accesseur en lecture du message de la réponse
	 *
	 * @return le message de la réponse
	 */
	public String getMessage(){
		return message;
	}
}
