package dbclpm.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LuongDienTieuThuDTO {
    private String customerName;
    private String email;
    private String address;
    private String month;
    private String year;
    private Long csc;
    private Long csm;
    private Long cstt;
    private Long tienDien;
}
