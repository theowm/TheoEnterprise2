package com.example.theoenterprise2.services;

import com.example.theoenterprise2.entities.Member;
import com.example.theoenterprise2.exceptions.ResourceNotFoundException;
import com.example.theoenterprise2.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService implements MemberServiceInterface {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<Member> fetchAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member addNewMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(int id, Member member) {
        memberRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Member", "id", id));
        return memberRepository.save(member);
    }

    @Override
    public Member fetchMemberById(int id) {

        return memberRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Member", "id", id));
    }

    @Override
    public void deleteMember(int id) {
        memberRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Member", "id", id));
        memberRepository.deleteById(id);
    }
}
