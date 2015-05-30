package genericCheckpointing.server;

public interface RestoreI {
	
	SerializableObject readDJSON(String wireFormat);
}
