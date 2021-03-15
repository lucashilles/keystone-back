package br.com.lucashilles.keystone;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import br.com.lucashilles.keystone.models.User;
import io.quarkus.runtime.StartupEvent;


@Singleton
public class Startup {
    @Transactional
    public void loadUsers(@Observes StartupEvent evt) {
        // reset and load all test users
//        User.deleteAll();
//        User.add("admin@email.com", "admin", "admin");
//        User.add("user@baidu.cn", "user", "user");
    }
}