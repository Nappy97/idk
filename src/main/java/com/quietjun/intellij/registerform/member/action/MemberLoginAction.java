package com.quietjun.intellij.registerform.member.action;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quietjun.intellij.registerform.commons.action.Action;
import com.quietjun.intellij.registerform.commons.action.ActionForward;
import com.quietjun.intellij.registerform.member.study.NaverDAO;
import com.quietjun.intellij.registerform.member.study.NaverDTO;

public class MemberLoginAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NaverDTO dto = new NaverDTO();
        dto.setMember_id(request.getParameter("member_id"));
        dto.setMember_pw(request.getParameter("member_pw"));
        NaverDAO dao = new NaverDAO();
        int result = dao.isMember(dto);
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        if (result == 0) {
            out.println("<script>");
            out.println("alert('비밀번호가 일치하지 않습니다!');");
            out.println("location.href = 'memberLogin.me';");
            out.println("</script>");
            return null;
        } else if (result == -1) {
            out.println("<script>");
            out.println("alert('아이디가 존재하지 않습니다!');");
            out.println("location.href = 'memberLogin.me';");
            out.println("</script>");
            return null;
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("id", dto.getMember_id());
            ActionForward forward = new ActionForward();
            forward.setPath("boardList.bo");
            forward.setRedirect(false);
            return forward;
        }
    }
}