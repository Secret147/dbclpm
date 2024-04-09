package dbclpm.ultilities.tien_dien;

import org.springframework.stereotype.Component;

@Component
public interface TienDienUltility {
	public long tinhTienDien(long oldValue, long newValue);
}
