package be.vanga.day10;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import be.vanga.AoCProgram;

public class Program extends AoCProgram	{

	// https://adventofcode.com/2018/day/10
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
		
		String result = "voir au dessus";
		solve(true);
		
		long end = System.currentTimeMillis();
		
		System.out.println("Execution : " + (end-start) + "ms");
		System.out.println("Result: " + result);
	}
	private int solve(boolean show)	{
		List<Coordinate> positions = new ArrayList<>();
		int result = -1;
		// LOAD COORDINATE AND VECTOR
		Pattern regex = Pattern.compile("position=<(.+), (.+)> velocity=<(.+), (.+)>");
		Matcher m;
		for (String input : inputs) {
			m = regex.matcher(input);
			if (m.find()) {
				positions.add(new Coordinate(
							Integer.parseInt(m.group(1).trim()),
							Integer.parseInt(m.group(2).trim()),
							new Point(Integer.parseInt(m.group(3).trim()), Integer.parseInt(m.group(4).trim()))
						)
					);
			}
		}
		long distance = Long.MAX_VALUE;
		long maxX = 0, maxY = 0, minX = 0, minY = 0;
		while(true)	{
			maxX = positions.stream().mapToInt(p -> p.x).max().getAsInt();
			maxY = positions.stream().mapToInt(p -> p.y).max().getAsInt();
			minX = positions.stream().mapToInt(p -> p.x).min().getAsInt();
			minY = positions.stream().mapToInt(p -> p.y).min().getAsInt();
			
			if ((maxX - minX)*(maxY - minY) > distance)	{
				break;
			}
			
			result++;
			distance = (maxX - minX)*(maxY - minY);
			positions.forEach(c -> c.move());
		}
		
		positions.forEach(c -> c.moveBack());
		maxX = positions.stream().mapToInt(p -> p.x).max().getAsInt();
		maxY = positions.stream().mapToInt(p -> p.y).max().getAsInt();
		minX = positions.stream().mapToInt(p -> p.x).min().getAsInt();
		minY = positions.stream().mapToInt(p -> p.y).min().getAsInt();
		
		final long mx = minX;
		final long my = minY;
		
		long distX = maxX - minX;
		long distY = maxY - minY;
		char[][] textArray = new char[(int) distX+1][(int) distY+1];
		positions.forEach(c -> {
			textArray[(int) (c.x-mx)][(int) (c.y-my)] = '#';
		});
		if (show)	{
			for (int i = 0; i < textArray[0].length; i++) {
				for (int j = 0; j < textArray.length; j++) {
					System.out.print(textArray[j][i]);
				}
				System.out.println();
			}
		}
		return result;
	}
	public void puzzle2()	{
		
		System.out.println("##### Puzzle 2 #####");
		long start = System.currentTimeMillis();
		
		int result = -1;
		result = solve(false);
		
		long end = System.currentTimeMillis();
		
		System.out.println("Execution : " + (end-start) + "ms");
		System.out.println("Result: " + result);
	}
}
