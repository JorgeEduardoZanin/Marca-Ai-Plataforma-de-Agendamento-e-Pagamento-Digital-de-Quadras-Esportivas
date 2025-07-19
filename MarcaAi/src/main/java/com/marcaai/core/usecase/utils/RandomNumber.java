package com.marcaai.core.usecase.utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomNumber {

	public static int sixDigitRandomNumber() {
		return ThreadLocalRandom.current().nextInt(100000, 1000000);
	}
	
}
