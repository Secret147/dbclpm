package dbclpm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dbclpm.entity.HoaDon;
import dbclpm.repository.HoaDonRepo;
import dbclpm.service.HoaDonService;

@Service
public class HoaDonServiceImpl implements HoaDonService{
	
	@Autowired
	private HoaDonRepo hoaDonRe;

	public HoaDonServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long getTotal() {
		// TODO Auto-generated method stub
		return hoaDonRe.getSumDoanhThu();
	}

	@Override
	public List<HoaDon> getListHoaDon() {
		// TODO Auto-generated method stub
		return hoaDonRe.findAll();
	}

}
