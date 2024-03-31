package dbclpm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dbclpm.entity.Nam;
import dbclpm.entity.Thang;
import dbclpm.repository.NamRepo;
import dbclpm.repository.ThangRepo;
import dbclpm.service.NamService;

@Service
public class NamServiceImpl  implements NamService{
	@Autowired
	private NamRepo namRe;
	
	@Autowired
	private ThangRepo thangRe;

	public NamServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Thang> getListThangByNam(Long namid) {
		List<Thang> Thangs = thangRe.findByNamId(namid);
		return Thangs;
	}

	@Override
	public List<Nam> getListNam() {
		List<Nam> Nams = namRe.findAll();
		return Nams;
	}

}
