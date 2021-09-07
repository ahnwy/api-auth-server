package ai.ps.apiauthserver.model.dto;

import lombok.Data;

@Data
public class AuthUserRoleMapDTO {
    private int authUserRoleId;
    private String username;
    private int roleId;

}
