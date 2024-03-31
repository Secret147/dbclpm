package dbclpm.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class BacDien {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long price;
	
	private String name;
	
	private String description;
	
	@OneToMany(mappedBy = "bacDien")
	@JsonIgnore
	private List<LuongDienTieuThu> list = new ArrayList<>();
	

	public BacDien() {
		// TODO Auto-generated constructor stub
	}

}
