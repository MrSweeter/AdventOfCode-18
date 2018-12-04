package be.vanga.day2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import be.vanga.FileReader;

public class Program {

	// https://adventofcode.com/2018/day/2
	public static void main(String[] args) {
		Program p = new Program();
		p.puzzle1();
		p.puzzle2();
	}
	
	private int result_1 = 0;
	private String result_2 = "";
	
	private int[] counter = new int[2];
	
	public void puzzle1()	{
		System.out.println("##### Puzzle 1 #####");
		
		FileReader reader = new FileReader();
		
		reader.readAndConsume(Program.class.getResource("input.txt").getPath(), new Consumer<String>() {
			@Override
			public void accept(String t) {
				
				if (countLetter(2, t))	{ counter[0]++; }
				if (countLetter(3, t))	{ counter[1]++; }
			}
		});
		
		result_1 = counter[0] * counter[1];
		System.out.println("Result: " + result_1);
	}
	
	private boolean countLetter(int frequency, String input) {
		
		Map<Character, Integer> occurence = new HashMap<>();
		
		for (char c : input.toCharArray()) {
			
			if (occurence.containsKey(c))	{
				occurence.put(c, occurence.get(c)+1);
			} else {
				occurence.put(c, 1);
			}
		}
		return occurence.values().contains(frequency);
	}
	
	private String current;
	
	public void puzzle2()	{
		System.out.println("##### Puzzle 2 #####");
		
		FileReader reader = new FileReader();
		String fileName = "input.txt";
		
		reader.readAndConsume(Program.class.getResource(fileName).getPath(), new Consumer<String>() {
			@Override
			public void accept(String t) {
				current = t;
				if (result_2.length() > 0)	{return;}
				
				reader.readAndConsume(Program.class.getResource(fileName).getPath(), new Consumer<String>() {
					@Override
					public void accept(String t) {
						if (result_2.length() > 0)	{return;}
						
						List<Integer> r = difference(t, current);
						if (r.size() == 1)	{
							result_2 += current.substring(0, r.get(0));
							result_2 += current.substring(r.get(0)+1);
						}
					}
				});
			}
		});
		
		System.out.println("Result: " + result_2);
	}
	
	private List<Integer> difference(String input1, String input2)	{
		List<Integer> diffs = new ArrayList<>();
		return diff(input1, input2, 0, diffs);
	}
	
	private List<Integer> diff(String input1, String input2, int index, List<Integer> differencesIndexes) {
		if (index >= input1.length())	{ return differencesIndexes; }
		if (input1.charAt(index) == input2.charAt(index))	{
			return diff(input1, input2, index+1, differencesIndexes);
		} else {
			differencesIndexes.add(index);
			return diff(input1, input2, index+1, differencesIndexes);
		}
	}
	
	/**
     * Returns a minimal set of characters that have to be removed from (or added to) the respective
     * strings to make the strings equal.
     */
    public static Pair<String> diff(String a, String b) {
        return diffHelper(a, b, new HashMap<>());
    }

    /**
     * Recursively compute a minimal set of characters while remembering already computed substrings.
     * Runs in O(n^2).
     */
    private static Pair<String> diffHelper(String a, String b, Map<Long, Pair<String>> lookup) {
        return lookup.computeIfAbsent(((long) a.length()) << 32 | b.length(), k -> {
            if (a.isEmpty() || b.isEmpty()) {
                return new Pair<>(a, b);
            } else if (a.charAt(0) == b.charAt(0)) {
                return diffHelper(a.substring(1), b.substring(1), lookup);
            } else {
                Pair<String> aa = diffHelper(a.substring(1), b, lookup);
                Pair<String> bb = diffHelper(a, b.substring(1), lookup);
                if (aa.first.length() + aa.second.length() < bb.first.length() + bb.second.length()) {
                    return new Pair<>(a.charAt(0) + aa.first, aa.second);
                } else {
                    return new Pair<>(bb.first, b.charAt(0) + bb.second);
                }
            }
        });
    }

    public static class Pair<T> {
        public Pair(T first, T second) {
            this.first = first;
            this.second = second;
        }

        public final T first, second;

        public String toString() {
            return "(" + first + "," + second + ")";
        }
    }
}
