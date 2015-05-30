package genericCheckpointing.util;

import genericCheckpointing.server.SerializableObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * DeSerializedDataStore implement DataStore and stores all the objects
 * that are DeSerialized
 *
 */
public class DeSerializedDataStore implements DataStore{

	private ArrayList<SerializableObject> recordList;
	
	/**
	 * Defualt constructor for the DeSerializedDataStore class
	 */
	public DeSerializedDataStore(){
		recordList = new ArrayList<SerializableObject>();
	}
	
	/**
	 * this method is used to store the SerializableObject to the ArrayList
	 * @param objIn the object to be stored
	 */
	public void addToStore(SerializableObject objIn){
		recordList.add(objIn);
	}
	
	/**
	 * this method returns the next available Object that was store in the ArrayList
	 * and removes that Object from the list
	 * @return SerializableObject that is available else returns null
	 */
	public SerializableObject getNextStoredObject(){
		SerializableObject retObj = null;
		Iterator<SerializableObject> i = recordList.iterator();
		if(i.hasNext()){
			retObj = i.next();
			recordList.remove(retObj);
		}
		return retObj;
	}
}
