package be.vanga.day3;

import java.io.File;
import java.io.IOException;

import be.vanga.AoCProgram;

public class Program extends AoCProgram	{

	// https://adventofcode.com/2018/day/3
	public static void main(String[] args) throws IOException {
		Program p = new Program();
		p.puzzle1();
		p.puzzle2();
	}
	
	public Program() throws IOException {
		super(new File(Program.class.getResource("input.txt").getPath()));
	}
	
	private int result_1 = 0;
	private String result_2 = "";
	
	public void puzzle1()	{
		
		System.out.println("##### Puzzle 1 #####");
		long start = System.currentTimeMillis();
		
		int maxSize = 1000;
		Claim result = null;
		
		result = loadClaim(result, maxSize);
		
		int[][] fabric = result.getFabric();
		
		for (int[] row : fabric) {
			for (int column : row) {
				if (column > 1)	{
					result_1++;
				}
			}
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("Execution : 0.00" + (end-start) + "ms");
		System.out.println("Result: " + result_1);
	}
	
	private Claim loadClaim(Claim result, int maxSize)	{
		Claim claim = null;
		for (String input : inputs) {
			claim = new Claim(input, maxSize);
			
			if (result == null)	{
				result = claim;
			} else {
				result.add(claim);
			}
		}
		return result;
	}
	
	public void puzzle2()	{
		
		System.out.println("##### Puzzle 2 #####");
		long start = System.currentTimeMillis();
		
		int maxSize = 1000;
		Claim result = null;
		
		result = loadClaim(result, maxSize);
		
		boolean breaker = false;
		
		Claim claim = null;
		for (String input : inputs) {
			if (breaker)	{continue;}
			
			claim = new Claim(input, maxSize);
			if (result.find(claim))	{
				breaker = true;
				result_2 = claim.getId();
			}
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("Execution : 0.00" + (end-start) + "ms");
		System.out.println("Result: " + result_2);
	}
}
