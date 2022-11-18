package com.example.testprojcet.data.entity.listener;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import com.example.testprojcet.data.entity.Listener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 리스너 어노테이션이 요청되는 시점
public class CustomListener {

    private final Logger LOGGER = LoggerFactory.getLogger(CustomListener.class);

    @PostLoad // 데이터가 select 된 후 요청
    public void postLoad(Listener entity) {
        LOGGER.info("[postLoad] called!!");
    }

    @PrePersist // 데이터가 save 되기 전 요청
    public void prePersist(Listener entity) {
        LOGGER.info("[prePersist] called!!");
    }

    @PostPersist // 데이터가 save 된 후 요청
    public void postPersist(Listener entity) {
        LOGGER.info("[postPersist] called!!");
    }

    @PreUpdate // 데이터가 update 되기 전 요청
    public void preUpdate(Listener entity) {
        LOGGER.info("[preUpdate] called!!");
    }

    @PostUpdate // 데이터가 update 된 후 요청
    public void postUpdate(Listener entity) {
        LOGGER.info("[postUpdate] called!!");
    }

    @PreRemove // 데이터가 delete 되기 전 요청
    public void preRemove(Listener entity) {
        LOGGER.info("[preRemove] called!!");
    }

    @PostRemove // 데이터가 delete 된 후 요청
    public void postRemove(Listener entity) {
        LOGGER.info("[postRemove] called!!");
    }
}