package dbclpm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dbclpm.entity.Thang;
import dbclpm.repository.ThangRepo;
import dbclpm.service.ThangService;

@Service
public class ThangServiceImpl implements ThangService{
	
    @Autowired
    private ThangRepo thangRe;
    
	public ThangServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Thang> getThangByNam(Long nam_id) {
		List<Thang> Thangs = thangRe.findByNamId(nam_id);
		return Thangs;
	}

}
