package dbclpm.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dbclpm.entity.HoaDon;
import dbclpm.entity.LuongDienTieuThu;
import dbclpm.entity.TieuThuTheoBac;
import dbclpm.ultilities.tien_dien.TienDienUltility;
import lombok.Data;

@Data
public class ThongKeDTO {

	private final long khachHangId;
	private final String khachHangDiaChi;
	private final long oldValue;
	private final long newValue;
	private final List<TieuThuTheoBac> dsTieuThuTheoBac;
	private final long tienDien;
	private final int trangThai;

	@Autowired
	private TienDienUltility tienDienUltility;

	public ThongKeDTO(LuongDienTieuThu luongDienTieuThu, HoaDon hoaDon) {
		super();
		this.khachHangId = luongDienTieuThu.getKhachHang().getId();
		this.khachHangDiaChi = luongDienTieuThu.getKhachHang().getAddress();
		this.oldValue = luongDienTieuThu.getCsc();
		this.newValue = luongDienTieuThu.getCsm();
		this.dsTieuThuTheoBac = luongDienTieuThu.getList();

		// Nếu xem thống kê tháng hiện tại (=> chưa có hóa đơn)
		if (hoaDon == null) {
			tienDien = tienDienUltility.tinhTienDien(oldValue, newValue);
			this.trangThai = -1;
		} else {
			// Nếu có hóa đơn rồi
			tienDien = hoaDon.getTotal();
			if (luongDienTieuThu.getState().equals("0")) {
				this.trangThai = 0;
			} else {
				this.trangThai = 1;
			}
		}
	}
}