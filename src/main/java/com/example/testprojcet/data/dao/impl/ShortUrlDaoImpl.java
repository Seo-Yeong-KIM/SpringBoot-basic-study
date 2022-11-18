package com.example.testprojcet.data.dao.impl;

import com.example.testprojcet.data.dao.ShortUrlDAO;
import com.example.testprojcet.data.entity.ShortUrl;
import com.example.testprojcet.data.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlDaoImpl implements ShortUrlDAO {

    private final ShortUrlRepository shortUrlRepository;

    @Autowired
    public ShortUrlDaoImpl(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    @Override
    public ShortUrl saveShortUrl(ShortUrl shortUrl) {
        ShortUrl foundShortUrl = shortUrlRepository.save(shortUrl);
        return foundShortUrl;
    }

    @Override
    public ShortUrl getShortUrl(String originalUrl) {
        ShortUrl foundShortUrl = shortUrlRepository.findByOrgUrl(originalUrl);
        return foundShortUrl;
    }

    @Override
    public ShortUrl getOriginalUrl(String shortUrl) {
        ShortUrl foundShortUrl = shortUrlRepository.findByUrl(shortUrl);
        return foundShortUrl;
    }

    // 네이버 API를 사용하면 단축 uri값을 임의로 변경할 수 없기 때문에 update 기능은 보류
    @Override
    public ShortUrl updateShortUrl(ShortUrl newShortUrl) {
        ShortUrl foundShortUrl = shortUrlRepository.findByOrgUrl(newShortUrl.getOrgUrl());

        foundShortUrl.setUrl(newShortUrl.getUrl());

        ShortUrl savedShortUrl = shortUrlRepository.save(foundShortUrl);

        return savedShortUrl;
    }

    @Override
    public void deleteByShortUrl(String shortUrl) {
        ShortUrl foundShortUrl = shortUrlRepository.findByUrl(shortUrl);
        shortUrlRepository.delete(foundShortUrl);
    }

    @Override
    public void deleteByOriginalUrl(String originalUrl) {
        ShortUrl foundShortUrl = shortUrlRepository.findByOrgUrl(originalUrl);
        shortUrlRepository.delete(foundShortUrl);
    }
}