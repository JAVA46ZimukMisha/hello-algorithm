package telran.util;

import java.util.function.Predicate;

public class NegativeNumbersPredicate implements Predicate<Integer> {

	@Override
	public boolean test(Integer t) {
		
		return t < 0;
	}

}