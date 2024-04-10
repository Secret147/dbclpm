package dbclpm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BacDien {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long price;

	private String name;

	private Integer startValue;

	private Integer endValue;

	private String description;

//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public long getPrice() {
//		return price;
//	}
//
//	public void setPrice(long price) {
//		this.price = price;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public int getStartValue() {
//		return startValue;
//	}
//
//	public void setStartValue(int startValue) {
//		this.startValue = startValue;
//	}
//
//	public int getEndValue() {
//		return endValue;
//	}
//
//	public void setEndValue(int endValue) {
//		this.endValue = endValue;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public BacDien(long id, long price, String name, int startValue, int endValue, String description) {
//		this.id = id;
//		this.price = price;
//		this.name = name;
//		this.startValue = startValue;
//		this.endValue = endValue;
//		this.description = description;
//	}
//
//	public BacDien() {
//	}
}
