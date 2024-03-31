package dbclpm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dbclpm.entity.KhachHang;
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
	public List<KhachHang> getListKhPayment() {
        List<Object[]> ob = ldttRe.getKhachHangctt();
        
        List<KhachHang> KhachHangs = new ArrayList<>();
        for(Object[] x :ob) {
        	KhachHang kh = new KhachHang(x);
        	if(!KhachHangs.contains(kh)) {
        		KhachHangs.add(kh);
        	}       	
        }
		return KhachHangs;
	}

}
