package dbclpm.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class HoaDon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private long total;

	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdAt;

	private String description;

	@OneToOne
	@JsonIgnore
	private LuongDienTieuThu luongDienTieuThu;

	@ManyToOne
	@JsonIgnore
	private NhanVien nhanVien;

	@ManyToOne
	@JsonIgnore
	private KhachHang khachHang;

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
	}

}
