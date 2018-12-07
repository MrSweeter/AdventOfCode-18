package be.vanga.day07;

public class Worker	{

	private char id;
	private int timeOffset;
	private boolean isWorking;
	private int workTime;
	
	public Worker(int offset) {
		id = '0';
		this.timeOffset = offset;
		isWorking = false;
		workTime = 0;
	}
	
	public boolean isWorking()	{
		return isWorking;
	}
	public void start(char c)	{
		id = c;
		isWorking = true;
		workTime = c - 64 + timeOffset; // A = 65
	}
	public boolean work()	{
		workTime--;
		if (workTime <= 0)	{
			isWorking = false;
		}
		return isWorking;
	}
	public char getWork()	{
		return id;
	}
	
	@Override
	public boolean equals(Object obj)	{
		if (!(obj instanceof Worker))	{ return false; }
		
		return ((Worker) obj).getWork() == this.id;
		
	}

	public int getWorkTime() {
		return workTime;
	}
}
