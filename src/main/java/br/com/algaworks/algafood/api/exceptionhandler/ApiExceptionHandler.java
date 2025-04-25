package br.com.algaworks.algafood.api.exceptionhandler;

import br.com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import br.com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.algaworks.algafood.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException e, HttpHeaders headers, HttpStatus status,
                                                        WebRequest request) {

        if (e instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) e, headers, status, request);
        }

        return super.handleTypeMismatch(e, headers, status, request);
    }


    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        ProblemType pt = ProblemType.DADOS_INVALIDOS;

        String detail = String.format("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente."
        );

        BindingResult bindingResult = e.getBindingResult();

        List<Field> problemFields = bindingResult.getFieldErrors().stream()
                .map(fieldError -> Field.builder()
                        .name(fieldError.getField())
                        .userMessage(fieldError.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());

        Problem problem = createProblemBuilder(status, pt, detail)
                .userMessage(detail)
                .fields(problemFields)
                .build();

        return handleExceptionInternal(e, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e,
                                                                    HttpHeaders headers, HttpStatus status, WebRequest request) {

        ProblemType pt = ProblemType.PARAMETRO_INVALIDO;

        String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', que é de um tipo inválido. "
                        + "Corrija e informe um valor compatível com o tipo %s.", e.getName(), e.getValue(),
                e.getRequiredType().getSimpleName());

        Problem problem = createProblemBuilder(status, pt, detail)
                .build();

        return handleExceptionInternal(e, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        Throwable rootCause = ExceptionUtils.getRootCause(e);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBinding((PropertyBindingException) rootCause, headers, status, request);
        }

        ProblemType pt = ProblemType.MENSAGEM_INCOMPREENSIVEL;

        String detail = "O corpo da requisição está inválido. Verifique o erro de sintaxe";

        Problem problem = createProblemBuilder(status, pt, detail)
                .build();

        return handleExceptionInternal(e, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException e,
                                                       HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = joinPath(e.getPath());

        ProblemType pt = ProblemType.MENSAGEM_INCOMPREENSIVEL;

        String detail = String.format("A propriedade '%s' recebeu o valor '%s', que é de um tipo inválido. "
                        + "Corrija e informe um valor compatível com o tipo %s.", path,
                e.getValue(), e.getTargetType().getSimpleName());

        Problem problem = createProblemBuilder(status, pt, detail)
                .build();

        return handleExceptionInternal(e, problem, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException e,
                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = joinPath(e.getPath());

        ProblemType pt = ProblemType.MENSAGEM_INCOMPREENSIVEL;

        String detail = String.format("A propriedade '%s' não é válida. "
                + "Remova essa propriedade e tente novamente.", path);

        Problem problem = createProblemBuilder(status, pt, detail)
                .build();

        return handleExceptionInternal(e, problem, headers, status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e,
                                                                  WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        ProblemType pt = ProblemType.RECURSO_NAO_ECONTRADO;

        String detail = e.getMessage();

        Problem problem = createProblemBuilder(status, pt, detail)
                .build();

        return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;

        ProblemType pt = ProblemType.ENTIDADE_EM_USO;

        String detail = e.getMessage();

        Problem problem = createProblemBuilder(status, pt, detail)
                .build();

        return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);

    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(NegocioException e, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        ProblemType pt = ProblemType.ERRO_NEGOCIO;

        String detail = e.getMessage();

        Problem problem = createProblemBuilder(status, pt, detail)
                .build();

        return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {

        if (body == null) {
            body = Problem.builder()
                    .title(status.getReasonPhrase())
                    .build();

        } else if (body instanceof String) {
            body = Problem.builder()
                    .title((String) body)
                    .status(status.value())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {

        return Problem.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail);
    }

    private String joinPath(List<JsonMappingException.Reference> references) {
        return references.stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));
    }

}
