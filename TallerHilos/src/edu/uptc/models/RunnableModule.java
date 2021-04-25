package edu.uptc.models;

import java.util.Random;

public class RunnableModule implements Runnable{
	public static int time;
	private boolean status = false;
	private int timeWorked;
	private User user;
	
	public RunnableModule() {
		timeWorked = 0;
		this.user = new User();
	}

	@Override
	public void run() {
		time = new Random().nextInt(4000)+500;
		timeWorked += time;
		for(int i = 0; i <= 10; i++) {
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		setStatus(false);
	}

	public int getTimeWorked() {
		return timeWorked;
	}
	
	public User getUser() {
		return user;
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setTimeWorked(int timeWorked) {
		this.timeWorked = timeWorked;
	}
	
	public void setUser(User user) {
		this.user = user;
	}	
	
	public static int tiempo() {
		return Thread.activeCount();
	}
}