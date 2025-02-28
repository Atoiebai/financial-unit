package com.test.financialunit.user;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
 interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);
}
