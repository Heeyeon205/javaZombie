package zombie;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Utils {
	private static Utils instance;
	private Scanner sc = new Scanner(System.in);
	private Random rd = new Random();
	
	private Utils() {}
	public static Utils getInstance() {
		if(instance == null) {
			instance = new Utils();
		}
		return instance;
	}
	
	public int getValue(String msg, int min, int max) {
		while(true) {
			System.out.println(msg);
		try {
			int sel = sc.nextInt();
			if(sel < min || sel >= max) {
				System.out.println("입력 범위 오류입니다.");
				continue;
			}
			return sel;
		}catch(InputMismatchException e) {
			System.out.println("숫자만 입력 가능합니다.");
			continue;
		}
		}
	}
		
	public int getRandomValue(int num) {
		return rd.nextInt(num);
	}
}
