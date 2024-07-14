package com.example.springbootthymeleafpostgresql.controller;

import com.example.springbootthymeleafpostgresql.model.Member;
import com.example.springbootthymeleafpostgresql.repository.MemberRepository;
import com.example.springbootthymeleafpostgresql.service.MemberExcelExporter;
import com.example.springbootthymeleafpostgresql.service.MemberPDFExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/admin/members")
    public String listMembers(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "admin/members";
    }

    @GetMapping("/admin/members/export/excel")
    public void exportMembersToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=members.xlsx";
        response.setHeader(headerKey, headerValue);

        List<Member> members = memberRepository.findAll();

        MemberExcelExporter excelExporter = new MemberExcelExporter(members);

        excelExporter.export(response);
    }

    @GetMapping("/admin/members/export/pdf")
    public void exportMembersToPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=members.pdf";
        response.setHeader(headerKey, headerValue);

        List<Member> members = memberRepository.findAll();

        MemberPDFExporter pdfExporter = new MemberPDFExporter(members);

        pdfExporter.export(response);
    }
}
