package be.vanga.day1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import be.vanga.FileReader;

public class Program {

	// https://adventofcode.com/2018/day/1
	public static void main(String[] args) {
		Program p = new Program();
		p.puzzle1();
		p.puzzle2();
	}
	
	private int result_1 = 0;
	private int result_2 = 0;
	
	public void puzzle1()	{
		
		System.out.println("##### Puzzle 1 #####");
		
		FileReader reader = new FileReader();
		
		reader.readAndConsume(Program.class.getResource("input.txt").getPath(), new Consumer<String>() {
			@Override
			public void accept(String t) {
				int value = Integer.parseInt(t);
				result_1 += value;
			}
		});
		System.out.println("Result: " + result_1);
	}
	
	private boolean breaker = false;
	private List<Integer> sequence = new ArrayList<>();
	
	public void puzzle2()	{
		
		System.out.println("##### Puzzle 2 #####");
		
		FileReader reader = new FileReader();
		
		while (!breaker)	{
			
			reader.readAndConsume(Program.class.getResource("input.txt").getPath(), new Consumer<String>() {
				@Override
				public void accept(String t) {
					if (t.startsWith("#"))	{ return; }
					if (sequence.contains(result_2))	{ breaker = true; return; }
					
					int value = Integer.parseInt(t);
					sequence.add(result_2);
					result_2 += value;
				}
			});
		}
		System.out.println("Result: " + result_2);
	}
}
