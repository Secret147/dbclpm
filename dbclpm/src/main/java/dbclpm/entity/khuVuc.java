package dbclpm.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class khuVuc {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String address;
	
	private String description;
	
	@OneToMany(mappedBy = "khuVuc")
	private List<khachHang> list = new ArrayList<>();

	public khuVuc() {
		// TODO Auto-generated constructor stub
	}

}
