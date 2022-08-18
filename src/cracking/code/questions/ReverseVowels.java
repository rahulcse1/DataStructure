package cracking.code.questions;

public class ReverseVowels {

	static boolean isVowel(char c) {
		return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O'
				|| c == 'U');
	}

	static String reverse(String s) {
		int low = 0;
		int high = s.length() - 1;
		char[] cs = s.toCharArray();
		while (low < high) {
			if (!isVowel(cs[low])) {
				low++;
				continue;
			}
			if (!isVowel(cs[high])) {
				high--;
				continue;
			}
			char t = cs[low];
			cs[low] = cs[high];
			cs[high] = t;
			low++;
			high--;
		}
		return String.copyValueOf(cs);
	}

	public static void main(String[] args) {
		System.out.println(reverse("hello world"));
	}
}
