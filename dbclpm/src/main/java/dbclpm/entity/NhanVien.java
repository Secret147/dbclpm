package dbclpm.entity;


import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class NhanVien {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String username;

	private String password;

	
	@OneToMany(mappedBy = "nhanVien")
	@JsonIgnore
	@Transient
	private List<HoaDon> list = new ArrayList<>();


	private String role;

	private String description;
}
