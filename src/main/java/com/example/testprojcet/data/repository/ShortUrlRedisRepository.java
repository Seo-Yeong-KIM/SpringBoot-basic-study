package com.example.testprojcet.data.repository;

import com.example.testprojcet.data.dto.ShortUrlResponseDto;
import org.springframework.data.repository.CrudRepository;

// Redis 리포지토리
public interface ShortUrlRedisRepository extends CrudRepository<ShortUrlResponseDto, String> {

}