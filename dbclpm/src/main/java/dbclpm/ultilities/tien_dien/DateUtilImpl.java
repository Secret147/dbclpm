package dbclpm.ultilities.tien_dien;

import org.springframework.stereotype.Component;

@Component
public class DateUtilImpl implements DateUtil{
    @Override
    public String setDueDate(int day, int month, int year) {
        if(month == 12){
            month = 1;
            year++;
        }else month++;
        return day+"/"+month+"/"+year;
    }
}
