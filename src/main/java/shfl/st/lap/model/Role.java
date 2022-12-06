package shfl.st.lap.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int roleId;
	private String roleName;
	/*
	 * @OneToMany(targetEntity = MenuMaster.class, mappedBy = "role", cascade =
	 * CascadeType.ALL, fetch = FetchType.EAGER)
	 * 
	 * @EqualsAndHashCode.Exclude Set<MenuMaster> menuList;
	 * 
	 * @Override public String toString() { return "Role [roleId=" + roleId +
	 * ", roleName=" + roleName + ", menuList=" + menuList + "]"; }
	 */

}
