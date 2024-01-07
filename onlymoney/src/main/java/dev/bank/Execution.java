package dev.bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dev.bank.process.Calculate;
import dev.bank.process.ProductListProcess;
import dev.bank.user.Consumer;

public class Execution {
	private static final String RESOURCES = "/resources/";

	public void execute(final String fileName) {
		
		List<String> lines = new ArrayList<>();

		try (InputStream inputStream = MainBank.class.getResourceAsStream(RESOURCES + fileName);
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		// 전체 메뉴 객체 생성
		//final Path path = Paths.get(RESOURCES + fileName);
		Scanner sc = new Scanner(System.in);

		//List<String> lines;
		List<Product> products;
		ProductListProcess plp = new ProductListProcess();
		
		
		products = plp.createList(lines);
		
		plp.checkList(products);
		
		// 구매할 품목 입력받기
		plp.gettingProduct(products);
		
		System.out.print("신용등급을 입력해주세요: ");
		int credit = sc.nextInt();
		System.out.print("몇개월 할부를 하시겠습니까 :  ");
		int installment = sc.nextInt();
		
		Consumer consumer = new Consumer(credit, installment);
		
		Calculate cal = new Calculate();
		
		List<Integer> result = cal.monthlyCost(products, consumer);
		
		
		for(int i=0; i<result.size(); i++) {
			System.out.printf("[ %d 번째 달: " + result.get(i)+ "원]%n", i+1);
		}

	}

}
