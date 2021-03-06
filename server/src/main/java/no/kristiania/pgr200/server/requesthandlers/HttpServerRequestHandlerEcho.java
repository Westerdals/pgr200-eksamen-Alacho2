package no.kristiania.pgr200.server.requesthandlers;

import no.kristiania.pgr200.server.HttpServerRequest;
import no.kristiania.pgr200.server.HttpServerResponse;

public class HttpServerRequestHandlerEcho implements HttpServerRequestHandler {

    @Override
    public boolean handleRequest(HttpServerRequest request, HttpServerResponse response) {
        if (!request.getPath().equals("echo"))
            return false;

        if(request.getParameter("status") != null) {
            response.setStatusCode(Integer.parseInt((request.getParameter("status"))));
        } else{
            response.setStatusCode(200);
        }

        if (request.getParameter("body") != null) {
            response.setBody(request.getParameter("body"));
        }

        if (request.getParameter("Location") != null) {
            response.getAdditionalHeaders().put("Location", request.getParameter("Location"));
        }

        return true;
    }
}
