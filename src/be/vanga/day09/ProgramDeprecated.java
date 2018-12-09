package be.vanga.day09;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Deprecated
public class ProgramDeprecated {
	
	private HashMap<Integer, Integer> players = new HashMap<>();

	@Deprecated
	private int solve(int[] input)	{
		int playerCount = input[0];
		int marbleCount = input[1];
		int result = -1;
		int currentMarble = 1;
		int currentMarbleIndex = 0;
		
		List<Integer> circle = new ArrayList<>(1);
		circle.add(0);
		
		int i, o, index;
		while (marbleCount >= currentMarble) {
			for(i = 1; i <= playerCount && marbleCount >= currentMarble; i++)	{
				
				if (currentMarble % 23 == 0)	{
					addToPlayer(i, currentMarble);
					currentMarbleIndex -= 7;
					
					if (currentMarbleIndex<0)	{
						currentMarbleIndex = circle.size()+currentMarbleIndex;
					}
					
					o = circle.remove(currentMarbleIndex);
					addToPlayer(i, o);
					
					
				} else {
					index = currentMarbleIndex+2;
					if (index > circle.size())	{
						index = 1;
					}
					currentMarbleIndex = index;
					circle.add(index, currentMarble);
				}
				currentMarble++;
			}
		}
		
		result = players.values().stream().mapToInt(Integer::intValue).max().getAsInt();
		return result;
	}

	@Deprecated
	private void addToPlayer(int player, int value)	{
		int score = players.containsKey(player) ? players.get(player) : 0;
		players.put(player, score+value);
		
	}
}
