package ai.ps.apiauthserver.model.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserVO {
    private int userId;
    private int serviceId;
}
