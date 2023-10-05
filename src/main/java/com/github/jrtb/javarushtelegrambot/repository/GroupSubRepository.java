package com.github.jrtb.javarushtelegrambot.repository;

import com.github.jrtb.javarushtelegrambot.repository.entity.GroupSub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupSubRepository extends JpaRepository<GroupSub, Integer> {
}
