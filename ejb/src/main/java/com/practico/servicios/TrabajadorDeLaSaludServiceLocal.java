package com.practico.servicios;

import java.util.List;

import com.practico.entidad.TrabajadorDeLaSalud;
import com.practico.excepcion.ExcepcionDeNegocio;

public interface TrabajadorDeLaSaludServiceLocal {
    void  agregarTrabajador(TrabajadorDeLaSalud t) throws ExcepcionDeNegocio;
    List<TrabajadorDeLaSalud> listar();
    List<TrabajadorDeLaSalud> buscarPorNombre(String nombre);
    TrabajadorDeLaSalud buscarPorMatricula(Integer matricula);
}
