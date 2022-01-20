package com.quietjun.intellij.registerform.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quietjun.intellij.registerform.commons.action.Action;
import com.quietjun.intellij.registerform.commons.action.ActionForward;
import com.quietjun.intellij.registerform.member.study.NaverDAO;
import com.quietjun.intellij.registerform.member.study.NaverDTO;

public class MemberJoinAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //String member_id = request.getParameter("member-id");
        NaverDTO dto = new NaverDTO();
        dto.setMember_id(request.getParameter("member_id"));
        dto.setMember_pw(request.getParameter("member_pw"));
        dto.setMember_name(request.getParameter("member_name"));
        dto.setMember_gender(request.getParameter("member_gender"));
        dto.setMember_email(request.getParameter("member_email"));

        NaverDAO dao = new NaverDAO();
        boolean result = dao.joinMember(dto);

        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        if (result == false) {
            out.println("<script>alert('회원가입 실패!')</script>");
            return null;
        } else {
            ActionForward forward = new ActionForward();
            forward.setPath("memberLogin.me");
            forward.setRedirect(true);
            return forward;
        }
    }
}


