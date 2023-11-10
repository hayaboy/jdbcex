package org.zerock.jdbcex.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.jdbcex.dto.TodoDTO;
import org.zerock.jdbcex.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "todoRegisterController", value = "/todo/register")
@Log4j2
public class TodoRegisterController  extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;
    private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/todo/register GET .......");


        // 글 등록시 로그인 한 상태에서 등록 가능하도록 세션을 가져옴
        HttpSession session = req.getSession();
        if(session.isNew()) { //기존에 JSESSIONID가 없는 새로운 사용자

            log.info("JSESSIONID 쿠키가 새로 만들어진 사용자(즉 처음 접속한 사람)이므로 로그인 화면으로 보내버림 ");
            resp.sendRedirect("/jdbcex/login");
            return;


        }





        req.getRequestDispatcher("/WEB-INF/todo/register.jsp").forward(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");

        TodoDTO todoDTO= TodoDTO.builder().title(req.getParameter("title"))
                .dueDate(LocalDate.parse(req.getParameter("dueDate"),DATEFORMATTER )).build();

        log.info("/todo/register POST...");
        log.info("해야할 일 등록 후 전송 객체  :" + todoDTO);

        try {
            todoService.register(todoDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //PRG 패턴
        resp.sendRedirect("/jdbcex/todo/list");
    }



}
