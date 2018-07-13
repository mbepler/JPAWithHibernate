package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteBuscaConta {
	
	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Conta conta = em.find(Conta.class, 1);
		
		// o find retorna uma conta em estado "Managed" ou seja ao alterar a conta, automaticamente é alterado no banco
		conta.setTitular("João");
		
		System.out.println(conta.getTitular());
		em.getTransaction().commit();
		em.close();
		// Ao encerrar a transação, o objeto para de ser um objeto "managed" 
		// E se torna um Detached, ou seja um objeto que já foi gerenciado pela JPA, Ou seja a uma representação no banco
		// porem a entidade não esta sendo sincronizada automaticamente pela JPA
		
		EntityManager em2 = new JPAUtil().getEntityManager();
		em2.getTransaction().begin();
		
		conta.setTitular("Leonardo");
		
		// o metodo persist não funciona para uma conta "detached", para sair de detached para managed
		// é preciso usar o metodo merge
		em2.merge(conta);
		
		em2.getTransaction().commit();
		em2.close();
	}

}
