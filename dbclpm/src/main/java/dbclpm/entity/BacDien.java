package dbclpm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class BacDien {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private long price;

	private String name;

	private int startValue;

	private int endValue;

	private String description;

}
