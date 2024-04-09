package dbclpm.ultilities.tien_dien;

import org.springframework.stereotype.Component;

@Component
public class ObjectUtilImpl implements ObjectUtil{
    @Override
    public boolean checkObject(Object obj) {
        return (obj != null && !obj.toString().isEmpty());
    }
}
