package dbclpm.ultilities.tien_dien;

import dbclpm.entity.BacDien;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BacDienUtilImpl implements BacDienUtil{

    @Override
    public String generateElectricityTierText(List<BacDien> bacDiens) {
        StringBuilder textContent = new StringBuilder();
        textContent.append("Bảng Bậc Điện\n");
        textContent.append("Bậc\tTừ (kWh)\tĐến (kWh)\tGiá (VNĐ/kWh)\n");

        for (BacDien tier : bacDiens) {
            textContent.append(tier.getName()).append("\t\t");
            textContent.append(tier.getStartValue()).append("\t\t");

            // Xử lý giá trị "Đến (kWh)"
            String endValue = (tier.getEndValue() == null || tier.getEndValue() == 1000) ? "Trở lên" : String.valueOf(tier.getEndValue());
            textContent.append(endValue).append("\t\t");

            textContent.append(tier.getPrice()).append("\n");
        }

        return textContent.toString();
    }


}
