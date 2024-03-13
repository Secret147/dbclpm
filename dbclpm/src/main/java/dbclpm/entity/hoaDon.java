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
public class hoaDon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long total;
	
	private Date create_at;
	
	private String description;
	
	@OneToMany(mappedBy = "hoaDon")
	@JsonIgnore
	private List<luongDienTieuThu> list = new ArrayList<>();
	
	@ManyToOne
	@JsonIgnore
	private nhanVien nhanVien;
	
	@ManyToOne
	@JsonIgnore
	private khachHang khachHang;

	public hoaDon() {
		// TODO Auto-generated constructor stub
	}

}
