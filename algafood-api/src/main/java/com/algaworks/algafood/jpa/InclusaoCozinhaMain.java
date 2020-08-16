package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;


public class InclusaoCozinhaMain {

	public static void main(String[] args) {

		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);

		Cozinha cosinha1 = new Cozinha();
		cosinha1.setNome("Brasileira");

		Cozinha cosinha2 = new Cozinha();
		cosinha2.setNome("Japonesa");

		cosinha1 = cozinhaRepository.salvar(cosinha1);
		cosinha2 = cozinhaRepository.salvar(cosinha2);

		System.out.printf("%d - %s\n", cosinha1.getId(), cosinha1.getNome());
		System.out.printf("%d - %s\n", cosinha2.getId(), cosinha2.getNome());

	}

}
