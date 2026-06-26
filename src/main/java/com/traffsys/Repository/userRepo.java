package com.traffsys.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.traffsys.entity.user;

public interface userRepo extends JpaRepository<user, Long> {

    //user findByUsername(String username);
    user findByUsername(String username);
}
