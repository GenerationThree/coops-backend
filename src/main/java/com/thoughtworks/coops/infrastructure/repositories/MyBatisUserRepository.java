package com.thoughtworks.coops.infrastructure.repositories;

import com.thoughtworks.coops.domain.user.User;
import com.thoughtworks.coops.domain.user.UserRepository;
import com.thoughtworks.coops.domain.user.UserId;
import com.thoughtworks.coops.infrastructure.mybatis.mappers.UserMapper;

import javax.inject.Inject;
import java.util.Optional;

public class MyBatisUserRepository implements UserRepository {
    @Inject
    UserMapper mapper;

    @Override
    public com.thoughtworks.coops.domain.user.User save(com.thoughtworks.coops.domain.user.User user) {
        mapper.save(user);
        return mapper.ofId(user.getUserId().id());
    }

    @Override
    public Optional<com.thoughtworks.coops.domain.user.User> ofId(UserId id) {
        return Optional.ofNullable(mapper.ofId(id.id()));
    }

    @Override
    public User findUserByName(String userName) {
        return mapper.findByUserName(userName);
    }
}
