package shfl.st.lap.employee.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
//@Entity
public class MenuMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int menuId;
	private String menuName;
	private int subMenuId;

}
