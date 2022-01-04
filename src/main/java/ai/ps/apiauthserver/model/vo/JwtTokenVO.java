package ai.ps.apiauthserver.model.vo;

import lombok.*;

@Data
@Builder
public class JwtTokenVO {
    private String serviceKey;

    public static JwtTokenVO ofToken(String uuid, String token){
        return JwtTokenVO.builder().serviceKey(uuid)
                .build();
    }
}


