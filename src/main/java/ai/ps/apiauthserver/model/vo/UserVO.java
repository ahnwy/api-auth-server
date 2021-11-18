package ai.ps.apiauthserver.model.vo;

import lombok.Data;

@Data
public class UserVO {
    private int userId;
    private String username;
    private String password;
    private String type;
    private String token;

    private int creatorId;
    private int updaterId;
    private String createdDtm;
    private String updatedDtm;
}
