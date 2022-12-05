package shfl.st.lap.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
//@Entity
public class MenuMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int menuId;
	private String menuName;
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
	@OneToMany(targetEntity = SubMenu.class, mappedBy = "menuList", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@EqualsAndHashCode.Exclude
	private Set<SubMenu> subMenus;

	@Override
	public String toString() {
		return "MenuMaster [menuId=" + menuId + ", menuName=" + menuName + ", role=" + role.getRoleId() + ", subMenus=" + subMenus
				+ "]";
	}

}
