package dbclpm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dbclpm.entity.HoaDon;

public interface HoaDonRepo extends JpaRepository<HoaDon, Long> {
	List<HoaDon> findByKhachHangXaID(long xaId);

	List<HoaDon> findByKhachHangXaHuyenID(long huyenId);

	List<HoaDon> findByKhachHangXaHuyenTinhID(long tinhId);
}
