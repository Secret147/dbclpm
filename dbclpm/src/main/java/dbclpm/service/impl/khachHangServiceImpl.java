package dbclpm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dbclpm.entity.khachHang;
import dbclpm.repository.luongDienTieuThuRepo;
import dbclpm.service.khachHangService;

@Service
public class khachHangServiceImpl implements khachHangService{
	@Autowired
	private luongDienTieuThuRepo ldttRe;

	public khachHangServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<khachHang> getListKhPayment() {
        List<Object[]> ob = ldttRe.getKhachHangctt();
        
        List<khachHang> khachHangs = new ArrayList<>();
        for(Object[] x :ob) {
        	khachHang kh = new khachHang(x);
        	if(!khachHangs.contains(kh)) {
        		khachHangs.add(kh);
        	}       	
        }
		return khachHangs;
	}

}
