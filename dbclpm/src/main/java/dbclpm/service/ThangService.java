package dbclpm.service;

import java.util.List;

import dbclpm.entity.Thang;

public interface ThangService {
    List<Thang> getThangByNam(Long nam_id);

}
