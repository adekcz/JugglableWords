package engine;

import java.util.Arrays;

public class FileUtils {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(validateSS("441"));
		System.out.println(validateSS("db97532"));

	}

	public static boolean validateSS(String str){
		int[] isValid = new int[str.length()];
		//Arrays.fill(isValid, 0);
		char[] siteswap = str.toCharArray();
		int[] result = new int[str.length()];
		System.out.println("pred zacatkem" + Arrays.toString(siteswap));
		
		for (int pos = 0; pos < siteswap.length; pos++){
			if (siteswap[pos] >= '0' && siteswap[pos]<='9'){
				result[pos] = (char) (((siteswap[pos] - '0' + pos +1)%siteswap.length));
			} else if (siteswap[pos] >= 'a' && siteswap[pos]<='z' ){
				result[pos] = (char) (((siteswap[pos] - 'a' + pos +11)%siteswap.length));
			} else if (siteswap[pos] >= 'A' && siteswap[pos]<='Z'){
				result[pos] = (char) (((siteswap[pos] - 'A' + pos +11)%siteswap.length));
			}
			
		}
		System.out.println("po probehnuti: " + Arrays.toString(result));
		
		boolean[] contains = new boolean[str.length()];
		Arrays.fill(contains, false);
		for (int i = 0; i < result.length; i++){
			contains[result[i]] = true;
		}
		for (int i = 0; i < contains.length; i++){
			if (!contains[i]){
				return false;
			}
		}
		
		
		return true;
	}
}
