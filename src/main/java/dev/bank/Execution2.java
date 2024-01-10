package dev.bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Filter;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import dev.bank.process.Calculate;
import dev.bank.process.ProductListProcess;
import dev.bank.user.Consumer;
public class Execution2 {
	private static final String RESOURCES = "/resources/";
//	private static final String RESOURCES2 = “src/main/resources/“;
	Scanner sc = new Scanner(System.in);
	
	private static final Logger logger = 
			Logger.getLogger(Execution2.class.getName());
	
	public int checkingValue(ValueFilter valFilter) {
		while(true) {
			try {
				int credit = sc.nextInt();
				if(valFilter.filterValue(credit)) {
					System.out.println("허용하지 않는 값입니다. 다시입력해주세요.\n>> ");	
					logger.log(Level.WARNING, "예상외 값 입력");
					continue;
				}
				return credit;
			}catch(Exception e) {
				System.out.println("문자를 입력하지 마세요");
				logger.log(Level.WARNING, "문자 입력");
				sc.nextLine();
				continue;
			}
		}
	}
	public void execute(final String fileName) throws IOException {
		
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
		final Path path = Paths.get(RESOURCES + fileName);
		//List<String> lines = Files.readAllLines(path);
		List<Product> products;
		ProductListProcess plp = new ProductListProcess();
		
		
		products = plp.createList(lines);
		
		plp.checkList(products);
		
		// 구매할 품목 입력받기
		plp.gettingProduct(products);
		
		
		
		
		int credit = 0;
		int installment=0;
		
		System.out.println("신용등급을 입력하세요: ");
		ValueFilter creditFilter = inputCredit -> inputCredit < 1 || inputCredit > 5;
		credit = checkingValue(creditFilter);
		
		System.out.println("몇개월 할부를 하시겠습니까 :  ");
		ValueFilter installmentFilter = inputInstall -> inputInstall<1;
		installment = checkingValue(installmentFilter);
		
		final String myFile = "myfile.log";
		Handler consoleHandler = new ConsoleHandler();
		
		Formatter formatter = new MyFormatter();
		Filter myFilter = new MyFilter(1);
		
		consoleHandler.setFormatter(formatter);  
		logger.setFilter(myFilter);
		
		//logger.addHandler(consoleHandler);
		
		logger.log(Level.INFO, String.format("%d등급 고객님 요청", credit));
		
		
		Consumer consumer = new Consumer(credit, installment);
		
		Calculate cal = new Calculate();
		
		List<Integer> result = cal.monthlyCost(products, consumer);
		
		
		for(int i=0; i<result.size(); i++) {
			System.out.printf("[ %d 번째 달: " + result.get(i)+ "원]%n", i+1);
		}
	}
}
