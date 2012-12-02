package engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Engine {

	public static Map<Integer, List<String>> siteswaps = new HashMap<Integer, List<String>>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(new SiteSwap("db97531"));
		System.out.println(new SiteSwap("534"));
		//System.out.println(new SiteSwap("543"));
		//load file
		
		//get lines
		List<String> list = FileUtils.getLines("C:\\Users\\anuvin\\Documents\\GitHub\\JugglableWords\\SiteSwapsWords\\src\\resources\\wlist_match10\\wlist_match10.txt");
		System.out.println("FROM MAIN");
		for (String str : list){
			System.out.println(str);
		}
		
		FileUtils.writeLines(list, "C:\\Users\\anuvin\\Documents\\GitHub\\JugglableWords\\SiteSwapsWords\\src\\resources\\result.txt");
		//if line is valid
		String line = "";
		if(siteswaps.get(line.length()) == null) {
			siteswaps.put(line.length(), new ArrayList<String>());
		}
	}
	
	

	//convert line to word, 
	public static String convert(String ss){
		String result = "";
		
		return result;
	}
	
	public static boolean isValidSS(String ss){
		int[] numerical = new int[ss.length()];
		System.out.println("pred: " + ss);
		//convert characters into numbers
		for (int i = 0; i< ss.length(); i++){
			if (ss.charAt(i)>='0' && ss.charAt(i) <='9'){
				numerical[i] = (ss.charAt(i) - '0' + 1 + i) % ss.length();
			}
			if (ss.charAt(i)>='a' && ss.charAt(i) <='z'){
				numerical[i] = (ss.charAt(i) - 'a' + 11 + i ) % ss.length();
			}
			if (ss.charAt(i)>='A' && ss.charAt(i) <='Z'){
				numerical[i] = (ss.charAt(i) - 'A' + 11 + i) % ss.length();
			}
		}
		System.out.println("po: " + Arrays.toString(numerical));
		
		//check if every ball is catched in different times
		boolean[] validityArray = new boolean[ss.length()];
		Arrays.fill(validityArray, false);
		for (int i = 0; i < ss.length(); i++){
			validityArray[numerical[i]] = true;
		}
		for (int i = 0; i < ss.length(); i++){
			if (!validityArray[i]){
				return false;
			}
		}
		return true;
	}
}
