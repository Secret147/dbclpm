package dbclpm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDetailDTO {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
