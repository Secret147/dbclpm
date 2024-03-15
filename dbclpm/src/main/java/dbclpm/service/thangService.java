package dbclpm.service;

import java.util.List;

import dbclpm.entity.thang;

public interface thangService {
    List<thang> getThangByNam(Long nam_id);

}
