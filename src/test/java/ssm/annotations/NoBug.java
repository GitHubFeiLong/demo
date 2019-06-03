package ssm.annotations;

public class NoBug {
	@TestNoBug
	private void jiafa() {
		System.out.println("1 + 1="+(1+1));
	}
	@TestNoBug
	private void jianfa() {
		System.out.println("6 - 2="+(6-2));	
	}
	@TestNoBug
	private void chengfa() {
		System.out.println("11*11="+(11*11));
	}
	@TestNoBug
	private void chufa() {
		System.out.println("29 / 0="+(20/0));
	}
	@TestNoBug
	private void quyu() {
		System.out.println("1 / 1="+(4/3));
	}


}
