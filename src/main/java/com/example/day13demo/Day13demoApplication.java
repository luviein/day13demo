package com.example.day13demo;

import java.util.List;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.day13demo.utility.Utility;

@SpringBootApplication
public class Day13demoApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Day13demoApplication.class);
		//SpringApplication.run(Day13demoApplication.class, args);
	
		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);

		List<String> opsVal = appArgs.getOptionValues("dataDir");

		System.out.println(opsVal);

		if(opsVal != null){

			//create dir
			Utility.createDir(opsVal.get(0));
		}else{
			//terminate program
			System.out.println("No data dir provided");
			System.exit(1);

		}
		app.run(args);
	}

	
	


}
