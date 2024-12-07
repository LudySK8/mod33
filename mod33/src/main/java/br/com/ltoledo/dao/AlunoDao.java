package br.com.ltoledo.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.ltoledo.domain.Aluno;

public class AlunoDao implements IAlunoDao{

    @Override
    public Aluno cadastrar(Aluno aluno) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(aluno);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();


        return aluno;
    }

}