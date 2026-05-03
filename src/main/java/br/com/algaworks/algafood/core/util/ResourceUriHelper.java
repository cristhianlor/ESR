package br.com.algaworks.algafood.core.util;

import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Objects;

public class ResourceUriHelper {

    public static void addUriInResponseHeader(Object reosourceId) {
        // Implementação para adicionar o URI no cabeçalho da resposta
        // Isso pode ser feito usando o HttpServletResponse, por exemplo:
        // response.setHeader("Location", uri);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(reosourceId).toUri();

        HttpServletResponse response = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getResponse();

        assert response != null;
        response.setHeader(HttpHeaders.LOCATION, uri.toString());
    }
}
