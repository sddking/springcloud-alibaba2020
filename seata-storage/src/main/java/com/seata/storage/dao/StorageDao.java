package com.seata.storage.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StorageDao {
    void decrease(@Param("productId") Integer productId, @Param("count") Integer count);
}
