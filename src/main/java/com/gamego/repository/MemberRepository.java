package com.gamego.repository;

import com.gamego.entity.Member;
import com.querydsl.core.annotations.QueryTransient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);

    Member findByNickname(String nickname);

//    @Query("select m from Member m where m.nickname = :nickname")
//    Member findMemberInfo(@Param("nickname") String nickname);
//
//    @Query("select m.id from Member m where m.nickname = :nickanme")
//    Member findMemberId(@Param("nickname") String nickname);

}
