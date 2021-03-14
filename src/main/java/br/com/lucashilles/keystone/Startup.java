package br.com.lucashilles.keystone;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import io.quarkus.runtime.StartupEvent;


@Singleton
public class Startup {
    @Transactional
    public void loadUsers(@Observes StartupEvent evt) {
        // reset and load all test users
        UserModel.deleteAll();
        UserModel.add("admin@email.com", "admin", "admin");
        UserModel.add("user@baidu.cn", "user", "user");
    }
}