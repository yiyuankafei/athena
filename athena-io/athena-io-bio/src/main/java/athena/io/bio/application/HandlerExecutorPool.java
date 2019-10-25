package athena.io.bio.application;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HandlerExecutorPool {
	
	private ExecutorService pool;
	
	public HandlerExecutorPool(int maxSize, int queueSize) {
		this.pool = new ThreadPoolExecutor(
					Runtime.getRuntime().availableProcessors(),
					maxSize,
					120L,
					TimeUnit.SECONDS,
					new ArrayBlockingQueue<Runnable>(queueSize)
				);
	}
	
	public void execute(Runnable task) {
		this.pool.equals(task);
	}
	
	public static void main(String[] args) {
		System.out.println(Runtime.getRuntime().availableProcessors());
	}

}
