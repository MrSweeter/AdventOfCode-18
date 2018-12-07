package be.vanga.day21;

import java.io.File;
import java.io.IOException;

import be.vanga.AoCProgram;

public class Program extends AoCProgram	{

	// https://adventofcode.com/2018/day/21
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
		
		long end = System.currentTimeMillis();
		
		System.out.println("Execution : 0.00" + (end-start) + "ms");
		System.out.println("Result: " + result);
	}
	
	public void puzzle2()	{
		
		System.out.println("##### Puzzle 2 #####");
		long start = System.currentTimeMillis();
		
		int result = -1;
		
		long end = System.currentTimeMillis();
		
		System.out.println("Execution : 0.00" + (end-start) + "ms");
		System.out.println("Result: " + result);
	}
}
