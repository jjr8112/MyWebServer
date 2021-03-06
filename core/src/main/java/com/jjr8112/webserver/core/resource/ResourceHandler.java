package com.jjr8112.webserver.core.resource;

import com.jjr8112.webserver.core.constant.CharsetProperties;
import com.jjr8112.webserver.core.exception.RequestParseException;
import com.jjr8112.webserver.core.exception.ResourceNotFoundException;
import com.jjr8112.webserver.core.exception.base.ServletException;
import com.jjr8112.webserver.core.exception.handler.ExceptionHandler;
import com.jjr8112.webserver.core.network.wrapper.nio.NioSocketWrapper;
import com.jjr8112.webserver.core.request.Request;
import com.jjr8112.webserver.core.response.Response;
import com.jjr8112.webserver.core.template.TemplateResolver;
import com.jjr8112.webserver.core.util.IOUtil;
import com.jjr8112.webserver.core.util.MimeTypeUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 用于处理静态资源
 */
@Slf4j
public class ResourceHandler {
    private ExceptionHandler exceptionHandler;

    public ResourceHandler(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    public void handle(Request request, Response response, NioSocketWrapper socketWrapper) {
        String url = request.getUrl();
        try {
            if (ResourceHandler.class.getResource(url) == null) {
                log.info("找不到该资源:{}", url);
                throw new ResourceNotFoundException();
            }
            byte[] body = IOUtil.getBytesFromFile(url);
            if (url.endsWith(".html")) {
                body = TemplateResolver
                        .resolve(new String(body, CharsetProperties.UTF_8_CHARSET), request)
                        .getBytes(CharsetProperties.UTF_8_CHARSET);
            }
            response.setContentType(MimeTypeUtil.getTypes(url));
            response.setBody(body);
        } catch (IOException e) {
            exceptionHandler.handle(new RequestParseException(), response, socketWrapper);
        } catch (ServletException e) {
            exceptionHandler.handle(e, response, socketWrapper);
        }
    }
}
