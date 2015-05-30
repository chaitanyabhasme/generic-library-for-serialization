
package genericCheckpointing.driver;

import java.lang.reflect.InvocationHandler;

import genericCheckpointing.djsonStoreRestore.StoreRestoreHandler;
import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.SerializableObject;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.DataStore;
import genericCheckpointing.util.DeSerializedDataStore;
import genericCheckpointing.util.EmployeeRecord;
import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.SerializedDataStore;
import genericCheckpointing.util.StudentRecord;

/**
 * Driver class to generate and store the StudentRecord and EmployeeRecord
 * Serializes the generated objects and compares them which the objects 
 * which were deserialized 
 *
 */
public class Driver {
    
    public static void main(String[] args) {

    	int NUM_OF_OBJECTS=0;
    	String checkpointFile=null;
    	try{
    		NUM_OF_OBJECTS = Integer.parseInt(args[0]);
        	checkpointFile = args[1];
        	if(!(NUM_OF_OBJECTS > 0)){
        		System.out.println("Atleast one object need to be created. Check input argument.");
        		System.exit(1);
        	}	
    	}catch(NumberFormatException e){
    		System.out.println("Error in passed arguments. Check arguments.");
    		System.exit(1);
    	}catch(ArrayIndexOutOfBoundsException e){
    		System.out.println("Error in passed arguments. Check arguments.");
    		System.exit(1);
    	}
	
    	ProxyCreator pc = new ProxyCreator();
	
    	InvocationHandler newHandler = new StoreRestoreHandler();
	
    	StoreRestoreI cpointRef = pc.createProxy( new Class[] {
    								StoreI.class, RestoreI.class
								 	}, 
								 	newHandler
								 	);
		
    	((StoreRestoreHandler) newHandler).setFilename(checkpointFile);
    	((StoreRestoreHandler) newHandler).openFileWrite();

    	SerializableObject myErecord;
    	SerializableObject mySrecord;
    	DataStore storedObjectStore = new SerializedDataStore(); 
    	for (int i=0; i<NUM_OF_OBJECTS; i++) {
		
    		myErecord = new EmployeeRecord(i);
    		mySrecord = new StudentRecord(i);
    		storedObjectStore.addToStore(myErecord);
    		storedObjectStore.addToStore(mySrecord);
    		((StoreI) cpointRef).writeDJSON(myErecord, "djson");
    		((StoreI) cpointRef).writeDJSON(mySrecord, "djson");
    	}
    	((StoreRestoreHandler) newHandler).closeFileWrite();
	
    	((StoreRestoreHandler) newHandler).openFileRead();
    	SerializableObject myRecordRet;
    	DataStore RestoredObjectStore = new DeSerializedDataStore();
    	for (int j=0; j < 2*NUM_OF_OBJECTS; j++) {

    		myRecordRet = ((RestoreI) cpointRef).readDJSON("djson");
    		RestoredObjectStore.addToStore(myRecordRet);
    	}

    	((StoreRestoreHandler) newHandler).closeFileRead();

    	SerializableObject myStoredObj = storedObjectStore.getNextStoredObject();
    	SerializableObject myRestoredObj = RestoredObjectStore.getNextStoredObject();
    	while(myStoredObj != null || myRestoredObj != null){
    		if(myStoredObj.equals(myRestoredObj)){
    			System.out.println("Object Match:" + myStoredObj.toString());
    			myStoredObj = storedObjectStore.getNextStoredObject();
    			myRestoredObj = RestoredObjectStore.getNextStoredObject();
    		}
    		else{
    			System.out.println("Object mismatch:");
    			System.out.println("Stored Object:" + myStoredObj);
    			System.out.println("Restored Object:" + myRestoredObj);
    			System.exit(1);
    		}
    	}
    }
}
