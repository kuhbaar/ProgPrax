import java.util.*;// FILL IN

/**
 * Diese Klasse repräsentiert eine Musikbibliothek, 
 * also eine Ansammlung an Playlists. Jede Playlist 
 * wird hierbei durch einen Namen identifiziert. 
 */
public class Library {
	public HashMap<String, Playlist> lib;

	/**
	 * Initialisiert diese Library, die anfangs 
	 * keine Playlists enthält. 
	 */
	public Library() {
		lib=new HashMap<String, Playlist>();

	}

	/**
	 * Dieser Kopierkonstruktor initialisiert die Library, 
	 * die eine bestimmte Untermenge an Playlists aus 
	 * einer anderen Library importiert. Stellen Sie 
	 * sicher, dass Sie echte Kopien der Playlists erzeugen 
	 * (sprich eine Änderung in einer Playlist in der 
	 * neuen Applikation soll die Playlist in der alten 
	 * Applikation nicht beeinflussen). Beachten Sie, 
	 * dass dabei die Audiodateien nicht kopiert werden 
	 * müssen (da sie weiterhin nur einmal auf der "Festplatte" 
	 * vorliegen und auch unveränderlich sind). 
	 *
	 * @param libraryToImport Die Library, 
	 * 	aus der importiert werden soll 
	 * @param listOfNames Ein String-Array mit den 
	 * 	Namen der zu kopierenden Playlists 
	 */
	public Library(Library libraryToImport, String[] listOfNames) {
		lib=new HashMap<String, Playlist>();
		for(int i=0;i<listOfNames.length;i++){
			if(!lib.containsKey(listOfNames[i]))
				this.addPlaylist(listOfNames[i],libraryToImport.getPlaylist(listOfNames[i]));
		}
		
	}

	/**
	 * Fügt eine Playlist unter dem durch name bestimmten 
	 * Namen zur Bibliothek hinzu. Falls bereits eine 
	 * Playlist mit dem Namen existiert, soll die bestehende 
	 * durch die neue (durch playlist angegebene) Playlist 
	 * ersetzt werden. 
	 *
	 * @param name Der Name, unter dem die 
	 * 	Playlist hinzugefügt werden soll 
	 * @param playlist Die Playlist, welche 
	 * 	hinzugefügt werden soll 
	 */
	public void addPlaylist(String name, Playlist playlist) {
		lib.put(name,playlist);
	}

	/**
	 * Ruft aus der Library die Playlist mit dem durch 
	 * name angegebenen Namen ab und gibt diese zurück. 
	 *
	 * @param name Der Name der abzurufenden Playlist 
	 *
	 * @return Die abgerufene Playlist 
	 */
	public Playlist getPlaylist(String name) {
		if(lib.containsKey(name)) return new Playlist(this.lib.get(name));
		else return null;
	}

	/**
	 * Löscht die Playlist mit dem durch name 
	 * gegebenen Namen aus der Library. 
	 *
	 * @param name Der Name der zu löschenden Playlist 
	 */
	public void removePlaylist(String name) {
		
			if(this.lib.containsKey(name)) this.lib.remove(name);
		

	}
}