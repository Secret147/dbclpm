package dbclpm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dbclpm.entity.KhachHang;
import dbclpm.entity.LuongDienTieuThu;
import dbclpm.repository.LuongDienTieuThuRepo;
import dbclpm.service.KhachHangService;

@Service
public class KhachHangServiceImpl implements KhachHangService{
	@Autowired
	private LuongDienTieuThuRepo ldttRe;

	public KhachHangServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<LuongDienTieuThu> getListKhPayment() {
		
        List<LuongDienTieuThu> ob = ldttRe.getKhachHangctt();
        
        
		return ob;
	}

}
