package dbclpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dbclpm.entity.BacDien;


public interface BacDienRepo extends JpaRepository<BacDien, Long>{
	BacDien findTop1ByEndValueLessThanOrderByEndValueDesc(double endValue);
    BacDien findTop1ByStartValueGreaterThanOrderByStartValueAsc(double startValue);
}
