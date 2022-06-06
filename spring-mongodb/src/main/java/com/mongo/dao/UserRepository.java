package com.mongo.dao;

import com.mongo.pojo.UserPO;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author yh
 * @description
 * @date 2022/6/5
 */
public interface UserRepository extends MongoRepository<UserPO, Long> {

    UserPO findByUsername(String username);
}
