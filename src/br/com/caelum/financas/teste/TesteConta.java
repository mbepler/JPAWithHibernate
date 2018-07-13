package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteConta {
	
	/* Exemplo persist
	public static void main(String[] args) {
		Conta conta = new Conta();
		conta.setTitular("Leonardo");
		conta.setAgencia("123");
		conta.setBanco("Caixa");
		conta.setNumero("456");
		
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		// o persistent faz a transação sair do estado "Transient" para "Managed"
		em.persist(conta);
		
		// neste momento a conta ja esta managed, então é a conta é criada(Create), e logo após é alterada(Update).
		conta.setBanco("Bradesco");
		em.getTransaction().commit();
		
		em.close();
		
	}*/
	
	public static void main(String[] args) {
		Conta conta = new Conta();
		conta.setId(1);
		conta.setTitular("Leonardo");
		conta.setAgencia("123");
		conta.setBanco("Caixa");
		conta.setNumero("456");
		
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		
		// necessario obter a conta, para remover, pois o JPA não remove contas DETATCHED
		conta = em.find(Conta.class, 1);
		
		// o persistent faz a transação sair do estado "Transient" para "Managed"
		em.remove(conta);
		
		// neste momento a conta ja esta managed, então é a conta é criada(Create), e logo após é alterada(Update).
		conta.setBanco("Bradesco");
		em.getTransaction().commit();
		
		em.close();
		
	}

}
