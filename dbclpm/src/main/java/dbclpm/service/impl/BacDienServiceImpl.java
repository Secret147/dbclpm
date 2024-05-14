package dbclpm.service.impl;

import dbclpm.entity.BacDien;
import dbclpm.repository.BacDienRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dbclpm.service.BacDienService;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class BacDienServiceImpl implements BacDienService {
	@Autowired
	private BacDienRepo bacDienRepo;
	@Override
	public List<BacDien> getAllBacDien() {
		return bacDienRepo.findAll();
	}

	public BacDienServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	

    @Override
    public BacDien findBacDienById(long id) {
        return bacDienRepo.findById(id).orElse(null);
    }

    @Override
    public BacDien findPreviousBacDien(Integer startValue) {
        return bacDienRepo.findTop1ByEndValueLessThanOrderByEndValueDesc(startValue);
    }

    @Override
    public BacDien findNextBacDien(Integer endValue) {
        return bacDienRepo.findTop1ByStartValueGreaterThanOrderByStartValueAsc(endValue);
    }

    @Override
    public boolean validateAndUpdateBacDien(BacDien currentBacDien, BacDien previousBacDien, BacDien nextBacDien, BacDien request) {
  
    	 if (currentBacDien == null || request == null) {
    	        return false; // Trả về false nếu currentBacDien hoặc request là null
    	    }

    	    if (previousBacDien == null) {
    	        // Nếu previousBacDien là null, kiểm tra các điều kiện liên quan
    	        if (request.getPrice() < nextBacDien.getPrice() && request.getStartValue() == 0 &&
    	            request.getEndValue() == nextBacDien.getStartValue() - 1 &&
    	            request.getStartValue() < request.getEndValue()) {
    	            currentBacDien.setName(request.getName());
    	            currentBacDien.setStartValue(request.getStartValue());
    	            currentBacDien.setEndValue(request.getEndValue());
    	            currentBacDien.setPrice(request.getPrice());
    	            currentBacDien.setDescription(request.getDescription() != null ? request.getDescription() : "");
    	            bacDienRepo.save(currentBacDien); // Lưu thông tin vào database
    	            return true;
    	        } else {
    	            return false; // Trả về false nếu điều kiện không hợp lệ
    	        }
    	    }

    	    if (nextBacDien == null) {
    	        // Nếu nextBacDien là null, kiểm tra các điều kiện liên quan
    	        if (request.getPrice() > previousBacDien.getPrice() &&
    	            request.getStartValue() == previousBacDien.getEndValue() + 1 &&
    	            request.getStartValue() < request.getEndValue()) {
    	            currentBacDien.setName(request.getName());
    	            currentBacDien.setStartValue(request.getStartValue());
    	            currentBacDien.setEndValue(request.getEndValue());
    	            currentBacDien.setPrice(request.getPrice());
    	            currentBacDien.setDescription(request.getDescription() != null ? request.getDescription() : "");
    	            bacDienRepo.save(currentBacDien); // Lưu thông tin vào database
    	            return true;
    	        } else {
    	            return false; // Trả về false nếu điều kiện không hợp lệ
    	        }
    	    }

    	    // Nếu cả previousBacDien và nextBacDien không phải null, kiểm tra các điều kiện liên quan
    	    if (request.getPrice() < nextBacDien.getPrice() &&
    	        request.getStartValue() == previousBacDien.getEndValue() + 1 &&
    	        request.getEndValue() == nextBacDien.getStartValue() - 1 &&
    	        request.getStartValue() < request.getEndValue() &&
    	        request.getPrice() > previousBacDien.getPrice()) {
    	        currentBacDien.setName(request.getName());
    	        currentBacDien.setStartValue(request.getStartValue());
    	        currentBacDien.setEndValue(request.getEndValue());
    	        currentBacDien.setPrice(request.getPrice());
    	        currentBacDien.setDescription(request.getDescription() != null ? request.getDescription() : "");
    	        bacDienRepo.save(currentBacDien); // Lưu thông tin vào database
    	        return true;
    	    } else {
    	        return false; // Trả về false nếu điều kiện không hợp lệ
    	    }
    }
    @Override
    public BacDien getMaxBacDien() {
        return bacDienRepo.findFirstByOrderByIdDesc();
    }
    @Override
    @Transactional
    public boolean addNewBacDien(BacDien bacdien) {
        try {
            Integer startValue = bacdien.getStartValue();
            Integer endValue = bacdien.getEndValue();
            
            if (startValue != null && startValue > -1 && endValue != null && startValue < endValue) {
                bacDienRepo.addNewBacDien(bacdien.getPrice(), bacdien.getName(), startValue, endValue, bacdien.getDescription());
                return true; // Trả về true nếu thêm bậc mới thành công
            } else {
                return false; // Trả về false nếu có lỗi khi thêm bậc mới
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi khi thêm bậc mới
        }
    }
    @Override
    public boolean deleteBacDien(long idToDelete) {
        try {
            long maxId = bacDienRepo.findMaxId();

            if (idToDelete == maxId) {
                bacDienRepo.deleteBacDienById(idToDelete);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

}
