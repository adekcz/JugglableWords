package engine;

import java.util.Arrays;

public class SiteSwap {
	private String vanillaSS;
	
	public String getVanillaSS() {
		return vanillaSS;
	}


	public void setVanillaSS(String vanillaSS) {
		this.vanillaSS = vanillaSS;
	}

	public int getNumberBalls() {
		return avg(vanillaSS);
	}

	public SiteSwap(String siteswap){
		if (!isValid(siteswap)) {
			throw new IllegalStateException(siteswap + " neni validni SS");
		}
		vanillaSS = siteswap;
	}
	
	
	public static boolean isValid(String str){
		char[] siteswap = str.toCharArray();
		int[] result = new int[str.length()];
		//System.out.println("pred zacatkem" + Arrays.toString(siteswap));
		
		for (int pos = 0; pos < siteswap.length; pos++){
			if (siteswap[pos] >= '0' && siteswap[pos]<='9'){
				result[pos] = (char) (((siteswap[pos] - '0' + pos +1)%siteswap.length));
			} else if (siteswap[pos] >= 'a' && siteswap[pos]<='z' ){
				result[pos] = (char) (((siteswap[pos] - 'a' + pos +11)%siteswap.length));
			} else if (siteswap[pos] >= 'A' && siteswap[pos]<='Z'){
				result[pos] = (char) (((siteswap[pos] - 'A' + pos +11)%siteswap.length));
			}
			
		}
		//System.out.println("po probehnuti: " + Arrays.toString(result));
		
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
	
	
	public static int avg(String ss){
		char[] siteswap = ss.toCharArray();
		int result = 0;
		
		for (int pos = 0; pos < siteswap.length; pos++){
			if (siteswap[pos] >= '0' && siteswap[pos]<='9'){
				result +=  (((siteswap[pos] - '0')));
			} else if (siteswap[pos] >= 'a' && siteswap[pos]<='z' ){
				result +=  (((siteswap[pos] - 'a')));
			} else if (siteswap[pos] >= 'A' && siteswap[pos]<='Z'){
				result +=  (((siteswap[pos] - 'A')));
			}
			
		}
		return result / ss.length();
	}
	@Override
	public String toString(){
		return "SS: " + vanillaSS + " \t" + " # of balls: " + avg(vanillaSS) + " " ;
	}
	
}
