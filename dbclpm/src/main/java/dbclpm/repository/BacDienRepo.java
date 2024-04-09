package dbclpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dbclpm.entity.BacDien;

import java.util.List;


public interface BacDienRepo extends JpaRepository<BacDien, Long>{
    List<BacDien> findAll();
}
