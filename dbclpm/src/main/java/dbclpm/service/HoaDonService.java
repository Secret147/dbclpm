package dbclpm.service;

import java.util.List;

import dbclpm.entity.HoaDon;

public interface HoaDonService {
	Long getTotal();
	List<HoaDon> getListHoaDon();

}
