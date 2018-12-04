package be.vanga.day4;

import java.util.Calendar;
import java.util.Date;

public class Guard {
	
	private String id;
	private int startSleep = 0;
	private Calendar c = Calendar.getInstance();
	private boolean isSleeping;
	private int[] minutes;
	private int totalSleeping = 0;
	
	public Guard(String id)	{
		this.id = id;
		minutes = new int[60];
	}

	public void begin(Date date)	{
		isSleeping = false;
	}
	
	public void sleep(Date date)	{
		c.setTime(date);
		startSleep = c.get(Calendar.MINUTE);
		isSleeping = true;
	}
	
	public void wakeup(Date date)	{
		if (isSleeping) {
			c.setTime(date);
			int stopSleep = c.get(Calendar.MINUTE);
			
			int time = stopSleep - startSleep;
			for(int i = startSleep; i < stopSleep; i++)	{
				minutes[i] += 1;
			}
			totalSleeping += time;
		}
		isSleeping = false;
	}
	
	@Override
	public boolean equals(Object obj)	{
		if (!(obj instanceof Guard)) {return false;}
		Guard g = (Guard) obj;
		return g.id.equals(this.id);
	}

	public String getId() {
		return id;
	}

	public int getHasSleep() {
		return totalSleeping;
	}
	public int[] getMinutes()	{
		return minutes;
	}
}
