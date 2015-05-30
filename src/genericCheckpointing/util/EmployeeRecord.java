package genericCheckpointing.util;

import genericCheckpointing.server.SerializableObject;
import java.util.Random;

/**
 * EmployeeRecord class as a SerializableObject 
 *
 */
public class EmployeeRecord extends SerializableObject {
    private int ii;
    private float ff;
    private double dd;
    private long ll;

    /**
     * Empty constructor for the EmployeeRecord class
     * sets the default values of the object
     */
    public EmployeeRecord() {
    	ii = 0;
    	ff = 0;
    	dd = 0.0;
    	ll = 0;
    }
    
    /**
     * Parameterized constructor for the EmployeeRecord class
     * randomly generates the values of the fields
     * @param iIn to set the ii value
     */
    public EmployeeRecord(int iIn) {
    	ii = iIn;
    	Random rGen = new Random();
    	ff = rGen.nextFloat();
    	ff = (float)Math.round(ff * 1000)/1000;
    	dd = rGen.nextDouble();
    	dd = (double)Math.round(dd * 1000)/1000;
    	ll = rGen.nextLong() % 10000;
    }
    
    /**
     * Overrides the toString method from java.lang.Object.toString
     */
    @Override
    public String toString(){
    	return getClass().getName() + ";" + "ii:" + ii +";"+ "ff:" + ff +";"+ "dd:" + dd +";"+ "ll:" + ll+";";
    }
    
    /**
     * this method overrides the hashCode method from java.lang.Object.hashCode
     */
	@Override
	public int hashCode() {
		int retVal = (int)(31 + 32*ii + 33*ff + 34*dd);
		return retVal;
	}
	
	 /**
     * this method overrides the equals method from java.lang.Object.equals
     */
	@Override
	public boolean equals(Object inRec){
		boolean ret = false;
	    if(inRec instanceof EmployeeRecord){
	    	EmployeeRecord empRec = (EmployeeRecord) inRec;
	    	ret = ((empRec.ii == ii) && (empRec.ff == ff) && (empRec.dd == dd) && (empRec.ll == ll));
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
	 * @return the ff
	 */
	public float getFf() {
		return ff;
	}

	/**
	 * @param ff the ff to set
	 */
	public void set_ff(float ff) {
		this.ff = ff;
	}

	/**
	 * @return the dd
	 */
	public double getDd() {
		return dd;
	}

	/**
	 * @param dd the dd to set
	 */
	public void set_dd(double dd) {
		this.dd = dd;
	}

	/**
	 * @return the ll
	 */
	public long getLl() {
		return ll;
	}

	/**
	 * @param ll the ll to set
	 */
	public void set_ll(long ll) {
		this.ll = ll;
	}

}
