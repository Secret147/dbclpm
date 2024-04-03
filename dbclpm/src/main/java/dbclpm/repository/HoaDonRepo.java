package dbclpm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dbclpm.entity.HoaDon;

public interface HoaDonRepo extends JpaRepository<HoaDon, Long> {
	List<HoaDon> findByKhachHangXaId(long xaId);

	List<HoaDon> findByKhachHangXaHuyenId(long huyenId);

	List<HoaDon> findByKhachHangXaHuyenTinhId(long tinhId);
}
