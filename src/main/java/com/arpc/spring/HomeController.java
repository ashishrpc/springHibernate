package com.arpc.spring;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//http://localhost:8082/springHibernate/home
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String home(Locale locale, Model model){
		logger.info("Welcome home! The client locale is {}.", locale);
		//adding some time lag to check interceptor execution
		try {
			Thread.sleep(1000);
			//out of memory
			//generateOOM();
			//memoryLeakage();
			//givenMap_whenNoEqualsNoHashCodeMethods_thenOutOfMemory();
			
			//stack OverFlow
			//recursivePrint(1);
			
			//Thread max creation
			CreateThreads.main(null);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		logger.info("Before returning view page");
		return "home";
	}
	
	public void generateOOM() throws Exception {
		int iteratorValue = 20;
		System.out.println("\n=================> OOM test started..\n");
		for (int outerIterator = 1; outerIterator < 20; outerIterator++) {
			System.out.println("Iteration " + outerIterator + " Free Mem: " + Runtime.getRuntime().freeMemory());
			int loop1 = 2;
			int[] memoryFillIntVar = new int[iteratorValue];
			// feel memoryFillIntVar array in loop..
			do {
				memoryFillIntVar[loop1] = 0;
				loop1--;
			} while (loop1 > 0);
			iteratorValue = iteratorValue * 5;
			System.out.println("\nRequired Memory for next loop: " + iteratorValue);
			Thread.sleep(1000);
		}
	}
	public void memoryLeakage() throws Exception{
		for(int i=0;i<100;i++){
			URL url = new URL("ftp://speedtest.tele2.net");
		    URLConnection urlc = url.openConnection();
		    InputStream is = urlc.getInputStream();
		    String str = "";
		}
	}
	public void givenMap_whenNoEqualsNoHashCodeMethods_thenOutOfMemory()
			throws IOException {
		Map<Object, Object> map = System.getProperties();
		while (true) {
			map.put(new Key("key"), "value");
		}
	}
	public void recursivePrint(int num) {//stackOverFlowError
		System.out.println("Number: " + num);
		if(num == 0)
			return;
		else
			recursivePrint(++num);
	}

}
class Key {
    public String key;
    
    public Key(String key) {
        this.key = key;
    }
}

///Max thread created on JVM
class CreateThreads {  
	/** 
	 * Thread class 
	 * 
	 */  
	private static final class MyThread extends Thread  
	{  
		private final int number;  
		private final String test;
		public MyThread(int number,String test) {  
			this.number = number;  
			this.test=test+"~"+number;
		}  
		@Override  
		public void run() {  
			if (shouldPrintMessage(number))  
			{  
				System.out.println("Thread no. "+number+" started.");  
			}  
			try {  
				//sleep forever  
				Thread.sleep(Long.MAX_VALUE);  
			} catch (InterruptedException e) {  
				e.printStackTrace();  
			}  
		}  
	}  
	/** 
	 * @param args 
	 */  
	public static void main(String[] args) {  
		long startTime = System.currentTimeMillis();   
		final int noOfThreads = 1000;//1000000;  
		int i=0;  
		try {  
			for (i=0; i < noOfThreads; i++)  
			{  
				if (shouldPrintMessage(i))  
				{  
					System.out.println("Creating thread "+i+" ("+(System.currentTimeMillis()-startTime)+"ms)");  
				}  
				new MyThread(i,"Ashish ").start();  
			}  
		} catch (Throwable e) {  
			System.out.println("Error thrown when creating thread "+i);  
			e.printStackTrace();  
		}  
	}  

	private static boolean shouldPrintMessage(int i)  
	{  
		return i % 100 == 0;  
	}  
}  