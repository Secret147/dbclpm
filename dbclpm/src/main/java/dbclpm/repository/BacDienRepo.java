package dbclpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dbclpm.entity.BacDien;

import java.util.List;

@Repository
public interface BacDienRepo extends JpaRepository<BacDien, Long> {
	BacDien findTop1ByEndValueLessThanOrderByEndValueDesc(double endValue);
  	BacDien findTop1ByStartValueGreaterThanOrderByStartValueAsc(double startValue);

	@Query("SELECT MAX(id) FROM BacDien")
	Long findMaxId();

	@Modifying
	@Query(value = "INSERT INTO bac_dien (price, name, start_value, end_value, description) VALUES (:price, :name, :startValue, :endValue, :description)", nativeQuery = true)
	void addNewBacDien(@Param("price") Long price, @Param("name") String name, @Param("startValue") Integer startValue,
			@Param("endValue") Integer endValue, @Param("description") String description);
	@Query("SELECT b FROM BacDien b WHERE b.endValue = (SELECT MAX(bb.endValue) FROM BacDien bb)")
    BacDien findPreviousBacDien();
	
	@Modifying
    @Query("DELETE FROM BacDien b WHERE b.id = :id")
    void deleteBacDienById(@Param("id") Long id);
	BacDien findFirstByOrderByIdDesc();
}
