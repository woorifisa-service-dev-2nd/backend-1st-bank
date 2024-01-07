package dev.bank.process;

import java.util.ArrayList;
import java.util.List;

import dev.bank.Product;
import dev.bank.user.Consumer;

public class Calculate {
	private int[][] creditResult = { { 6, 10 }, { 5, 12 }, { 4, 14 }, { 3, 16 }, { 2, 18 } }; // [등급][무이자할부개월수, 이자율]

	
	
	private int totalCost(List<Product> products) { // 전체 물건 합산비용
		int total = 0;
		for (Product product : products) {
			if (product.getCount() > 0) {
				total += product.getCost() * product.getCount();
			}
		}
		return total;
	}
	
	private List<Integer> interest(int total, int installment){ //할부 금액 계산
		List<Integer> listCostPay = new ArrayList<Integer>();
		int costPay = (total / installment);
		for (int i = 0; i < installment; i++) {
			listCostPay.add(costPay);
		}
		return listCostPay;
	}

	private List<Integer> interestFree(int total, int installment) { // 무이자할부로 가능한 경우
		
		return interest(total, installment);
	}

	
	private List<Integer> interestGrant(int total, int installment, int interestRate) { // 무이자 할부 불가능
		
		total = (total*(100 + interestRate))/100;
		
		return interest(total, installment);
	}

	public List<Integer> monthlyCost(List<Product> products, Consumer consumer) {// 할부금액 각 달에 맞게 리스트 뽑는 메서드
		int total = totalCost(products);
		if (consumer.getInstallment() > creditResult[consumer.getCreditRating() - 1][0]) {
			// 이자로 계산하는 함수
			
			return interestGrant(total, consumer.getInstallment(), creditResult[consumer.getCreditRating() - 1][1]);
		}else {
			// 무이자로 계산 함수
			return interestFree(total, consumer.getInstallment());
		}
	}

}
