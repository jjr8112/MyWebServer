package com.jjr8112.webserver.core.request.dispatcher;

import com.jjr8112.webserver.core.exception.base.ServletException;
import com.jjr8112.webserver.core.request.Request;
import com.jjr8112.webserver.core.response.Response;

import java.io.IOException;

public interface RequestDispatcher {

    void forward(Request request, Response response) throws ServletException, IOException;
}
