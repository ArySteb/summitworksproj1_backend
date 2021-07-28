package com.aryansteven.summitworksproj.repo;

import com.aryansteven.summitworksproj.model.NgoEvent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NgoEventRepo extends JpaRepository<NgoEvent, Integer> {

}
