package br.com.algaworks.algafood.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.algaworks.algafood.exception.EntidadeEmUsoException;
import br.com.algaworks.algafood.exception.EntidadeNaoEncontradaException;
import br.com.algaworks.algafood.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e) {

		Problema problema = Problema.builder().dataHora(LocalDateTime.now()).mensagem(e.getMessage()).build();

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problema);
	}

	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException e) {

		Problema problema = Problema.builder().dataHora(LocalDateTime.now()).mensagem(e.getMessage()).build();

		return ResponseEntity.status(HttpStatus.CONFLICT).body(problema);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> tratarNegocioException(NegocioException e) {

		Problema problema = Problema.builder().dataHora(LocalDateTime.now()).mensagem(e.getMessage()).build();

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problema);
	}

}