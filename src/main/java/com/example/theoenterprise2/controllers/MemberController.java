package com.example.theoenterprise2.controllers;

import com.example.theoenterprise2.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.theoenterprise2.entities.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/admin/members")
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(memberService.fetchAllMembers());
    }

    @PostMapping ("/admin/addmember")
    public ResponseEntity<Member> addNewMember(@RequestBody Member member) {
        return new ResponseEntity<>(memberService.addNewMember(member), HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/deletemember/{id}")
    public ResponseEntity<String> deleteMemberById(@PathVariable int id){
        memberService.deleteMember(id);
        return ResponseEntity.ok("Member with id " + id + " was deleted!");
    }

    @PutMapping("/admin/updatemember/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable int id, @RequestBody Member member){
        return ResponseEntity.ok(memberService.updateMember(id, member));
    }

    @GetMapping("/admin/member/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable int id){
        return ResponseEntity.ok(memberService.fetchMemberById(id));
    }

    @GetMapping("/admin/deletemember")
    public String showAllEmployeesPage(Model model){
        model.addAttribute("members", memberService.fetchAllMembers());
        return "handlemembers";
    }

    @GetMapping("/admin/showallmembers")
    public String deleteEmployeeById(@RequestParam int id){
        memberService.deleteMember(id);
        return "redirect:/admin/deletemember";
    }
}
