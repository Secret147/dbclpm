package dbclpm.service.impl;

import dbclpm.entity.BacDien;
import dbclpm.repository.BacDienRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dbclpm.service.BacDienService;

import java.util.List;

@Service
public class BacDienServiceImpl implements BacDienService {
	@Autowired
	private BacDienRepo bacDienRepo;
	@Override
	public List<BacDien> getAllBacDien() {
		return bacDienRepo.findAll();
	}

	public BacDienServiceImpl() {
		// TODO Auto-generated constructor stub
	}

}
