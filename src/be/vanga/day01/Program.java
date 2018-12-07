package be.vanga.day01;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import be.vanga.AoCProgram;

public class Program extends AoCProgram	{

	// https://adventofcode.com/2018/day/1
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
		
		int result = 0;
		for (String input : inputs) {
			int value = Integer.parseInt(input);
			result += value;
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("Execution : 0.00" + (end-start) + "ms");
		System.out.println("Result: " + result);
	}
	
	public void puzzle2()	{
		
		System.out.println("##### Puzzle 2 #####");
		long start = System.currentTimeMillis();
		
		boolean breaker = false;
		int result=0;
		List<Integer> sequence = new ArrayList<>();
		
		while (!breaker)	{
			for (String input : inputs) {
				if (sequence.contains(result))	{ breaker = true; continue;}
				
				int value = Integer.parseInt(input);
				sequence.add(result);
				result += value;
			}
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("Execution : 0.00" + (end-start) + "ms");
		System.out.println("Result: " + result);
	}
}
