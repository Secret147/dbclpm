package dbclpm.service.impl;

import java.util.ArrayList;
import java.util.List;

import dbclpm.dto.LuongDienTieuThuDTO;
import dbclpm.ultilities.tien_dien.TienDienUltility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dbclpm.entity.LuongDienTieuThu;
import dbclpm.repository.LuongDienTieuThuRepo;
import dbclpm.service.LuongDienTieuThuService;

@Service
public class LuongDienTieuThuServiceImpl implements LuongDienTieuThuService {

	@Autowired
	private LuongDienTieuThuRepo ldttRepo;

	@Autowired
	private TienDienUltility tienDienUltility;

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

	@Override
	public List<LuongDienTieuThuDTO> findLdttByThangAndState(Long thangId, String state) {
		List<LuongDienTieuThu> ldtts = ldttRepo.findByThangIdAndState(thangId, state);
		List<LuongDienTieuThuDTO> ldttDTOs = new ArrayList<>();
		for(LuongDienTieuThu l : ldtts){
			LuongDienTieuThuDTO ldttDTO = new LuongDienTieuThuDTO();
			ldttDTO.setCustomerName(l.getKhachHang().getName());
			ldttDTO.setEmail(l.getKhachHang().getEmail());
			ldttDTO.setMonth(l.getThang().getName());
			ldttDTO.setYear(l.getThang().getNam().getName());
			ldttDTO.setAddress(l.getKhachHang().getAddress());
			ldttDTO.setCsc(l.getCsc());
			ldttDTO.setCsm(l.getCsm());
			ldttDTO.setCstt(l.getCsm() - l.getCsc());
			ldttDTO.setTienDien(tienDienUltility.tinhTienDien(l.getCsc(), l.getCsm()));
			ldttDTOs.add(ldttDTO);
		}
		return  ldttDTOs;
	}

}
