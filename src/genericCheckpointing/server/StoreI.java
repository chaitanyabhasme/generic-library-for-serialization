package genericCheckpointing.server;

public interface StoreI extends StoreRestoreI{

	void writeDJSON(SerializableObject aRecord, String wireFormat);
}
