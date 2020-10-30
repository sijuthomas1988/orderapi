package com.skr.airasia.orderapi;

import com.skr.airasia.orderapi.dto.HotelDto;
import com.skr.airasia.orderapi.repository.HotelInformationRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import java.text.NumberFormat;
import java.util.stream.Stream;

@SpringBootApplication
@EnableJms
public class OrderApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApiApplication.class, args);
	}

	@Bean
	ApplicationRunner init(HotelInformationRepository repository) {

		String[][] data = {
				{"1", "1234_KL_Hilton", "Hilton_KL", "1234_Standard_Hilton_KL", "Standard", "Hilton_KL_Standard", "15", "15"},
				{"2", "1234_KL_Hilton", "Hilton_KL", "1234_Suite_Hilton_KL", "Suite", "Hilton_KL_Suite", "6", "6"},
				{"3", "1234_KL_Regis", "Regis_KL", "1234_Suite_Regis_KL", "Suite", "regis_KL_Suite", "0", "0"},
				{"4", "1234_KL_Regis", "Regis_KL", "1234_Standard_Regis_KL", "Standard", "regis_KL_Standard", "1", "1"}
		};

		return args -> {
			Stream.of(data).forEach(array -> {
				try {
					HotelDto hotelDetails = new HotelDto(
							Long.valueOf(array[0]),
							array[1],
							array[2],
							array[3],
							array[4],
							array[5],
							Long.valueOf(array[6]),
							Long.valueOf(array[7])
					);
					repository.save(hotelDetails);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			});
			repository.findAll().forEach(System.out::println);
		};
	}

}
