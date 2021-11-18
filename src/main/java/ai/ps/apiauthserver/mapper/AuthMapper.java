package ai.ps.apiauthserver.mapper;


import ai.ps.apiauthserver.model.dto.AuthUserDTO;
import ai.ps.apiauthserver.model.dto.TokenManagementDTO;
import ai.ps.apiauthserver.model.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface AuthMapper {
    public Optional<TokenManagementDTO> selectTokenManagement(UserVO userVO);

    public int saveToken(TokenManagementDTO tokenManagementDTO);

    public String getUuid(int userId);

    public AuthUserDTO getAccount(int userId);

    public List<String> getAuthorities(int userId);
}
