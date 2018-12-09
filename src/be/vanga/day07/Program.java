package be.vanga.day07;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import be.vanga.AoCProgram;

public class Program extends AoCProgram	{

	// https://adventofcode.com/2018/day/7
	public static void main(String[] args) throws IOException {
		Program p = new Program();
		p.puzzle1();
		p.puzzle2();
	}
	
	public Program() throws IOException {
		super(new File(Program.class.getResource("input.txt").getPath()));
	}
	
	private Pattern regex = Pattern.compile("Step (.) must be finished before step (.) can begin.");
	private Map<Character, Item> items;
	private Stack<Character> resultList;
	
	private void init()	{
		Item item;
		Matcher m;
		items = new TreeMap<>();
		resultList = new Stack<>();
		for (String input : inputs) {
			m = regex.matcher(input);
			if (m.matches())	{
				char key = m.group(1).charAt(0);
				char value = m.group(2).charAt(0);
				item = items.containsKey(key) ? items.get(key) : new Item(key);
				item.addPost(value);
				items.put(key, item);
				
				item = items.containsKey(value) ? items.get(value) : new Item(value);
				item.addPre(key);
				items.put(value, item);
				
			}
		}
	}
	
	public void puzzle1()	{
		
		System.out.println("##### Puzzle 1 #####");
		long start = System.currentTimeMillis();
		
		String result = "";
		init();

		Function<Item, Boolean> condition = item -> item.getPre().size() == 0;
		char c;
		
		while(items.size() > 0)	{
			c = findNext(condition);
			resultList.push(c);
			result += c;
			items.remove(c);
			removeOnProcess(c);
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("Execution : " + (end-start) + "ms");
		System.out.println("Result: " + result);
	}
	private void removeOnProcess(char c)	{
		for (Item it : items.values()) {
			it.removePre(c);
		}
	}
	
	public void puzzle2()	{
		
		System.out.println("##### Puzzle 2 #####");
		long start = System.currentTimeMillis();
		
		int result = -1;
		init();
		
		result = solveWithXWorker(5, 60);
		
		long end = System.currentTimeMillis();
		
		System.out.println("Execution : " + (end-start) + "ms");
		System.out.println("Result: " + result);
	}
	
	private boolean workAvailable()	{
		for (Item item : items.values()) {
			if (item.getPre().size() == 0)	{
				return true;
			}
		}
		return false;
	}
	
	
	private Set<Item> findAllNext(Function<Item, Boolean> condition)	{
		Set<Item> list = new TreeSet<>();
		for (Item item : items.values()) {
			if (condition.apply(item))	{
				list.add(item);
			}
		}
		return list;
	}
	private char findNext(Function<Item, Boolean> condition)	{
		Set<Item> set = findAllNext(condition);
		Optional<Item> item = set.stream().findFirst();
		return item.isPresent() ? item.get().get() : '0';
	}
	
	private int solveWithXWorker(int workerCount, int offset)	{
		int result = 0;
		
		List<Worker> workers = new ArrayList<>();
		for (int i = 0; i < workerCount; i++)	{
			workers.add(new Worker(offset));
		}
		
		List<Character> inProcess = new ArrayList<>();
		char c;
		boolean process = true;
		
		while (process)	{
			
			for (Worker worker : workers) {
				if (!worker.isWorking() && workAvailable())	{
					
					c = findNext(item -> item.getPre().size() == 0 && !inProcess.contains(item.get()));
					if (!inProcess.contains(c))	{
						inProcess.add(c);
						worker.start(c);
					}
				}
			}
			
			for (Worker worker : workers) {
				if (!worker.work())	{
					c = worker.getWork();
					items.remove(c);
					removeOnProcess(c);
					resultList.push(c);
				}
			}
			
			if (!workAvailable()) {
				process = false;
			}
			result++;
		}
		
		return result;
	}
}
