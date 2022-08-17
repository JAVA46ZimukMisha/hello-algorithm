package telran.recursion;

public class LineRecursion {
public static long factorial(int n) {
	
	if(n == 0) {
		return 1;
	}
	return  n * factorial(n - 1);
}
/**
 * 
 * @param a either negative or positive
 * @param b positive
 * @return a ^ b
 */

//public static long pow(int a, int b) {
//	if (b == 0) {
//		return 1;
//	}
//
//	return a * pow(a, b - 1);
//}
public static int pow(int a, int b) {
	if(b==0) {
		return 1;
	}
	if(b==1) {
		return a;
	}
	boolean isANegative = false;
	boolean isBEven = b%2==0;
	if(a<0) {
		a=-a;
		isANegative = true;
	}
	int ind = a;
	int res =  powBMinus(a, b, ind);
	return isANegative&&!isBEven ?  -res: res;
	
}
private static int powBMinus(int a, int b, int ind) {
	b--;
	a = a+summARuns(a,ind);
	if(b==1) {
		return a;
	}
	return powBMinus(a, b, ind);
}
private static int summARuns(int a, int ind) {
	ind--;
	if(ind==0) {
		return 0;
	}
	return a+summARuns(a, ind);
}

/**
 * 
 * @param x
 * @return x ^ 2
 */
public static int square(int x) {
	if(x<0) {
		x=-x;
	}
	if(x==0) {
		return 0;
	}
	if(x==1) {
		return 1;
	}
	return square(x-1) + x + x - 1;
}
/**
 * 
 * @param ar - array of in teger numbers
 * @return sum of all numbers from the given array
 */
public static int sum(int ar[]) {
	return sum(0, ar);
}
private static int sum(int firstIndex, int[] ar) {
	if (firstIndex == ar.length) {
		return 0;
	}
	return ar[firstIndex] + sum(firstIndex + 1, ar);
	
}
}