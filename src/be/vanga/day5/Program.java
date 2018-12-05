package be.vanga.day5;

import java.io.File;
import java.io.IOException;

import be.vanga.AoCProgram;

public class Program extends AoCProgram	{

	// https://adventofcode.com/2018/day/5
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
		
		int result = -1;
		String input = check(inputs.get(0));
		
		result = input.length();
		
		long end = System.currentTimeMillis();
		
		System.out.println("Execution : 0.00" + (end-start) + "ms");
		System.out.println("Result: " + result);
	}

	private String check(String input) {
		for (int i = 0; i < input.length()-1; i++) {
			
			char c1 = input.charAt(i);
			char c2 = input.charAt(i+1);
			if (c1 != c2 && Character.toLowerCase(c1) == Character.toLowerCase(c2))	{
				input = input.substring(0, i) + input.substring(i+2);
				i = -1;
			}
		}
		return input;
	}
	
	public void puzzle2()	{
		
		System.out.println("##### Puzzle 2 #####");
		long start = System.currentTimeMillis();
		
		int result = Integer.MAX_VALUE;
		String input = inputs.get(0);
		
		String value = "";
		for (int i = 0; i < 26; i++) {
			
			value = input.replaceAll((char)(i+'a')+"|"+(char)(i+'A'), ""); 
			value = check(value);
			
			if (value.length() < result)	{
				result = value.length();
			}
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("Execution : 0.00" + (end-start) + "ms");
		System.out.println("Result: " + result);
	}
}
