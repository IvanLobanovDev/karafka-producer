package telran.java51;

import java.util.Random;
import java.util.function.Supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KarafkaProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KarafkaProducerApplication.class, args);
	}

	@Bean
	Supplier<String> stringSupplier() {
		return () -> supplierRunner();
	}
	
	String supplierRunner() {
		while (true) {
			String str = getSaltString();
			if (str.charAt(0) == '0') {
				try {
					System.out.println("Str starts from '0' is forbiden");
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return "";
			}
			return str;
		}
	}
	
	protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < rnd.nextInt(8, 16)) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

}
