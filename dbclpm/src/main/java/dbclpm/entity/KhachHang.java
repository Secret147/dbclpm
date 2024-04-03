package dbclpm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class KhachHang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String address;

	private String email;

	private String name;

	private String tel;

	private String note;

	@ManyToOne
	@JsonIgnore
	private Xa xa;

	public KhachHang() {
		// TODO Auto-generated constructor stub
	}

	public KhachHang(Object[] obj) {
		this.id = (Long) obj[0];
		this.address = (String) obj[2];
		this.email = (String) obj[3];
		this.name = (String) obj[4];
		this.note = (String) obj[5];
		this.tel = (String) obj[6];
	}

}
