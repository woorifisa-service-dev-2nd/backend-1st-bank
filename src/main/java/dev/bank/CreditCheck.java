package dev.bank;

import java.util.Scanner;

public class CreditCheck implements Check {

	@Override
	public int checking() {
		Scanner sc = new Scanner(System.in);
		int credit = 0;
		
		while(true) {
			try {
				System.out.print("신용등급을 입력해주세요: ");
				credit = sc.nextInt();
				if(credit <1 || credit>5) {
					System.out.println("신용등급은 1~5까지입니다.");	
					continue;
				}
				
			}catch(Exception e) {
				System.out.println("문자를 입력하지 마세요");
				sc.nextLine();
				continue;
			}
			break;
		}
		
		return credit;

	}

}
