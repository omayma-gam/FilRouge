package com.Application.FilRouge.Repository;

import com.Application.FilRouge.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
