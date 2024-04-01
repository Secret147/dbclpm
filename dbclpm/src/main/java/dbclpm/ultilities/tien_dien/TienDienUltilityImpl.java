package dbclpm.ultilities.tien_dien;

import java.util.Comparator;
import java.util.List;

import dbclpm.entity.BacDien;
import dbclpm.repository.BacDienRepo;

public class TienDienUltilityImpl implements TienDienUltility {
	
	private final BacDienRepo bacDienRepo;
	
	public TienDienUltilityImpl(BacDienRepo bacDienRepo) {
		this.bacDienRepo = bacDienRepo;
	}
	

	@Override
	public long tinhTienDien(long oldValue, long newValue) {
		// Lấy thông tin tất cả bậc điện hiện tại
		List<BacDien> dsBacDien = bacDienRepo.findAll();
		if (dsBacDien.isEmpty()) {
			return -1;
		}

		// Sắp xếp theo thứ tự
		dsBacDien.sort(new Comparator<BacDien>() {
			@Override
			public int compare(BacDien o1, BacDien o2) {
				return (int) (o1.getStartValue() - o2.getStartValue());
			}
		});

		// Tính tiền điện
		long tienDien = 0;
		long chenhLech = newValue - oldValue;
		int i = 0;
		while (i < dsBacDien.size() && chenhLech > 0) {
			BacDien bacDien = dsBacDien.get(i);
			long temp = bacDien.getEndValue() - bacDien.getStartValue() + 1;
			if (chenhLech > temp) {
				tienDien += temp * bacDien.getPrice();
			} else {
				tienDien += chenhLech * bacDien.getPrice();
				break;
			}
			chenhLech -= temp;
			i++;
		}

		return tienDien;
	}

}
