package be.vanga.day3;

import java.util.function.Consumer;

import be.vanga.FileReader;

public class Program {

	// https://adventofcode.com/2018/day/3
	public static void main(String[] args) {
		Program p = new Program();
		p.puzzle1();
		p.puzzle2();
	}
	
	private int result_1 = 0;
	private String result_2 = "";
	
	private Claim result;
	private Claim c;
	
	public void puzzle1()	{
		System.out.println("##### Puzzle 1 #####");
		
		FileReader reader = new FileReader();
		int maxSize = 1000;
		
		reader.readAndConsume(Program.class.getResource("input.txt").getPath(), new Consumer<String>() {
			@Override
			public void accept(String t) {
				c = new Claim(t, maxSize);
				
				if (result == null)	{
					result = c;
				} else {
					result.add(c);
				}
			}
		});
		
		int[][] fabric = result.getFabric();
		for (int[] is : fabric) {
			for (int is2 : is) {
				if (is2 > 1)	{
					result_1++;
				}
			}
		}
		
		System.out.println("Result: " + result_1);
	}
	
	private boolean breaker;
	
	public void puzzle2()	{
		System.out.println("##### Puzzle 2 #####");
		
		FileReader reader = new FileReader();
		int maxSize = 1000;
		result = null;
		String fileName = "input.txt";
		
		reader.readAndConsume(Program.class.getResource(fileName).getPath(), new Consumer<String>() {
			@Override
			public void accept(String t) {
				c = new Claim(t, maxSize);
				
				if (result == null)	{
					result = c;
				} else {
					result.add(c);
				}
			}
		});
		
		reader.readAndConsume(Program.class.getResource(fileName).getPath(), new Consumer<String>() {
			@Override
			public void accept(String t) {
				if (breaker)	{return;}
				
				c = new Claim(t, maxSize);
				if (result.find(c))	{
					breaker = true;
					result_2 = c.getId();
				}
			}
		});
		
		System.out.println("Result: " + result_2);
	}
}
