package cracking.code.sort;

import java.util.Arrays;

public class SelectionSort {

	public static void main(String[] args) {
		int[] arr = new int[] { 0, 5, 2, 3, 7, 6, 1 };
		selectionSort(arr);
		bubbleSort(arr);
	}

	public static int[] selectionSort(int[] arr) {
		int size = arr.length;
		for (int i = 0; i < size - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < size; j++)
				if (arr[j] < arr[minIndex])
					minIndex = j;
			int temp = arr[minIndex];
			arr[minIndex] = arr[i];
			arr[i] = temp;
		}
		System.out.println(Arrays.toString(arr));
		return arr;
	}

	static int[] bubbleSort(int arr[]) {
		int size = arr.length;
		for (int i = 0; i < size - 1; i++) {
			for (int j = i + 1; j < size; j++) {
				if (arr[i] > arr[j]) {
					int temp = arr[i];
					arr[j] = arr[i];
					arr[i] = temp;
				}
			}
		}
		System.out.println(Arrays.toString(arr));
		return arr;
	}

	int partition(int array[], int low, int high) {

		int pivot = array[high];
		int left = low;
		int right = high - 1;

		while (left <= right) {

			while (array[left] < pivot) {
				left++;
			}

			while (array[right] > pivot) {
				right--;
			}

			if (left < right) {
				swap(array, left, right);
			}
		}
		swap(array, left, right);
		return (left);
	}

	private void swap(int[] arr, int left, int right) {
		int temp = arr[left];
		arr[left] = arr[right];
		arr[right] = temp;
	}

	void quickSort(int array[], int low, int high) {
		if (low < high) {
			int pi = partition(array, low, high);
			quickSort(array, low, pi - 1);
			quickSort(array, pi + 1, high);
		}
	}
}
