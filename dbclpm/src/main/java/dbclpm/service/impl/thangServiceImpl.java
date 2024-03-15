package dbclpm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dbclpm.entity.thang;
import dbclpm.repository.thangRepo;
import dbclpm.service.thangService;

@Service
public class thangServiceImpl implements thangService{
	
    @Autowired
    private thangRepo thangRe;
    
	public thangServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<thang> getThangByNam(Long nam_id) {
		List<thang> thangs = thangRe.findByNamId(nam_id);
		return thangs;
	}

}
