package cracking.code.tree;

public class TrieNode {
	public char c;
	public TrieNode[] children;
	public boolean isWord;
	
	public TrieNode(char c) {
		this.c = c;
		this.isWord = false;
		this.children = new TrieNode[26];
	}

}
