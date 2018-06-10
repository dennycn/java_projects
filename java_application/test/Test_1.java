package test;
/*
 * @desc: test java argument
 * @cmd: javac java
 * javac test/Test_1.java
 * java -classpath . test/Test_1
 */

import java.util.*;

public class Test_1 { 
	public static void main(String[] args) {
		/*java -Xint  only interpre, Average time: 3271*/
		/*java -Xcomp only compile, Average time: 1874*/
		/*java -Xmixed JVM mixed,  Average time: 1250*/
		System.out.println("Hello world");
		long startTime = System.currentTimeMillis();
		Map<String, Object> map = new HashMap<>();
		for (int i = 0; i < 1000000; i++) {
			map.put(String.valueOf(i), i);
		}
		System.out.println("Average time: " + (System.currentTimeMillis() - startTime));
	}
}