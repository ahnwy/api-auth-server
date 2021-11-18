package ai.ps.apiauthserver.model.dto;

import ai.ps.apiauthserver.model.vo.UserVO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenManagementDTO {
    private int userId;
    private String token;
    private String type;
    private String uuid;

    public static TokenManagementDTO ofSave(UserVO userVO, String token, String uuid){
        return TokenManagementDTO.builder().uuid(uuid)
                .token(token)
                .userId(userVO.getUserId())
                .type(userVO.getType())
                .build();
    }
}
