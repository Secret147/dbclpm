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
public class NhanVien {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String username;
	
	private String password;
	
	private String chucvu;
	
	private String description;
	
	@OneToMany(mappedBy = "nhanVien")
	private List<HoaDon> list = new ArrayList<>();

	public NhanVien() {
		// TODO Auto-generated constructor stub
	}

}
