package com.mm.libraryrestapi.repositories;

import com.mm.libraryrestapi.entity.UserReadHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReadHistoryRepository extends JpaRepository<UserReadHistory, Long> {

}
