package protocol.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import protocol.InvalidRequestException;
import protocol.Response;

/**
 * MettreAJour est une classe permetant la mise � jour d'un nom ou d'un surnom sur le serveur
 */
public class MettreAJour extends Service {
	private static final long serialVersionUID = -4554518145285427739L;
	
	private String name;
    private String newName = null;
    private Set<String> nicknames = null;

    /**
     * Constructeur de MettreAJour
     *
     * Permet la mise � jour d'un nom ou d'un surnom
     */
    public MettreAJour() {
        super("UPDATE");
        nicknames = new HashSet<String>();
    }

    /**
     * Constructeur de MettreAJour
     *
     * Permet la mise � jour d'un nom ou d'un surnom
     *
     * @param name
     * 				Le nom permetant de trouver les surnoms � modifier
     * @param nicknames
     *				Les nouveaux surnoms
     */
    public MettreAJour(String name, Set<String> nicknames) {
        super("UPDATE");
        this.name = name;
        this.nicknames = nicknames;
    }

    /**
     * Constructeur de MettreAJour
     *
     * Permet la mise � jour d'un nom ou d'un surnom
     *
     * @param name
     * 				Le nom � modifier
     * @param newName
     *				Le nouveau nom
     */
    public MettreAJour(String name, String newName) {
        super("UPDATE");
        this.name = name;
        this.newName = newName;
    }

    /**
     * Constructeur de MettreAJour
     *
     * Permet la mise � jour d'un nom ou d'un surnom
     *
     * @param name
     * 				Le nom � modifier
     * @param newName
     *				Le nouveau nom
     * @param nicknames
     * 				Les nouveaux surnoms
     */
    public MettreAJour(String name, String newName, Set<String> nicknames) {
        super("UPDATE");
        this.name = name;
        this.newName = newName;
        this.nicknames = nicknames;
    }

    @Override
    public HashMap<String, Set<String>> exec(HashMap<String, Set<String>> map) throws InvalidRequestException {
        if(!map.containsKey(name)) throw new InvalidRequestException(
                "Le nom "+name+" n'a pas pu �tre modifié car n'est pas présent sur le serveur.");

        //Pas de newName, donc modification des nicknames
        if(newName == null) {
            map.remove(name);
            map.put(name, nicknames);
        }
        //Pas de nicknames, donc modification du nom
        else if (nicknames == null) {
            nicknames = map.get(name);
            map.remove(name);
            map.put(newName, nicknames);
        }
        // Les deux
        else {
            map.remove(name);
            map.put(newName, nicknames);
        }

        return map;
    }

    @Override
    public Response createResponse(boolean status, String message, HashMap<String, Set<String>> map) {
        return new Response(status, message);
    }
}
