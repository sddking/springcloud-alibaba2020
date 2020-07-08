package com.seata.storage.service;

public interface StorageService {
    public void decrease(Long productId,Integer count);
}
