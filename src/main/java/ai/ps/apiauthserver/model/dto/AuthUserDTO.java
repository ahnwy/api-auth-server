package ai.ps.apiauthserver.model.dto;

import lombok.Data;

@Data
public class AuthUserDTO {
    private int userId;
    private String username;
    private String password;

    private int creatorId;
    private int updaterId;
    private String createdDtm;
    private String updatedDtm;
}
