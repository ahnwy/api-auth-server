package ai.ps.apiauthserver.model.vo;

import ai.ps.apiauthserver.model.dto.TokenManagementDTO;
import lombok.*;

@Data
@Builder
public class JwtTokenVO {
    private String uuid;
    private String token;

    public static JwtTokenVO ofToken(String uuid, String token){
        return JwtTokenVO.builder().token(token)
                .uuid(uuid)
                .build();
    }
}


