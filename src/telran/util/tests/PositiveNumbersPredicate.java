package telran.util.tests;

import java.util.function.Predicate;

public class PositiveNumbersPredicate implements Predicate<Integer> {

	@Override
	public boolean test(Integer t) {
		return t>0;
	}

}
