package athena.socket.netty.application.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


class Temp extends Thread {
	public void run() {
		System.out.println("run");
	}
}

public class ScheduleThread {
	
	
	public static void main(String[] args) {
		Temp t = new Temp();
		ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);
		ScheduledFuture<?> scheduleTask = schedule.scheduleWithFixedDelay(t, 2, 3, TimeUnit.SECONDS);
		
	}

}
