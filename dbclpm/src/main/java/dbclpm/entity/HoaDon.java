package dbclpm.entity;

import java.sql.Date;
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

@Data
@Entity
public class HoaDon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long total;
	
	private Date createAt;
	
	private String description;
	
	@OneToMany(mappedBy = "hoaDon")
	@JsonIgnore
	private List<LuongDienTieuThu> list = new ArrayList<>();
	
	@ManyToOne
	@JsonIgnore
	private NhanVien NhanVien;
	
	@ManyToOne
	@JsonIgnore
	private KhachHang KhachHang;

	public HoaDon() {
		// TODO Auto-generated constructor stub
	}

}
