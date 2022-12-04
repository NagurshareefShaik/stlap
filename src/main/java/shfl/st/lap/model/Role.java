package shfl.st.lap.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Singular;
import lombok.ToString;

@Data
@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int roleId;
	private String roleName;
	@OneToMany(targetEntity = MenuMaster.class, mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<MenuMaster> menuList;

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", menuList=" + menuList + "]";
	}

}
