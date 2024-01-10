package dev.bank;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class MyFilter implements Filter {
	private int credit;

	public MyFilter(int credit) {
		super();
		this.credit = credit;
	}

	@Override
	public boolean isLoggable(LogRecord record) {
		
		if(record.getMessage().contains(String.valueOf(credit))) {
			return true;
		}
		
		return false;
	}

}
