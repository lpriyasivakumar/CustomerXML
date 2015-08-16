public class StringUtil {
	public static String formatString(String s){
		if(s.length()< 10){
			s += addSpace(10-s.length());
		}
		return s;
	}

	private static String addSpace(int i) {
		String spaces="";
		for(int space = 0; space< i; space ++)
			spaces +=" ";
		return spaces;
	}

}
