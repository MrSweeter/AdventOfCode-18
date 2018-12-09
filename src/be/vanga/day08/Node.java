package be.vanga.day08;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public List<Integer> data;
    public List<Node> children;
    
    public Node()	{
    	this.data = new ArrayList<>();
    	children = new ArrayList<>();
    }
    
    public int getValue()	{
    	if (children.isEmpty())	{
    		return data.stream().mapToInt(Integer::intValue).sum();
    	}
    	int sum = 0;
    	for (int index : data) {
    		index--;
			if (index >= 0 && index < children.size())	{
				sum += children.get(index).getValue();
			}
		}
    	return sum;
    }
    
    public List<Integer> getAllData()	{
    	List<Integer> d = data;
    	for (Node node : children) {
			data.addAll(node.getAllData());
		}
    	return d;
    }
    
    @Override
    public String toString()	{
    	return children + " | " + data;
    }
}
