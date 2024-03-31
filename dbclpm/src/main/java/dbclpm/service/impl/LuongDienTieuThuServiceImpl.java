package dbclpm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dbclpm.entity.LuongDienTieuThu;
import dbclpm.repository.LuongDienTieuThuRepo;
import dbclpm.service.LuongDienTieuThuService;

@Service
public class LuongDienTieuThuServiceImpl implements LuongDienTieuThuService {

	@Autowired
	private LuongDienTieuThuRepo ldttRepo;

	public LuongDienTieuThuServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long getLuongdienTieuthuTheoThang(Long thangid) {
		Long result = ldttRepo.getLuongdienTieuThuByThang(thangid);
		if (result != null) {
			return result;
		}
		return 0L;

	}

	@Override
	public List<LuongDienTieuThu> getLdttByThang(Long id) {
		List<LuongDienTieuThu> ldtts = ldttRepo.findByThangId(id);
		return ldtts;
	}

}
