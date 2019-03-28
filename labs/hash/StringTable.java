package hash;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

//
// STRINGTABLE.JAVA
// A hash table mapping Strings to their positions in the the pattern sequence
// You get to fill in the methods for this part.
//
public class StringTable {
    
    private LinkedList<Record>[] buckets;
    private int nBuckets;

    //
    // number of records currently stored in table --
    // must be maintained by all operations
    //
    public int size;
    
    
    //
    // Create an empty table with nBuckets buckets
    //
    @SuppressWarnings("unchecked")
	public StringTable(int nBuckets)
    {
    	this.nBuckets = nBuckets;
    	buckets = new LinkedList[nBuckets];
    	for (int i = 0; i < nBuckets; i++) {
    	buckets[i]= new LinkedList<Record>();
    	}
    	size = 0;
    	// TODO - fill in the rest of this method to initialize your table
    }
    
    
    /**
     * insert - inserts a record to the StringTable
     *
     * @param r
     * @return true if the insertion was successful, or false if a
     *         record with the same key is already present in the table.
     */
    public boolean insert(Record r) 
    {  
    	// TODO - implement this method
    	String Locations = r.key;
    	boolean ri = true;
    	int Locationi = stringToHashCode(Locations);
    	int Index = toIndex(Math.abs(Locationi));
    	LinkedList<Record> Listplace = buckets[Index];
   
    	if (find(r.key) != null) {
    		
    	ri = false;
    	}
    	else{
    		Listplace.addLast(r);
    		ri = true;
    	size++;
    	}
    	
    	return ri;
    
    }
    
    
    
    /**
     * find - finds the record with a key matching the input.
     *
     * @param key
     * @return the record matching this key, or null if it does not exist.
     */
    public Record find(String key) 
    {
    	// TODO - implement this method
    	int Hashcode = stringToHashCode(key);
    	int Indexloc = toIndex(Math.abs(Hashcode));
    	LinkedList<Record> Listloc = buckets[Indexloc];
    	for (Record s : Listloc) {
    	if (key.equals(s.key)) {
    	return s;
    	}
	}
    	return null;
    }
    
    
    /**
     * remove - finds a record in the StringTable with the given key
     * and removes the record if it exists.
     *
     * @param key
     */
    public void remove(String key) 
    {
    	int Hash = stringToHashCode(key);
    	int Hindex = toIndex(Math.abs(Hash));
    	LinkedList<Record> Place = buckets[Hindex];
    	Record deleted = find(key);
    	if (deleted != null ) {
    	Place.remove(deleted);
    	size--;
    }
    	}
    	// TODO - implement this method
    
    

    /**
     * toIndex - convert a string's hashcode to a table index
     *
     * As part of your hashing computation, you need to convert the
     * hashcode of a key string (computed using the provided function
     * stringToHashCode) to a bucket index in the hash table.
     *
     * You should use a multiplicative hashing strategy to convert
     * hashcodes to indices.  If you want to use the fixed-point
     * computation with bit shifts, you may assume that nBuckets is a
     * power of 2 and compute its log at construction time.
     * Otherwise, you can use the floating-point computation.
     */
    private int toIndex(int hashcode)
    {
    	// Fill in your own hash function here
	double A = (Math.sqrt(5) - 1)/2;
	int re = (int) (((Math.abs(hashcode)*A)%1.0)*nBuckets);
    	//System.out.println(re);
    	return re;
    	
    }
    
    
    /**
     * stringToHashCode
     * Converts a String key into an integer that serves as input to
     * hash functions.  This mapping is based on the idea of integer
     * multiplicative hashing, where we do multiplies for successive
     * characters of the key (adding in the position to distinguish
     * permutations of the key from each other).
     *
     * @param string to hash
     * @returns hashcode
     */
    int stringToHashCode(String key)
    {
    	int A = 1952786893;
	
    	int v = A;
    	for (int j = 0; j < key.length(); j++)
	    {
    		char c = key.charAt(j);
    		v = A * (v + (int) c + j) >> 16;
	    }
	
    	return v;
    }

    /**
     * Use this function to print out your table for debugging
     * purposes.
     */
    public String toString() 
    {
    	StringBuilder sb = new StringBuilder();
	
    	for(int i = 0; i < nBuckets; i++) 
	    {
    		sb.append(i+ "  ");
    		if (buckets[i] == null) 
		    {
    			sb.append("\n");
    			continue;
		    }
    		for (Record r : buckets[i]) 
		    {
    			sb.append(r.key + "  ");
		    }
    		sb.append("\n");
	    }
    	return sb.toString();
    }
}
