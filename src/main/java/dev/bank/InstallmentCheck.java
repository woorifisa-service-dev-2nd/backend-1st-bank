package dev.bank;

import java.util.Scanner;

public class InstallmentCheck implements Check {

	@Override
	public int checking() {
		Scanner sc = new Scanner(System.in);
		int installment = 0;
		
		while(true) {
			try {
				System.out.print("몇개월 할부를 하시겠습니까 :  ");
				installment = sc.nextInt();
				if(installment <1) {
					System.out.println("할부는 최소 1개월 이상이여야 합니다.");	
					continue;
				}
				
			}catch(Exception e) {
				System.out.println("문자를 입력하지 마세요");
				sc.nextLine();
				continue;
			}
			break;
		}
		
		return installment;

	}

}
