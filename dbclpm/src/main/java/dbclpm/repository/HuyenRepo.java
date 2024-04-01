package dbclpm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dbclpm.entity.Huyen;

public interface HuyenRepo extends JpaRepository<Huyen, Long> {
	public List<Huyen> findByTinhId(long tinhId);
}
