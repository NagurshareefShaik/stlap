package shfl.st.lap.employee.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
//@Entity
public class SubMenu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String subMenuName;
	@ManyToOne
	@JoinColumn(name = "menu_id")
	private MenuMaster menuList;
	@Override
	public String toString() {
		return "SubMenu [id=" + id + ", subMenuName=" + subMenuName + ", menuList=" + menuList.getMenuId() + "]";
	}
	
	
	

}
