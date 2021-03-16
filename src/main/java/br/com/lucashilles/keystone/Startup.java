package br.com.lucashilles.keystone;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import br.com.lucashilles.keystone.models.Enterprise;
import br.com.lucashilles.keystone.models.User;
import io.quarkus.runtime.StartupEvent;

import java.util.List;


@Singleton
public class Startup {

    @Transactional
    public void loadUsers(@Observes StartupEvent evt) {
        // reset and load all test users
        User.deleteAll();
        Enterprise.deleteAll();

        User.add("admin@email.com", "admin", "admin");
        User user = User.add("user@baidu.cn", "user", "ENGINEER");

        Enterprise enterprise1 = new Enterprise();
        enterprise1.name = "Riacho1";
        enterprise1.users = List.of(user);
        enterprise1.persist();

        Enterprise enterprise2 = new Enterprise();
        enterprise2.name = "Riacho2";
        enterprise2.users = List.of(user);
        enterprise2.persist();

    }
}