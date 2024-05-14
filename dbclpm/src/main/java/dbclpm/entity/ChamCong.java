package dbclpm.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.sql.Date;
@Data
@Entity
public class ChamCong {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

 
    private Date date;


    private String hoursWork;


    
    @ManyToOne
    private NhanVien nhanvien;

}
