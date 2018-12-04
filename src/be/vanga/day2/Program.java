package be.vanga.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.vanga.AoCProgram;

public class Program extends AoCProgram	{

	// https://adventofcode.com/2018/day/2
	public static void main(String[] args) throws IOException {
		Program p = new Program();
		p.puzzle1();
		p.puzzle2();
	}
	
	public Program() throws IOException {
		super(new File(Program.class.getResource("input.txt").getPath()));
	}
	
	public void puzzle1()	{
		
		System.out.println("##### Puzzle 1 #####");
		long start = System.currentTimeMillis();
		
		int result;
		int[] counter = new int[2];
		
		for (String input : inputs) {
			if (countLetter(2, input))	{ counter[0]++; }
			if (countLetter(3, input))	{ counter[1]++; }
		}
		
		result = counter[0] * counter[1];
		
		long end = System.currentTimeMillis();
		
		System.out.println("Execution : 0.00" + (end-start) + "ms");
		System.out.println("Result: " + result);
	}
	
	private boolean countLetter(int frequency, String input) {
		
		Map<Character, Integer> occurence = new HashMap<>();
		
		for (char c : input.toCharArray()) {
			
			int v = 1;
			if (occurence.containsKey(c))	{
				v = occurence.get(c)+1;
			}
			occurence.put(c, v);
		}
		return occurence.values().contains(frequency);
	}
	
	public void puzzle2()	{
		
		System.out.println("##### Puzzle 2 #####");
		long start = System.currentTimeMillis();
		
		String result = "";
		String current = "";
		
		for (String input : inputs) {
			current = input;
			if (result.length() > 0)	{continue;}
			
			for (String input2 : inputs) {
				if (result.length() > 0)	{continue;}
				
				List<Integer> r = difference(input2, current);
				if (r.size() == 1)	{
					result += current.substring(0, r.get(0));
					result += current.substring(r.get(0)+1);
				}
			}
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("Execution : 0.00" + (end-start) + "ms");
		System.out.println("Result: " + result);
	}
	
	private List<Integer> difference(String input1, String input2)	{
		return diff(input1, input2, 0, new ArrayList<>());
	}
	
	private List<Integer> diff(String input1, String input2, int index, List<Integer> differencesIndexes) {
		if (index >= input1.length())	{ return differencesIndexes; }
		if (input1.charAt(index) == input2.charAt(index))	{
			return diff(input1, input2, index+1, differencesIndexes);
		} else {
			differencesIndexes.add(index);
			return diff(input1, input2, index+1, differencesIndexes);
		}
	}
}
