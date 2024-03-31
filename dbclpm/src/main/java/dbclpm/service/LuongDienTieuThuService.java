package dbclpm.service;

import java.util.List;

import dbclpm.entity.LuongDienTieuThu;

public interface LuongDienTieuThuService {
	Long getLuongdienTieuthuTheoThang(Long thangid);
	
	List<LuongDienTieuThu> getLdttByThang(Long id);

}
