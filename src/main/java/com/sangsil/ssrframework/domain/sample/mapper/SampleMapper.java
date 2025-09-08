package com.sangsil.ssrframework.domain.sample.mapper;

import com.sangsil.ssrframework.domain.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SampleMapper {
    List<UserDto.Response> findUserAll();
}
