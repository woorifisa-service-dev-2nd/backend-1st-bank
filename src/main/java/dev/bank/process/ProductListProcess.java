package dev.bank.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dev.bank.Product;

public class ProductListProcess {
	
	public List<Product> createList(List<String> lines){ //리스트 생성
		List<Product> products = new ArrayList<Product>();
		
		for (String line : lines) {
			String[] columns = line.split(",");
			String productCode = columns[0];
			String name = columns[1];
			int cost = Integer.parseInt(columns[2]);
			Product product = new Product(productCode, name, cost);
			products.add(product);
		}
		
		return products;
	}
	
	public void checkList(List<Product> products) {// 리스트 출력
		System.out.println("----------------------");
		for (Product product : products) {
			System.out.println(
					"|" + product.getProductCode() + "|" + product.getName() + "|" + product.getCost() + "|");
		}
		System.out.println("----------------------");
	}
	
	public void gettingProduct(List<Product> products) {//구매할 품목 추가
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("구매할 품목을 입력해주세요: ");
			String a = sc.next(); //구매할 품목

			boolean flag = false; // 상품리스트에 있는지 true면 있는 것, false면 없음
			
			String reBuy; // 추가 구매하는 지 확인용

			for (Product product : products) { //리스트 확인하면서 있으면 구매 개수 묻기
				if (product.getName().equals(a)) {
					flag = true;
					while(true) {
						System.out.print("구매할 갯수를 입력해주세요: ");
						try {
							int i = sc.nextInt();
							if(i<1) {
								System.out.println("개수를 1이상으로 해주세요");	
								continue;
							}
							
							product.setCount(product.getCount() + i);
							
						}catch(Exception e) {
							System.out.println("문자를 입력하지 마세요");
							sc.nextLine();
							continue;
						}
						break;
					}
					
					
					
				}
				
			}

			if (flag == false) {
				System.out.println("상품 리스트에 없는 품목입니다.");
				continue;
			}
			else {
				while (true) { // 더 구매할지 물어보는 반복문
					
					System.out.println("더 구매하시겠습니까? 1.네, 2.아니오");
					reBuy = sc.next();
					if (reBuy.equals("1")|| reBuy.equals("네")) {
						break;
					} else if (reBuy.equals("2")|| reBuy.equals("아니오")) {
						break;
					} else { //비정상적인 입력시
						System.out.println("잘못 입력하셨습니다.");;
						System.out.println(reBuy);
						
					}
				}
				// troubleShooting 1 - 더 구매할때 비정상 입력시 처리
				if(reBuy.equals("1")|| reBuy.equals("네"))
					continue;
				
				else if(reBuy.equals("2")|| reBuy.equals("아니오"))
					break;		
			}
		}
	}

}
