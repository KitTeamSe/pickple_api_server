package com.se.pickple_api_server.v1.recruitment.infra.repository;

import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.recruitment.domain.entity.RecruitmentBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruitmentBoardJpaRepository extends JpaRepository<RecruitmentBoard, Long> {
    List<RecruitmentBoard> findAllByAccount(Account account);
}