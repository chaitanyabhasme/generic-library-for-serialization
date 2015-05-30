package genericCheckpointing.util;

import genericCheckpointing.server.SerializableObject;
import java.util.Random;
/**
 * StudentRecord class  as a SerializableObject 
 *
 */
public class StudentRecord extends SerializableObject {

    private int ii;
    private short ss;
    private boolean bb;
    
    /**
     * Empty constructor for the StudentRecord class
     * sets the default values of the object
     */
    public StudentRecord() {
    	ii = 0;
    	ss = 0;
    	bb = true;
    }
    
    /**
     * Parameterized constructor for the StudentRecord class
     * randomly generates the values of the fields
     * @param iIn to set the ii value
     */
    public StudentRecord(int iIn) {
    	ii = iIn;
    	Random rGen = new Random();
    	ss = (short)rGen.nextInt(1000);
    	bb = (rGen.nextInt(1000) < 500);
    }

    /**
     * Overrides the toString method from java.lang.Object.toString
     */
    @Override
    public String toString(){
    	return getClass().getName() + ";" + "ii:" + ii + ";" + "ss:" + ss  + ";" + "bb:" + bb  + ";" ;  
    }
    
    /**
     * this method overrides the hashCode method from java.lang.Object.hashCode
     */
	@Override
	public int hashCode() {
		int retVal = (int)(31 + 32*ii + 33*ss);
		return retVal;
	}


	/**
	 * this method overrides the equals method from java.lang.Object.equals
	 */
	@Override
	public boolean equals(Object inRec){
		boolean ret = false;
	    if(inRec instanceof StudentRecord){
	    	StudentRecord stuRec = (StudentRecord) inRec;
	    	ret = ((stuRec.ii == ii) && (stuRec.bb == bb) && (stuRec.ss == ss));
	    }
	    return ret;
	}
    
	/**
	 * @return the ii
	 */
	public int getIi() {
		return ii;
	}

	/**
	 * @param ii the ii to set
	 */
	public void set_ii(int ii) {
		this.ii = ii;
	}

	/**
	 * @return the ss
	 */
	public short getSs() {
		return ss;
	}

	/**
	 * @param ss the ss to set
	 */
	public void set_ss(short ss) {
		this.ss = ss;
	}

	/**
	 * @return the bb
	 */
	public boolean isBb() {
		return bb;
	}

	/**
	 * @param bb the bb to set
	 */
	public void set_bb(boolean bb) {
		this.bb = bb;
	}
    
}
