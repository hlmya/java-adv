package coreservlets_stream;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stream_p3 {

	/*
	 *  • Print 5 random doubles
		• Make a List of 10 random doubles
		• Make an array of 20 random doubles
	 */
	public static void main(String[] args) {
		//Print 5 random doubles
	    randomNums(10).limit(5).forEach(System.out::println);
	    
	    //Make a LIST of 10 random doubles
		List<Double> listDouble = randomNums(10).limit(10).collect(Collectors.toList());
		System.out.println(listDouble);
		
		//Make an ARRAY of 20 random doubles
		Double[] arrayDouble = randomNums(10).limit(10).toArray(Double[]::new);
		System.out.println(Arrays.asList(arrayDouble));
	}

	  // Or, use DoubleStream.generate, which produces DoubleStream
	  // instead of Stream<Double>
	  public static Stream<Double> randomNums(double maxValue) {
	    return(Stream.generate(() -> Math.random() * maxValue));
	  }

}
