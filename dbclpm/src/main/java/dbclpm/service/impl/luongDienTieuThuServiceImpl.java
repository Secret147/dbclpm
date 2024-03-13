package dbclpm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dbclpm.entity.thang;
import dbclpm.repository.luongDienTieuThuRepo;
import dbclpm.repository.thangRepo;
import dbclpm.service.luongDienTieuThuService;

@Service
public class luongDienTieuThuServiceImpl implements luongDienTieuThuService {
	
	@Autowired
	private luongDienTieuThuRepo ldttRepo;
	
	@Autowired
	private thangRepo thangRe;

	public luongDienTieuThuServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long getLuongdienTieuthuTheoThang(Long thangid) {
		Long result = ldttRepo.getLuongdienTieuThuByThang(thangid);	
		if(result != null) {
			return result;
		}
		return 0L;
		
	}

}
