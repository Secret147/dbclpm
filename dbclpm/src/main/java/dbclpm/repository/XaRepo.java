package dbclpm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dbclpm.entity.Xa;

public interface XaRepo extends JpaRepository<Xa, Long> {
	public List<Xa> findByHuyenId(long huyenId);
}
