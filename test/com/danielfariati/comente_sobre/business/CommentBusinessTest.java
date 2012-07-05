package com.danielfariati.comente_sobre.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.com.caelum.vraptor.util.test.JSR303MockValidator;
import br.com.caelum.vraptor.validator.ValidationException;
import br.com.caelum.vraptor.validator.ValidationMessage;

import com.danielfariati.comente_sobre.model.Comment;
import com.danielfariati.comente_sobre.repository.CommentRepository;

public class CommentBusinessTest {

	@Mock private EntityManager manager;
	private JSR303MockValidator validator;

	private CommentRepository repository;

	@Before
	public void setup() throws SQLException {
		validator = Mockito.spy(new JSR303MockValidator());

		repository = new CommentBusiness(manager, validator);
	}

	@Test
	public void shouldThrowExceptionIfEmailNull() {
		Comment comment = new Comment();
		comment.setEmail(null);
		comment.setMessage("mensagem");

		try {
			repository.save(comment);
			fail("exception esperada mas nao lancada");
		} catch (ValidationException e) {
			ValidationMessage expected = new ValidationMessage("O campo e-mail deve ser preenchido!", "comment.email");

			assertEquals("deveria ter lançado apenas 1 erro", 1, e.getErrors().size());
			assertEquals("mensagens de erro deveriam ser iguais", expected.getMessage(), e.getErrors().get(0).getMessage());
			assertEquals("categorias de erro deveriam ser iguais", expected.getCategory(), e.getErrors().get(0).getCategory());
		}
	}

	@Test
	public void shouldThrowExceptionIfEmailEmpty() {
		Comment comment = new Comment();
		comment.setEmail("");
		comment.setMessage("mensagem");

		try {
			repository.save(comment);
			fail("exception esperada mas nao lancada");
		} catch (ValidationException e) {
			ValidationMessage expected = new ValidationMessage("O campo e-mail deve ser preenchido!", "comment.email");

			assertEquals("deveria ter lançado apenas 1 erro", 1, e.getErrors().size());
			assertEquals("mensagens de erro deveriam ser iguais", expected.getMessage(), e.getErrors().get(0).getMessage());
			assertEquals("categorias de erro deveriam ser iguais", expected.getCategory(), e.getErrors().get(0).getCategory());
		}
	}

	@Test
	public void shouldThrowExceptionIfMessageNull() {
		Comment comment = new Comment();
		comment.setEmail("email");
		comment.setMessage(null);

		try {
			repository.save(comment);
			fail("exception esperada mas nao lancada");
		} catch (ValidationException e) {
			ValidationMessage expected = new ValidationMessage("O campo mensagem deve ser preenchido!", "comment.message");

			assertEquals("deveria ter lançado apenas 1 erro", 1, e.getErrors().size());
			assertEquals("mensagens de erro deveriam ser iguais", expected.getMessage(), e.getErrors().get(0).getMessage());
			assertEquals("categorias de erro deveriam ser iguais", expected.getCategory(), e.getErrors().get(0).getCategory());
		}
	}

	@Test
	public void shouldThrowExceptionIfMessageEmpty() {
		Comment comment = new Comment();
		comment.setEmail("email");
		comment.setMessage("");

		try {
			repository.save(comment);
			fail("exception esperada mas nao lancada");
		} catch (ValidationException e) {
			ValidationMessage expected = new ValidationMessage("O campo mensagem deve ser preenchido!", "comment.message");

			assertEquals("deveria ter lançado apenas 1 erro", 1, e.getErrors().size());
			assertEquals("mensagens de erro deveriam ser iguais", expected.getMessage(), e.getErrors().get(0).getMessage());
			assertEquals("categorias de erro deveriam ser iguais", expected.getCategory(), e.getErrors().get(0).getCategory());
		}
	}

}
