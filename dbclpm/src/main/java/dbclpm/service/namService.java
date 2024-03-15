package dbclpm.service;

import java.util.List;

import dbclpm.entity.nam;
import dbclpm.entity.thang;

public interface namService {
    List<thang> getListThangByNam(Long namid);
    List<nam> getListNam();

}
