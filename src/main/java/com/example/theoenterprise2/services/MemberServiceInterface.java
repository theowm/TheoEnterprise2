package com.example.theoenterprise2.services;

import com.example.theoenterprise2.entities.Member;

import java.util.List;

public interface MemberServiceInterface {

    List<Member> fetchAllMembers();

    Member addNewMember(Member member);

    Member updateMember(int id, Member member);

    Member fetchMemberById(int id);

    void deleteMember(int id);

}
