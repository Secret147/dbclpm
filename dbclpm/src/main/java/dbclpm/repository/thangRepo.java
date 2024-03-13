package dbclpm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dbclpm.entity.thang;

public interface thangRepo extends JpaRepository<thang, Long> {
	List<thang> findByNamId(Long id);

}
