package dbclpm.entity;

import java.util.List;

import jakarta.persistence.*;
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

	@ManyToOne
	private KhachHang khachHang;

	@ManyToOne
	private Thang thang;

	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name = "luong_dien_tieu_thu_id")
	private List<TieuThuTheoBac> dsTieuThuTheoBac;

}
