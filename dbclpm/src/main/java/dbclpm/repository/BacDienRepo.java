package dbclpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dbclpm.entity.BacDien;

import java.util.List;


public interface BacDienRepo extends JpaRepository<BacDien, Long>{
	BacDien findTop1ByEndValueLessThanOrderByEndValueDesc(double endValue);
  BacDien findTop1ByStartValueGreaterThanOrderByStartValueAsc(double startValue);
}
