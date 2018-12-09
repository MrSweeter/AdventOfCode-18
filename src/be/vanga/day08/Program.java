package be.vanga.day08;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import be.vanga.AoCProgram;

public class Program extends AoCProgram	{

	// https://adventofcode.com/2018/day/8
	public static void main(String[] args) throws IOException {
		Program p = new Program();
		p.puzzle1();
		p.puzzle2();
	}
	
	public Program() throws IOException {
		super(new File(Program.class.getResource("input.txt").getPath()));
	}
	
	private List<Integer> input;
	
	public void puzzle1()	{
		
		System.out.println("##### Puzzle 1 #####");
		long start = System.currentTimeMillis();
		
		input = Arrays.asList(inputs.get(0).split(" ")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		int result = -1;
		
		Node node = toNode();
		result = node.getAllData().stream().mapToInt(Integer::intValue).sum();
		
		long end = System.currentTimeMillis();
		
		System.out.println("Execution : " + (end-start) + "ms");
		System.out.println("Result: " + result);
	}
	private Node toNode()	{
		
		Node node = new Node();
		
		int childCount = input.get(0);
		int metaCount = input.get(1);
		
		// REMOVE PARENT DATA
		input = input.subList(2, input.size());
		
		Node n;
		// GET CHILD
		for(int i = 0; i < childCount; i++)	{
			n = toNode();
			// REMOVE NODE DATA SIZE
			input = input.subList(n.data.size(), input.size());
			node.children.add(n);
		}
		// GET DATA
		for(int i = 0; i < metaCount; i++) {
			node.data.add(input.get(i));
		}
		
		return node;
	}
	
	public void puzzle2()	{
		
		System.out.println("##### Puzzle 2 #####");
		long start = System.currentTimeMillis();
		
		input = Arrays.asList(inputs.get(0).split(" ")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		int result = -1;
		
		Node node = toNode();
		result = node.getValue();
		
		long end = System.currentTimeMillis();
		
		System.out.println("Execution : " + (end-start) + "ms");
		System.out.println("Result: " + result);
	}
}
