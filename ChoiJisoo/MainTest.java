package main;

import java.util.Map;

import util.Util;

public class MainTest {
	public static void main(String[] args) {
		Util util = new Util();
		
		Map<String,Object> result = util.randomWordCut("ABCDEFGHIJKLMNOPQRSTU", 3);
		
		
		System.out.println(result.get("cuttingWord"));
		System.out.println(result.get("cuttingWordIndex"));
	}
}
