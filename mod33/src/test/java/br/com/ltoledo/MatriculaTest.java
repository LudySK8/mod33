package br.com.ltoledo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.Instant;

import org.junit.Test;

import br.com.ltoledo.dao.AlunoDao;
import br.com.ltoledo.dao.ComputadorDao;
import br.com.ltoledo.dao.CursoDao;
import br.com.ltoledo.dao.IAlunoDao;
import br.com.ltoledo.dao.IComputador;
import br.com.ltoledo.dao.ICursoDao;
import br.com.ltoledo.dao.IMatriculaDao;
import br.com.ltoledo.dao.MatriculaDao;
import br.com.ltoledo.domain.Aluno;
import br.com.ltoledo.domain.Computador;
import br.com.ltoledo.domain.Curso;
import br.com.ltoledo.domain.Matricula;

public class MatriculaTest {

    private IMatriculaDao matriculaDao;

    private ICursoDao cursoDao;

    private IAlunoDao alunoDao;

    private IComputador computadorDao;

    public MatriculaTest() {
        matriculaDao = new MatriculaDao();
        cursoDao = new CursoDao();
        alunoDao = new AlunoDao();
        computadorDao = new ComputadorDao();
    }

    @Test
    public void cadastrar() {
        Curso curso = criarCurso("A1");
        Aluno aluno = criarAluno("A1");

        Matricula mat = new Matricula();
        mat.setCodigo("A1");
        mat.setDataMatricula(Instant.now());
        mat.setStatus("ATIVA");
        mat.setValor(2000d);
        mat.setCurso(curso);
        mat.setAluno(aluno);

        aluno.setMatricula(mat);
        mat = matriculaDao.cadastrar(mat);

        assertNotNull(mat);
        assertNotNull(mat.getId());

        Matricula matBD = matriculaDao.buscarPorCodigoCurso(mat.getCodigo());
        assertNotNull(matBD);
        assertEquals(mat.getId(), matBD.getId());

        Matricula matBDObj = matriculaDao.buscarPorCurso(curso);
        assertNotNull(matBDObj);
        assertEquals(mat.getId(), matBDObj.getId());
    }

    private Computador criarComputador(String codigo) {
        Computador comp = new Computador();
        comp.setCodigo(codigo);
        comp.setDescricao("Comp 1");
        return comp;
        //return computadorDao.cadastrar(comp);
    }

    private Aluno criarAluno(String codigo) {
        Computador comp = criarComputador("A1");
        Computador comp2 = criarComputador("A2");
        Aluno aluno = new Aluno();
        aluno.setCodigo(codigo);
        aluno.setNome("Ludmyla");
        aluno.add(comp);
        aluno.add(comp2);
        //comp.add(aluno);
        //comp2.add(aluno);
        return alunoDao.cadastrar(aluno);
    }

    private Curso criarCurso(String codigo) {
        Curso curso = new Curso();
        curso.setCodigo(codigo);
        curso.setDescricao("CURSO TESTE");
        curso.setNome("Curso de Java Backend");
        return cursoDao.cadastrar(curso);
    }
}