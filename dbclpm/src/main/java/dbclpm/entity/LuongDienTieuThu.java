package dbclpm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class LuongDienTieuThu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long csm;
	
	private Long csc;
	
	private String state;

	public LuongDienTieuThu() {
		// TODO Auto-generated constructor stub
	}
	
	@ManyToOne
	@JsonIgnore
	private KhachHang khachHang;
	
	@ManyToOne
	private Thang thang;
	
	@ManyToOne
	private BacDien bacDien;
	
	@ManyToOne
	@JsonIgnore
	private HoaDon hoaDon;
	
	

}
