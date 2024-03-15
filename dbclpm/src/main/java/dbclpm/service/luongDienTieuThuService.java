package dbclpm.service;

import java.util.List;

import dbclpm.entity.luongDienTieuThu;

public interface luongDienTieuThuService {
	Long getLuongdienTieuthuTheoThang(Long thangid);
	
	List<luongDienTieuThu> getLdttByThang(Long id);

}
