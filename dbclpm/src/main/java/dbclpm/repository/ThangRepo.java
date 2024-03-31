package dbclpm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dbclpm.entity.Thang;

public interface ThangRepo extends JpaRepository<Thang, Long> {
	List<Thang> findByNamId(Long id);

}
