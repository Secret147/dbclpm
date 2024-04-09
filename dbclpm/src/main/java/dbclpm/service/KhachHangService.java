package dbclpm.service;

import java.util.List;
import java.util.Map;

import dbclpm.dto.KhachHangDTO;
import dbclpm.entity.KhachHang;
import dbclpm.entity.LuongDienTieuThu;

public interface KhachHangService {

	List<KhachHang> getAllKhachHang();
	List<LuongDienTieuThu> getListKhPayment();

	List<KhachHang> findByXaOrHuyenOrTinh(Map<String, Object> params);

}
