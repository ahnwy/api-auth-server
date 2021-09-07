package ai.ps.apiauthserver.model.vo;

import lombok.Data;

@Data
public class AuthVO {
    private String userId;
    private String username;
    private String password;

    private int creatorId;
    private int updaterId;
    private String createdDtm;
    private String updatedDtm;
}
