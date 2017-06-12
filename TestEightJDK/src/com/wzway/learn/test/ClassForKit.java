package com.wzway.learn.test;

import java.io.File;
import java.io.IOException;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.omg.Messaging.SyncScopeHelper;

import com.wzway.learn.domain.Streams.Status;
import com.wzway.learn.domain.Streams.Task;

public class ClassForKit {
	public static void main(String[] args) {
		parallelArray();
		//testJavaScript();
		//oldTestKit();
		//testMethod();
		
	//	testDataApi();
	}

	private static void parallelArray() {
		 long[] arrayOfLong = new long [ 20000 ];        
         
	        Arrays.parallelSetAll( arrayOfLong, 
	            index -> ThreadLocalRandom.current().nextInt( 1000000 ) );
	        Arrays.stream( arrayOfLong ).limit( 10 ).forEach( 
	            i -> System.out.print( i + " " ) );
	        System.out.println();
	         
	        Arrays.parallelSort( arrayOfLong );     
	        Arrays.stream( arrayOfLong ).limit( 10 ).forEach( 
	            i -> System.out.print( i + " " ) );
	        System.out.println();
	}

	private static void testJavaScript() {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		try {
			//engine.eval("function fun(){ alert('1');}; fun(); ");
			System.out.println( "Result:" + engine.eval( "function f() { return 1; }; f() + 1;" ) );
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void oldTestKit() {
//		Arrays.asList(1,23,4,3,346,346,356,36,456,3456).forEach( ( Integer e) -> System.out.println(e+"1"));
		Arrays.asList(1,23,4,3,346,346,356,36,456,3456).sort((e1,e2) -> {
			int result = e1.compareTo(e2);
			return result;
		});
		//list.forEach((Integer e) -> System.out.println(e.toString()+"1"));
		//Functional create = Functional.create(FunctionalImpl :: new);
	//	create.defaultMeothod();
		//System.out.println(Functional ::defaultMeothod());
		final Collection<Task> tasks = Arrays.asList(new Task(Status.OPEN,22),new Task(Status.OPEN,34),new Task(Status.CLOSED,34));
		int sum = tasks.stream().filter(e -> e.getStatus() == Status.CLOSED).mapToInt(task ->task.getPoints()).sum();
		System.out.println(sum);
	//	final Collection<Task> tasks = Arrays.asList(new Task(Status.OPEN,22),new Task(Status.OPEN,34),new Task(Status.CLOSED,34));
		Integer reduce = tasks.stream().parallel().map(task -> task.getPoints()).reduce(0,Integer::sum);
		System.out.println(reduce);
		
	}

	private static void testDataApi() {
		/*Clock clock = Clock.systemUTC();
		System.out.println(clock.instant());
		System.out.println(clock.millis());*/
		
		/*LocalDate now = LocalDate.now();
		System.out.println(now);*/
		/*LocalDate now = LocalDate.now(Clock.systemUTC());
		System.out.println(now);*/
		
		/*LocalTime now = LocalTime.now();
		LocalTime now2 = LocalTime.now(Clock.systemDefaultZone());
		System.out.println(now);
		System.out.println(now2);*/
		
		/*LocalDateTime now = LocalDateTime.now();
		LocalDateTime now2 = LocalDateTime.now(Clock.systemUTC());
		LocalDateTime now3 = LocalDateTime.now(Clock.systemDefaultZone());
		System.out.println(now.toString());
		System.out.println(now2.toString());
		System.out.println(now3.toString());*/
		
	/*	ZonedDateTime now = ZonedDateTime.now();
		ZonedDateTime now2 = ZonedDateTime.now(Clock.systemUTC());
		ZonedDateTime now3 = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
		System.out.println(now);
		System.out.println(now2);
		System.out.println(now3);*/
		
		LocalDateTime from = LocalDateTime.of(2015, Month.JANUARY,18,23,24);
		LocalDateTime to = LocalDateTime.of(2017,Month.APRIL,23,23,23);
		Duration duration = Duration.between(from, to);
		System.out.println(duration.toDays());
		System.out.println(duration.toHours());
	}

	private static void testMethod() {
		Runtime mt = Runtime.getRuntime();
		File myFile = new File("c:\\Windows","Notepad.exe");
		try {
			mt.exec(myFile.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
