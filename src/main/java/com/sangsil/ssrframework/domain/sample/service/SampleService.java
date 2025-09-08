package com.sangsil.ssrframework.domain.sample.service;

import com.sangsil.ssrframework.domain.sample.mapper.SampleMapper;
import com.sangsil.ssrframework.domain.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SampleService {

    private final SampleMapper sampleMapper;

    public List<UserDto.Response> findUserAll() {
        return sampleMapper.findUserAll();
    }

}
