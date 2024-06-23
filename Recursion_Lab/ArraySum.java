
public class ArraySum {
	/**
	 * Sums the array from integer x to integer 0
	 * @param a
	 * @param index
	 * @return
	 */
	public int sumOfArray(Integer[] a, int index) {
		if(index == 0)
			return a[0];
		return a[index].intValue() + sumOfArray(a, index - 1);
	}
}
