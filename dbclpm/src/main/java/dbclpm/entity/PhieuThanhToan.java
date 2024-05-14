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
public class PhieuThanhToan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date date;
	private int totalMount;
	
	@OneToMany(mappedBy = "ptt")
	@JsonIgnore
	List<DichVuPhieuThanhToan> dichvuptts = new ArrayList<>();
	
	@ManyToOne
	private KhachHang khachhang;
	
}

