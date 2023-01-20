package shfl.st.lap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import shfl.st.lap.debittype.model.DebitType;
import shfl.st.lap.debittype.service.DebitTypeService;
import shfl.st.lap.frequency.model.FrequencyType;
import shfl.st.lap.frequency.service.FrequencyTypeService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class StlapApplication implements ApplicationRunner {

	@Autowired
	FrequencyTypeService frequencyTypeService;

	@Autowired
	DebitTypeService debitTypeService;

	public static void main(String[] args) {
		SpringApplication.run(StlapApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		insertFrequencyData();
		insertDebitData();

	}

	private void insertDebitData() {
		debitTypeService.deleteAll();
		List<DebitType> debitList = new ArrayList<>();
		DebitType debitType1 = new DebitType(1, "Fixed Amount");
		DebitType debitType2 = new DebitType(2, "Maximum Amount");
		debitList.add(debitType1);
		debitList.add(debitType2);
		debitTypeService.insertDebit(debitList);

	}

	private void insertFrequencyData() {
		frequencyTypeService.deleteAll();
		List<FrequencyType> frequencyList = new ArrayList<>();
		FrequencyType frequencyType1 = new FrequencyType(1, "Monthly");
		FrequencyType frequencyType2 = new FrequencyType(2, "Yearly");
		FrequencyType frequencyType3 = new FrequencyType(3, "As and When required");
		frequencyList.add(frequencyType1);
		frequencyList.add(frequencyType2);
		frequencyList.add(frequencyType3);
		frequencyTypeService.insertFrequency(frequencyList);

	}

}
