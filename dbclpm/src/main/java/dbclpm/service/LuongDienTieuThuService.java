package dbclpm.service;

import java.util.List;

import dbclpm.dto.LuongDienTieuThuDTO;
import dbclpm.entity.LuongDienTieuThu;

public interface LuongDienTieuThuService {
	Long getLuongdienTieuthuTheoThang(Long thangid);
	
	List<LuongDienTieuThu> getLdttByThang(Long id);

	List<LuongDienTieuThuDTO> findLdttByThangAndNamAndState(Long thangId, Long namId, String state);

}
