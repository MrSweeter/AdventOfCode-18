package be.vanga.day04;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;

import be.vanga.AoCProgram;

public class Program extends AoCProgram	{

	// https://adventofcode.com/2018/day/4
	public static void main(String[] args) throws IOException {
		Program p = new Program();
		p.puzzle1();
		p.puzzle2();
	}
	
	public Program() throws IOException {
		super(new File(Program.class.getResource("input.txt").getPath()));
	}
	
	private Guard current;
	private List<Guard> guards;
	private Map<Date, String> schedule = new TreeMap<>();
	
	public void puzzle1()	{
		
		System.out.println("##### Puzzle 1 #####");
		long start = System.currentTimeMillis();
		
		int result = 0;
		guards = new ArrayList<>();
		
		initProgram();
		
		Guard dreamer = guards.get(0);
		for (Guard guard : guards) {
			if (dreamer.getHasSleep() < guard.getHasSleep())	{
				dreamer = guard;
			}
		}
		
		result = Integer.parseInt(dreamer.getId().replace("#", "")) * getIndexOfMax(dreamer.getMinutes());
		
		long end = System.currentTimeMillis();
		
		System.out.println("Execution : " + (end-start) + "ms");
		System.out.println("Result: " + result);
	}
	private void initProgram()	{
		for (String input : inputs) {
			try {	
				loadSchedule(input);
			} catch (ParseException e) {
				e.printStackTrace();
			}	
		}
		
		schedule.forEach(new BiConsumer<Date, String>() {
			@Override
			public void accept(Date t, String u) {
				perfom(u, t);
			}
		});
	}
	private void loadSchedule(String t) throws ParseException	{

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		t = t.substring(1);
		String action = t.substring(t.lastIndexOf("]")+1).trim();
		Date time;
		time = format.parse(t.substring(0, t.lastIndexOf("]")).trim());
		schedule.put(time, action);
		
	}
	private void perfom(String action, Date time)	{
		if (action.contains("Guard"))	{
			String guard = action.substring(action.indexOf("#"), action.indexOf(" " , action.indexOf("#")));
			
			Guard g = new Guard(guard);
			if (!guards.contains(g))	{
				guards.add(g);
			}
			current = guards.get(guards.indexOf(g));
			current.begin(time);
			
		} else if (action.contains("wakes") && current != null)	{
			current.wakeup(time);
		} else if (action.contains("falls") && current != null)	{
			current.sleep(time);
		}
	}
	private int getIndexOfMax(int[] array)	{
		int maxIndex = 0;
		for (int i = 0; i < array.length; i++) {
			maxIndex = array[i] > array[maxIndex] ? i : maxIndex;
		}
		return maxIndex;
	}
	
	public void puzzle2()	{
		
		System.out.println("##### Puzzle 2 #####");
		long start = System.currentTimeMillis();
		
		int result = 0;
		
		initProgram();
		
		Guard dreamer = guards.get(0);
		int maxMinute = 0;
		for (Guard guard : guards) {
			int temp = Arrays.stream(guard.getMinutes()).max().orElse(-1);
			if (temp > maxMinute)	{
				maxMinute = temp;
				
				dreamer = guard;
				result = getIndexOfMax(guard.getMinutes());
			}
		}
		
		result = Integer.parseInt(dreamer.getId().replace("#", "")) * result;
		
		long end = System.currentTimeMillis();
		
		System.out.println("Execution : " + (end-start) + "ms");
		System.out.println("Result: " + result);
	}
}
