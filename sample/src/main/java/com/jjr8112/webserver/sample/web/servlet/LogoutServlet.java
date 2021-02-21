package com.jjr8112.webserver.sample.web.servlet;

import com.jjr8112.webserver.core.exception.base.ServletException;
import com.jjr8112.webserver.core.request.Request;
import com.jjr8112.webserver.core.response.Response;
import com.jjr8112.webserver.core.servlet.impl.HttpServlet;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LogoutServlet extends HttpServlet {

    @Override
    public void doGet(Request request, Response response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/logout.html").forward(request,response);
    }

    @Override
    public void doPost(Request request, Response response) throws ServletException, IOException {
        request.getSession().removeAttribute("username");
        request.getSession().invalidate();
        response.sendRedirect("/login");
    }
}
