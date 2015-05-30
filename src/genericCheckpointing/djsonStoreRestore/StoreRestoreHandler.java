package genericCheckpointing.djsonStoreRestore;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * StoreRestoreHandler class implements Invocation Handler
 * This class is used to serialize and deserialize the object with type SerializableObject
 *
 */
public class StoreRestoreHandler implements InvocationHandler {

	private String fileName;
	private BufferedReader fileReader = null;
	private PrintWriter fileWriter = null;
	
	/**
	 * The control is passed to this method whenever a method is called 
	 * using the proxy instance
	 * @param arg0 the object instance
	 * @param arg1 the method called using the object instance
	 * @param arg2 the parameters passed through the call
	 * @return Object specific to the method being called
	 */
	@Override
	public Object invoke(Object arg0, Method arg1, Object[] arg2){
		Object retObj = null;
		String[] data;
		String methodName = arg1.getName();
		
		if(methodName.equals("writeDJSON")){
			this.writeToFile(arg2[0]);
		}
		if(methodName.equals("readDJSON")){
			String className = null;
			try {
				data = fileReader.readLine().split(":");
				className = data[1];
				Class<?> genClass = Class.forName(className);
				Object genObj = genClass.newInstance();
				retObj = this.readFromFile(genObj);
			} catch (IOException e) {
				System.out.println("Unable to read the file I/O Exception");
				System.exit(1);
			} catch (ClassNotFoundException e) {
				System.out.println("Passes class name not found while creating the new instance");
				System.exit(1);
			} catch (InstantiationException e) {
				System.out.println("Object instantiation error");
				System.exit(1);
			} catch (IllegalAccessException e) {
				System.out.println("Illegal Access Exception for creating the instance");
				System.exit(1);
			}
		}
		return retObj;
	}
	
	/**
	 * This method is used to serialize the object to the file
	 * @param objIn Object to be serialized
	 */
	public void writeToFile(Object objIn){
		
		fileWriter.println("ClassName:" + objIn.getClass().getName());
		Field[] fList = objIn.getClass().getDeclaredFields();
		for(int i=0;i<fList.length;i++){
			try {
				String fieldName = fList[i].getName();
				fList[i].setAccessible(true);
				Object val;
				val = fList[i].get(objIn);
				fileWriter.println(fList[i].getType().getName() + "#" + fieldName + "#" + val);
			} catch (IllegalArgumentException e) {
				System.out.println("Illegal Argument passed");
				System.exit(1);
			} catch (IllegalAccessException e) {
				System.out.println("Illegal Access");
				System.exit(1);
			}
			
		}
		fileWriter.println("#ObjectEnd#");
	}
	
	/**
	 * this method is used to update the Object which is deserialized 
	 * i.e. read from the file
	 * @param objIn Object instance which was created
	 * @return the updated Object
	 */
	
	public Object readFromFile(Object objIn){
		String line = null;
		String[] data;
		try {
			while((line = fileReader.readLine()) != null){
				if(line.equals("#ObjectEnd#"))
					break;
				data = line.split("#");
				String fieldType = data[0];
				String fieldName = data[1];
				String fieldVal = data[2];
				this.setObjectValues(objIn, fieldType, "set_" + fieldName, fieldVal);
			}
		} catch (IOException e) {
			System.out.println("Unable to read the file I/O Exception");
			System.exit(1);
		}
		return objIn;
	}
	
	/**
	 * This method is used to set the field values of an Object
	 * @param objIn the Object of which the fields should be updated
	 * @param type the type of the field to the updated
	 * @param methodName the name of the method which will be called to update the object
	 * @param val the value of the field to be updated
	 */
	public void setObjectValues(Object objIn, String type, String methodName, String val){
		
		Method callMe = null;
		Method m[] = objIn.getClass().getDeclaredMethods();
		for(int i = 0;i<m.length;i++){
			
			if(m[i].getName().equals(methodName)){
				callMe = m[i];
				break;
			}
		}
		Object[] param = new Object[1];
		if(type.equals("int")){
			param[0] = new Integer(Integer.parseInt(val));
		}
		else if(type.equals("float")){
			param[0] = new Float(Float.parseFloat(val));
		}
		else if(type.equals("boolean")){
			param[0] = new Boolean(Boolean.parseBoolean(val));
		}
		else if(type.equals("short")){
			param[0] = new Short(Short.parseShort(val));
		}
		else if(type.equals("double")){
			param[0] = new Double(Double.parseDouble(val));
		}
		else if(type.equals("long")){
			param[0] = new Long(Long.parseLong(val));
		}
		try {
			callMe.invoke(objIn, param);
		} catch (IllegalAccessException e) {
			System.out.println("Illegal access Exception");
			System.exit(1);
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal Argument passed Exception");
			System.exit(1);
		} catch (InvocationTargetException e) {
			System.out.println("Invocation Target Exception");
			System.exit(1);
		}
	
	}
	
	/**
	 * This method sets the file name of the StoreRestoreHandler object
	 * @param fname the input filename which will be stored
	 */
	public void setFilename(String fname){
		fileName = fname;
	}
	
	/**
	 * this method is to open the file for writing to the file stored
	 */
	public void openFileWrite(){
		try {
			fileWriter = new PrintWriter(fileName);
		} catch (FileNotFoundException e) {
			System.out.println("Input File not found");
			System.exit(1);
		}
	}
	
	/**
	 * this method is used to closed the writing to the file stored
	 */
	public void closeFileWrite(){
			fileWriter.close();
	}
	
	/**
	 * this method is used to open the reading for the file stored
	 */
	public void openFileRead(){
		try {
			fileReader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("Input File not found");
			System.exit(1);
		}
	}
	
	/**
	 * this method is used to closed the reading to the file stored
	 */
	public void closeFileRead(){
		try {
			fileReader.close();
		} catch (IOException e) {
			System.out.println("Unable to close the file");
		}
	}
}
