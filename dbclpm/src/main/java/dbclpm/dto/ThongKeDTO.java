package dbclpm.dto;

import java.util.List;

import dbclpm.entity.HoaDon;
import dbclpm.entity.LuongDienTieuThu;
import dbclpm.entity.TieuThuTheoBac;
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

	public ThongKeDTO(LuongDienTieuThu luongDienTieuThu, HoaDon hoaDon, long tienDien) {
		this.khachHangId = luongDienTieuThu.getKhachHang().getId();
		this.khachHangDiaChi = luongDienTieuThu.getKhachHang().getAddress();
		this.oldValue = luongDienTieuThu.getCsc();
		this.newValue = luongDienTieuThu.getCsm();
		this.dsTieuThuTheoBac = luongDienTieuThu.getDsTieuThuTheoBac();

		// Nếu xem thống kê tháng hiện tại (=> chưa có hóa đơn)
		if (hoaDon == null) {
			this.tienDien = tienDien;
			this.trangThai = -1;
		} else {
			// Nếu có hóa đơn rồi
			this.tienDien = hoaDon.getTotal();
			if (luongDienTieuThu.getState().equals("0")) {
				this.trangThai = 0;
			} else {
				this.trangThai = 1;
			}
		}
	}
}