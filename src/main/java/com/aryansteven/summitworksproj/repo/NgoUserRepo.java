package com.aryansteven.summitworksproj.repo;

import java.util.Optional;

import com.aryansteven.summitworksproj.model.NgoUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NgoUserRepo extends JpaRepository<NgoUser, Integer> {

    Optional<NgoUser> findByEmail(String email);

}
