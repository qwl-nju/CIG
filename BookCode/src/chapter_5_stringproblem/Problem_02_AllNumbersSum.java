package chapter_5_stringproblem;

public class Problem_02_AllNumbersSum {

	public static int numSum(String str) {
		if (str == null) {
			return 0;
		}
		char[] charArr = str.toCharArray();
		int res = 0;
		int num = 0;
		boolean posi = true;
		int cur = 0;
		for (int i = 0; i < charArr.length; i++) {
			cur = charArr[i] - '0';
			if (cur < 0 || cur > 9) {
				res += num;
				num = 0;
				if (charArr[i] == '-') {
					if (i - 1 > -1 && charArr[i - 1] == '-') {
						posi = !posi;
					} else {
						posi = false;
					}
				} else {
					posi = true;
				}
			} else {
				num = num * 10 + (posi ? cur : -cur);
			}
		}
		res += num;
		return res;
	}
	
	
	
	
	//QWL
	public static int numSum2(String str) {
		if(str == null || str.length() == 0){
			return 0;
		}
		int res = 0;
		int len = str.length();
		int f = 1;
		for(int i = 0; i < len;){
			if(str.charAt(i) <= '9' && str.charAt(i) >= '0'){
				int t = 0;
				while(i < len && str.charAt(i) <= '9' && str.charAt(i) >= '0'){
					t = t * 10 + (str.charAt(i) - '0');
					i++;
				}
				res += t * f;
				f = 1;
			}else if(str.charAt(i) == '-'){
				int n = 0;
				while(i < len && str.charAt(i) == '-'){
					n++;
					i++;
				}
				f = n % 2 == 0 ? 1 : -1;
			}else{
				f = 1;
				i++;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		String test = "1K-100ABC500D-T--100F200G!!100H---3001K-100ABC500D-T--100F200G!!100H---3001K-100ABC500D-T--100F200G!!100H---3001K-100ABC500D-T--100F200G!!100H---300";
		System.out.println(numSum(test));
		System.out.println("===========================");
		System.out.println(numSum2(test));

	}

}
