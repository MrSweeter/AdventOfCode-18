package be.vanga.day06;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import be.vanga.AoCProgram;

public class Program extends AoCProgram	{

	// https://adventofcode.com/2018/day/6
	public static void main(String[] args) throws IOException {
		Program p = new Program();
		p.puzzle1();
		p.puzzle2();
	}
	
	public Program() throws IOException {
		super(new File(Program.class.getResource("input.txt").getPath()));
	}
	
	private void init()	{
		// LOAD COORDINATE FROM INPUTS
		int id = -1;
		coordinates = new HashMap<>();
		for (String input : inputs) {
			int[] r = new int[2];
			r[0]= Integer.parseInt(input.substring(0, input.indexOf(",")));
			r[1]= Integer.parseInt(input.substring(input.indexOf(",")+2));
			coordinates.put(id, r);
			id--;
		}
		// SIZE OF FINAL GRID
		int maxW = coordinates.values().stream().map((ar) -> ar[0]).mapToInt(Integer::intValue).max().getAsInt()+1;
		int maxH = coordinates.values().stream().map((ar) -> ar[1]).mapToInt(Integer::intValue).max().getAsInt()+1;
		grid = new int[maxH][maxW];
		// FILL GRID
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = 0;
			}
		}
		// LOAD COORDINATE IN GRID
		for (Entry<Integer, int[]> is : coordinates.entrySet()) {
			grid[is.getValue()[1]][is.getValue()[0]] = is.getKey();
		}
	}
	
	private Map<Integer, int[]> coordinates = new HashMap<>();
	private int[][] grid;
	
	public void puzzle1()	{
		
		System.out.println("##### Puzzle 1 #####");
		long start = System.currentTimeMillis();
		
		int result = -1;
		init();
		
		// LOAD CLOSEST
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = findClosest(i, j);
			}
		}
		
		// REMOVE INFINITE AREA
		for (int i = 0; i < grid.length; i++) {
			// IF FIRST OR LAST ROW
			if (i == 0 || i == grid.length-1)	{
				for (int j = 0; j < grid[i].length; j++) {
					remove(i, j);
				}
			} else {
				remove(i, 0);
				remove(i, grid[i].length-1);
			}
		}
		
		// MAP GRID VALUE
		List<Integer> temp = new ArrayList<>();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				temp.add(grid[i][j]);
			}
		}
		// GET MAX SIZE
		for (int v : coordinates.keySet()) {
			if (Collections.frequency(temp, v*-1) > result)	{
				result = Collections.frequency(temp, v*-1);
			}
		}
		// + ID CASE
		result++;
		
		long end = System.currentTimeMillis();
		
		System.out.println("Execution : 0.00" + (end-start) + "ms");
		System.out.println("Result: " + result);
	}
	private void remove(int i, int j)	{
		int v = grid[i][j] > 0 ? grid[i][j]*-1 : grid[i][j];
		if (coordinates.containsKey(v))	{
			coordinates.remove(v);
		}
	}
	private int findClosest(int h, int w)	{
		// IF ID return
		if (grid[h][w] < 0)	{return grid[h][w];}
		
		int r = 0;
		Map<Integer, Integer> proximity = new HashMap<>();
		
		// MANHATTAN DISTANCE WITH ALL ID
		for (Entry<Integer, int[]> v : coordinates.entrySet()) {
			int id = v.getKey();
			int[] is = v.getValue();
			
			int distance = manhattan(h, w, is[1], is[0]);
			proximity.put(id, distance);
		}
		
		// MINIMAL DISTANCE
		int min = proximity.values().stream().mapToInt(Integer::intValue).min().getAsInt();
		
		// IF NOT DOUBLE GET ID OF MINIMAL
		if (Collections.frequency(proximity.values(), min) == 1)	{
			r = proximity.entrySet().stream().filter(entry -> entry.getValue() == min).findFirst().get().getKey();
		}
		return Math.abs(r);
	}
	private int manhattan(int i1, int j1, int i2, int j2)	{
		return Math.abs(i1-i2) + Math.abs(j1-j2);
	}
	
	public void puzzle2()	{
		
		System.out.println("##### Puzzle 2 #####");
		long start = System.currentTimeMillis();
		
		int result = -1;
		init();
		
		// FOR ALL ELEMENT
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				int sum = 0;
				// MANGATTAN WILL COORDINATE AND SUM IT
				for (Entry<Integer, int[]> v : coordinates.entrySet()) {
					int[] is = v.getValue();
					
					int distance = manhattan(i, j, is[1], is[0]);
					sum+=distance;
				}
				if (sum < 10000)	{
					grid[i][j] = 100000;
				}
			}
		}
		
		// MAP GRID VALUE
		List<Integer> temp = new ArrayList<>();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				temp.add(grid[i][j]);
			}
		}
		// GET MAX SIZE
		result = Collections.frequency(temp, 100000);	
		
		long end = System.currentTimeMillis();
		
		System.out.println("Execution : 0.00" + (end-start) + "ms");
		System.out.println("Result: " + result);
	}
	
//	private void display()	{
//		for (int i = 0; i < grid.length; i++) {
//			for (int j = 0; j < grid[i].length; j++) {
//				System.out.printf("%4d", grid[i][j]);
//			}
//			System.out.print("\n");
//		}
//	}
}
