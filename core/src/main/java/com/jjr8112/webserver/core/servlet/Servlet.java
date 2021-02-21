package com.jjr8112.webserver.core.servlet;

import com.jjr8112.webserver.core.exception.base.ServletException;
import com.jjr8112.webserver.core.request.Request;
import com.jjr8112.webserver.core.response.Response;

import java.io.IOException;

public interface Servlet {
    void init();

    void destroy();

    void service(Request request, Response response) throws ServletException, IOException;
}

