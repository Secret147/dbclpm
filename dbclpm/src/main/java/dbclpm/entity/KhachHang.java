package dbclpm.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

	private String numberPhone;

	private String note;

	@OneToMany(mappedBy = "khachHang")
	@JsonIgnore
	private List<LuongDienTieuThu> list = new ArrayList<>();

	@ManyToOne
	@JsonIgnore
	private KhuVuc khuVuc;

	@OneToMany(mappedBy = "khachHang")
	private List<HoaDon> listHoaDon = new ArrayList<>();

	public KhachHang() {
		// TODO Auto-generated constructor stub
	}

	public KhachHang(Object[] obj) {
		this.id = (Long) obj[0];
		this.address = (String) obj[2];
		this.email = (String) obj[3];
		this.note = (String) obj[4];
		this.numberPhone = (String) obj[5];
		this.name = (String) obj[6];
	}

}
