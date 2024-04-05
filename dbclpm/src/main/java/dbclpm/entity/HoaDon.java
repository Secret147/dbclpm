package dbclpm.entity;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Data
@Entity
public class HoaDon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long total;
	
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date created_at;
	
	private String description;
	
	@OneToMany(mappedBy = "hoaDon")
	@JsonIgnore
	private List<LuongDienTieuThu> list = new ArrayList<>();
	
	@ManyToOne
	private NhanVien nhanVien;
	
	@ManyToOne
	private KhachHang khachHang;
	
	@PrePersist
    protected void onCreate() {
        created_at = new Date();
    }

	public HoaDon() {
		// TODO Auto-generated constructor stub
	}

}
