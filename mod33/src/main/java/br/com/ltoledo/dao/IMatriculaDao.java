package br.com.ltoledo.dao;

import br.com.ltoledo.domain.Curso;
import br.com.ltoledo.domain.Matricula;

public interface IMatriculaDao {

    Matricula cadastrar(Matricula mat);

    Matricula buscarPorCodigoCurso(String codigoCurso);

    Matricula buscarPorCurso(Curso curso);

    Matricula buscarPorCodigoCursoCriteria(String codigoCurso);

    Matricula buscarPorCursoCriteria(Curso curso);

}