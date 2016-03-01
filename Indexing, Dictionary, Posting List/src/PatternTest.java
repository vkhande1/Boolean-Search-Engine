package Project_Part_2;

public class PatternTest {
	public static void main(String[] args) {
		String startsWith = "\\[|\\(|\"";
		String endsWith =  "\\]|\\)|\"|,|\\?|!";
		
		String tokenToAdd = "(\"ecl]";
		tokenToAdd=tokenToAdd.replaceAll(startsWith, "");
		tokenToAdd=tokenToAdd.replaceAll(endsWith, "");
		
//		if (tokenToAdd.matches(startsWith)) {
//			tokenToAdd = tokenToAdd.substring(1, tokenToAdd.length());
//		}
//
//		if (tokenToAdd.matches(endsWith)) {
//			tokenToAdd = tokenToAdd.substring(0, tokenToAdd.length() - 1);
//		}
		System.out.println(tokenToAdd);
		
		String s = "[abcdefg]";
		 String regex = "\\[|\\]";
		 s = s.replaceAll(regex, "");
		 System.out.println(s);
	}
}
