package dbclpm.ultilities.tien_dien;

import org.springframework.stereotype.Service;

@Service
public interface TienDienUltility {
	public long tinhTienDien(long oldValue, long newValue);
}
