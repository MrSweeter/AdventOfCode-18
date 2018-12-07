package be.vanga.day03;

import java.util.Arrays;

public class Claim	{

	private String id;
	private int[][] claimInFabric;
	
	private int leftOffset;
	private int topOffset;
	private int width;
	private int height;
	
	public Claim(String data, int maxSize)	{
		
		data = data.replace("@", "");
		data = data.replace(":", "");
		data = data.replace("x", " ");
		data = data.replace(",", " ");
		String[] datas = data.split("( )+");
		id = datas[0];
		leftOffset = Integer.parseInt(datas[1]);
		topOffset = Integer.parseInt(datas[2]);
		width = Integer.parseInt(datas[3]);
		height = Integer.parseInt(datas[4]);
		
		claimInFabric = new int[maxSize][maxSize];
		loadClaim();
	}
	
	private void loadClaim()	{
		for (int j = topOffset; j < topOffset+height; j++)	{
			for (int i = leftOffset; i < leftOffset+width; i++)	{
				claimInFabric[i][j] = 1;
			}
		}
	}
	
	public Claim add(Claim c)	{
		
		if (c.id.equals(this.id))	{return this;}
		
		for (int j = c.topOffset; j < c.topOffset+c.height; j++)	{
			for (int i = c.leftOffset; i < c.leftOffset+c.width; i++)	{
				this.claimInFabric[i][j] += 1;
			}
		}
		
		return this;
	}
	
	public boolean find(Claim c) {
		
		for (int j = c.topOffset; j < c.topOffset+c.height; j++)	{
			for (int i = c.leftOffset; i < c.leftOffset+c.width; i++)	{
				if (this.claimInFabric[i][j] != 1)	{
					return false;
				}
			}
		}
		return true;
	}
	
	public int[][] getFabric() {
		return claimInFabric;
	}
	
	public String getId()	{
		return id;
	}
	
	@Override
	public String toString()	{
		StringBuilder builder = new StringBuilder();
		
		for (int[] is : claimInFabric) {
			builder.append(Arrays.toString(is));
			builder.append('\n');
		}
		
		return builder.toString();
	}
}
