package telran.util;

import java.util.Arrays;
import java.util.Iterator;

public interface Set<T> extends Collection<T> {
	@Override
	default T[] toArray(T[] ar) {
	
	// write the default method implementation based on the iterating
	Iterator<T> it = iterator();
	int size = size();
	if (ar.length < size) {
		ar = Arrays.copyOf(ar, size);
	} else if (ar.length > size) {
		for(int i = size; i < ar.length; i++) {
			ar[i] = null;
		}
	}
	int ind = 0;
	while(it.hasNext()) {
		ar[ind++] = it.next();
	}
	//if ar.length < size then you should create new array of type T with proper length(consider method Arrays.copyOf)
	//if ar.length == size then you just fill the given array and reference to the same array will be returned
	//if ar'length > size then you fill the given array and rest part should be filled by null's and 
	// reference to the same array will be returned
	return ar;
}

}