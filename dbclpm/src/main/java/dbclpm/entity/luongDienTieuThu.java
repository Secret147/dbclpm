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
public class luongDienTieuThu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long csm;
	
	private Long csc;
	
	private String state;

	public luongDienTieuThu() {
		// TODO Auto-generated constructor stub
	}
	
	@ManyToOne
	@JsonIgnore
	private khachHang khachHang;
	
	@ManyToOne
	private thang thang;
	
	@ManyToOne
	private bacDien bacDien;
	
	@ManyToOne
	@JsonIgnore
	private hoaDon hoaDon;
	
	

}
