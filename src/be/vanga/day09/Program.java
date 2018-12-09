package be.vanga.day09;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import be.vanga.AoCProgram;

public class Program extends AoCProgram	{

	// https://adventofcode.com/2018/day/9
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
		
//		int[][] inputs = {{10,1618}, {13,7999}, {17,1104}, {21, 6111}, {30,5807}};
		int[][] inputs = {{476,71431}};
		long result = -1;
		result = solve(inputs[0]);
		
		long end = System.currentTimeMillis();
		
		System.out.println("Execution : " + (end-start) + "ms");
		System.out.println("Result: " + result);
	}
	
	// https://www.reddit.com/r/adventofcode/comments/a4i97s/2018_day_9_solutions/ebetdc5
	private long solve(int[] input) {
		int players = input[0];
		int end = input[1];
		
        CircleDeque<Integer> circle = new CircleDeque<>(); // CURRENT MARBLE IS THE FIRST
        circle.addFirst(0);
        long[] scores = new long[players];
        
        for (int i = 1; i <= end; i++) {
            if (i % 23 == 0) {
                circle.rotate(-7);
                scores[i % players] += i + circle.pop();

            } else {
                circle.rotate(2);
                circle.addLast(i);
            }
        }
        return Arrays.stream(scores).max().getAsLong();
    }
	
	public void puzzle2()	{
		
		System.out.println("##### Puzzle 2 #####");
		long start = System.currentTimeMillis();
		
		int[][] inputs = {{476,7143100}};
		long result = -1;
		result = solve(inputs[0]);
		
		long end = System.currentTimeMillis();
		
		System.out.println("Execution : " + (end-start) + "ms");
		System.out.println("Result: " + result);
	}
}
