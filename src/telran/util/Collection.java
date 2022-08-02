package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

public interface Collection<T> extends Iterable<T> {
/**
 * adds object of type T in collection
 * @param obj
 * @return true if added
 */
	boolean add(T obj);
	/***************************************/
	/** 
	 * removes object equaled to the given pattern 
	 * @param pattern any object
	 * @return true if removed 
	 */
	boolean remove(Object pattern);
	/******************************************/
	/**
	 * removes all objects matching the given predicate
	 * @param predicate
	 * @return true if a collection has been updated
	 */
	boolean removeIf(Predicate<T> predicate);
	/*************************************************/
	/**
	 * 
	 * @param predicate
	 * @return true if there is an object equaled to the given pattern
	 */
	boolean contains(Object pattern);
	/********************************************************/
	/**
	 * 
	 * @return amount of the objects
	 */
	int size();
	default T[] toArray(T[] ar) {
		Iterator<T> it = iterator();
		if(ar.length<this.size()) {
			ar = Arrays.copyOf(ar, this.size());
		}
		int ind=0;
		for(T num: this) {
			ar[ind++]=num;
		}
		return ar;
	}
	
}