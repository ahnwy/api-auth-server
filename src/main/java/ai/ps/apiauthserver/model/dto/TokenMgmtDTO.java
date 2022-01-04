package ai.ps.apiauthserver.model.dto;

import ai.ps.apiauthserver.model.vo.UserVO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenMgmtDTO {
    private int userId;
    private int svcId;
    private String token;
    private String svcKey;

    public static TokenMgmtDTO ofBuild(UserVO userVO, String token, String uuid){
        return TokenMgmtDTO.builder().svcKey(uuid)
                .token(token)
                .userId(userVO.getUserId())
                .svcId(userVO.getServiceId())
                .build();
    }
}
