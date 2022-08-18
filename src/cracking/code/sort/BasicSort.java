package cracking.code.sort;

public class BasicSort {

	/*
	 * The complexity of bubble sort is O(n^2) in both worst and average cases
	 */
	public static int[] bubbleSort(int[] a) {
		int len = a.length;
		int temp;
		for (int i = 0; i < len - 1; i++) {
			for (int j = 0; j < len - i - 1; j++) {
				temp = a[j];
				a[j] = a[j + 1];
				a[j + 1] = temp;
			}
		}
		return a;
	}

	/*
	 * The complexity of bubble sort is O(n^2) in both worst and average cases
	 */
	public static int[] selectionSort(int[] a) {
		int len = a.length;
		int pivot = 0;
		int temp;
		for (int i = 0; i < len - 1; i++) {
			for (int j = 1 + 1; j < len; j++) {
				if (a[j] < a[pivot]) {
					pivot = j;
				}
			}
			temp = a[pivot];
			a[pivot] = a[i];
			a[i] = temp;
		}
		return a;
	}

	public static void quicksort(int[] a, int low, int high) {
		if (low >= high || low < 0) {
			return;
		}
		int pivot = partition(a, low, high);
		quicksort(a, low, pivot - 1);
		quicksort(a, pivot + 1, high);
	}

	private static int partition(int[] a, int low, int high) {
		int pivot = a[(low + high) / 2];
		int i = low + 1;
		for (int j = low + 1; j <= high; j++) {
			if (a[j] < pivot) {

				int temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				i += 1;
			}
		}
		int temp = a[low];
		a[low] = a[i - 1];
		a[i - 1] = temp;

		return i - 1;
	}

	public static void mergeSort(int[] a) {
		int l = a.length;
		if (l < 2) {
			return;
		}
		int m_index = l / 2;
		int[] leftHalf = new int[m_index];
		int[] rightHalf = new int[l - m_index];

		for (int i = 0; i < m_index; i++) {
			leftHalf[i] = a[i];
		}
		for (int i = m_index; i < l; i++) {
			rightHalf[i - m_index] = a[i];
		}

		mergeSort(leftHalf);
		mergeSort(rightHalf);

		mergeHalves(a, leftHalf, rightHalf);
	}

	private static void mergeHalves(int[] a, int[] leftHalf, int[] rightHalf) {
		int leftsize = leftHalf.length;
		int rightsize = rightHalf.length;
		int i = 0, j = 0, k = 0;

		while (i < leftsize && j < rightsize) {
			if (leftHalf[i] < rightHalf[j]) {
				a[k] = leftHalf[i];
				i++;
			} else {
				a[k] = rightHalf[j];
				j++;
			}
			k++;
		}

		while (i < leftsize) {
			a[k] = leftHalf[i];
			i++;
			k++;
		}

		while (j < rightsize) {
			a[k] = rightHalf[j];
			j++;
			k++;
		}
	}

}
