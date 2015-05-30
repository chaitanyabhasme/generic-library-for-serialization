package genericCheckpointing.util;

import genericCheckpointing.server.SerializableObject;

/**
 * DataStore interface for the classes that will store the SerializableObject
 *
 */
public interface DataStore {
	
	public void addToStore(SerializableObject obj);
	
	public SerializableObject getNextStoredObject();
}
