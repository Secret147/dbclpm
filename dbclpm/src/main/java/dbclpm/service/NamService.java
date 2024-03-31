package dbclpm.service;

import java.util.List;

import dbclpm.entity.Nam;
import dbclpm.entity.Thang;

public interface NamService {
    List<Thang> getListThangByNam(Long namid);
    List<Nam> getListNam();

}
