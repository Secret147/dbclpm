package dbclpm.service;
import dbclpm.entity.BacDien;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface BacDienService {
    List<BacDien> getAllBacDien();
    BacDien findBacDienById(long id);
    BacDien findPreviousBacDien(Integer startValue);
    BacDien findNextBacDien(Integer endValue);
    boolean validateAndUpdateBacDien(BacDien currentBacDien, BacDien previousBacDien, BacDien nextBacDien, BacDien request);
    BacDien getMaxBacDien();
    boolean addNewBacDien(BacDien bacdien);
    boolean deleteBacDien(long idToDelete);
}
