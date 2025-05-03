package com.example.theoenterprise2.services;

import com.example.theoenterprise2.entities.Address;
import com.example.theoenterprise2.entities.Member;
import com.example.theoenterprise2.exceptions.ResourceNotFoundException;
import com.example.theoenterprise2.repositories.AddressRepository;
import com.example.theoenterprise2.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService implements MemberServiceInterface {

    private MemberRepository memberRepository;

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    private AddressRepository addressRepository;
    @Autowired
    public void setAddressRepository(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Member> fetchAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member addNewMember(Member member) {
        if (member.getAddress() != null && member.getAddress().getId() != 0) {
            Address existingAddress = addressRepository.findById(member.getAddress().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Address", "id", member.getAddress().getId()));
            member.setAddress(existingAddress);
        } else if (member.getAddress() != null) {
            Address savedAddress = addressRepository.save(member.getAddress());
            member.setAddress(savedAddress);
        }
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
