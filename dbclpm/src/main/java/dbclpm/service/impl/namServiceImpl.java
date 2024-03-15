package dbclpm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dbclpm.entity.nam;
import dbclpm.entity.thang;
import dbclpm.repository.namRepo;
import dbclpm.repository.thangRepo;
import dbclpm.service.namService;

@Service
public class namServiceImpl  implements namService{
	@Autowired
	private namRepo namRe;
	
	@Autowired
	private thangRepo thangRe;

	public namServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<thang> getListThangByNam(Long namid) {
		List<thang> thangs = thangRe.findByNamId(namid);
		return thangs;
	}

	@Override
	public List<nam> getListNam() {
		List<nam> nams = namRe.findAll();
		return nams;
	}

}
