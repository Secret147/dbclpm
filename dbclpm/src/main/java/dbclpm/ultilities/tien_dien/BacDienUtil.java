package dbclpm.ultilities.tien_dien;

import dbclpm.entity.BacDien;

import java.util.List;

public interface BacDienUtil {
    public String generateElectricityTierText(List<BacDien> bacDiens);
}
