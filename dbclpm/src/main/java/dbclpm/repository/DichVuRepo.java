package dbclpm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dbclpm.entity.BacDien;
import dbclpm.entity.DichVu;

public interface DichVuRepo extends JpaRepository<DichVu, Long>{
	 List<DichVu> findAll();
}
