package ai.ps.apiauthserver.mapper;


import ai.ps.apiauthserver.model.dto.TokenMgmtDTO;
import ai.ps.apiauthserver.model.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Mapper
public interface AuthMapper {
    public Optional<TokenMgmtDTO> selectTokenMgmt(UserVO userVO);

    public int saveToken(TokenMgmtDTO tokenMgmtDTO);

    public int createUserMetering(UserVO userVO);

    public String getServiceKey(int userId);

    public int removeToken(UserVO userVO);
}
