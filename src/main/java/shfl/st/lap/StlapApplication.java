package shfl.st.lap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.twilio.Twilio;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class StlapApplication {
	
	static {
		Twilio.init("AC095a19cec3101b3ace4523c41f87796a", "9ba3c1dd73400b4d0c2b9a6aafebf5b9");
	}

	public static void main(String[] args) {
		SpringApplication.run(StlapApplication.class, args);
	}


}
