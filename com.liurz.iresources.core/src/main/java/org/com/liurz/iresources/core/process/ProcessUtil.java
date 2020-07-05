package org.com.liurz.iresources.core.process;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.lang.Runnable;
/**
 * 多线程处理工具类
 * 
 * @ClassName: ProcessUtil
 * @Description: TODO
 * @author lwx393577：
 * @date 2020年3月30日 下午9:14:31
 *
 */
public class ProcessUtil {

	// 阻塞队列
	private static BlockingQueue<Runnable> taskQueue= new ArrayBlockingQueue<Runnable>(5000);
	// 线程池，固定大小，有界队列
	public static ExecutorService executorService = new ThreadPoolExecutor(10,10,60, TimeUnit.SECONDS,taskQueue);
	/**
	 * 多个线程处理，最好不要超过10个线程，线程池数量根据线程的数据来定
	 * 
	 */
	public <V> List<Future<V>> submitCallable(List<Callable<V>> callables) {
		List<Future<V>> result = new ArrayList<Future<V>>();
		if (null == callables || callables.size() <= 0) {
			return result;
		}
		ExecutorService executorService = null;
		try {
			executorService = Executors.newFixedThreadPool(callables.size());
			for (Callable<V> callable : callables) {

				result.add(executorService.submit(callable));
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			if (null != executorService) {
				executorService.shutdown();
			}
		}
	}

	/**
	 * 非阻塞多个线程处理，线程池数量根据电脑核数
	 * 
	 * @Title: submitCallable
	 * @Description: TODO
	 * @param callables
	 * @return
	 * @return List<Future<V>>
	 */
	public <V> List<Future<V>> synchExcelCallableTask(List<Callable<V>> callables) {
		List<Future<V>> result = new ArrayList<Future<V>>();
		if (null == callables || callables.size() <= 0) {
			return result;
		}
		ExecutorService executorService = null;
		try {
			executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
			for (Callable<V> callable : callables) {

				result.add(executorService.submit(callable));
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			if (null != executorService) {
				executorService.shutdown();
			}
		}
	}

	/**
	 * 单线程处理，不阻塞
	 * 
	 * @Title: submitSingleCallableTask
	 * @Description: TODO
	 * @param callable
	 * @return
	 * @return List<Future<V>>
	 */
	public <V> Future<V> submitSingleCallableTask(Callable<V> callable) {

		ExecutorService executorService = null;
		try {
			executorService = Executors.newSingleThreadExecutor();
			return executorService.submit(callable);
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			if (null != executorService) {
				executorService.shutdown();
			}
		}
	}

	public void submitRunnable(Runnable runnable){
		try {
			executorService.submit(runnable);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
