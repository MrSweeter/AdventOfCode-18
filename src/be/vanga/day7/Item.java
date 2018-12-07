package be.vanga.day7;

import java.util.Set;
import java.util.TreeSet;

public class Item implements Comparable<Item> {

	private char id;
	private Set<Character> pre;
	private Set<Character> post;
	
	public Item(char c) {
		this.id = c;
		pre = new TreeSet<>();
		post = new TreeSet<>();
	}
	
	public char get()	{
		return id;
	}
	
	public Set<Character> getPre()	{
		return pre;
	}
	
	public Set<Character> getPost()	{
		return post;
	}
	
	public void removePre(char c)	{
		pre.remove(c);
	}
	
	public void addPre(char c) {
		pre.add(c);
	}
	
	public void addPost(char c)	{
		post.add(c);
	}
	
	@Override
	public String toString()	{
		return pre + "-" + id + "-" + post;
	}
	
	@Override
	public boolean equals(Object obj)	{
		if (!(obj instanceof Item))	{ return false; }
		
		return ((Item) obj).get() == this.id;
	}
	
	@Override
	public int compareTo(Item o) {
		return Character.compare(get(), id);
	}
}
