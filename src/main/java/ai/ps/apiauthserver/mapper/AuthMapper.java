package ai.ps.apiauthserver.mapper;


import ai.ps.apiauthserver.model.dto.AuthUserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AuthMapper {
    public AuthUserDTO getAccount(String username);

    public List<String> getAuthorities(int userId);
}
