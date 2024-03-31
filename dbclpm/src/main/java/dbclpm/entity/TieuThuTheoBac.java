package dbclpm.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Entity
@Data
public class TieuThuTheoBac {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date created_at;
	
	private int value;
	
	private Long price;
	
	@PrePersist
    protected void onCreate() {
        created_at = new Date();
    }
	
	@ManyToOne 
	private BacDien bacDien;
	
	@ManyToOne 
	@JsonIgnore
	private LuongDienTieuThu ldtt;
	
	
	
	

}
