package com.practico.dao;

import java.util.List;

import com.practico.entidad.TrabajadorDeLaSalud;

import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@SuppressWarnings("unused")
@Singleton
public class TrabajadorDeLaSaludDAOBean implements TrabajadorDeLaSaludDAORemote, TrabajadorDeLaSaludDAOLocal {
    @PersistenceContext(unitName="practicojavaeePersistenceUnit")
    private EntityManager em;

    @Override
    public void agregarTrabajador(TrabajadorDeLaSalud t) {
        em.persist(t);
    }

    @Override
    public List<TrabajadorDeLaSalud> listar() {
        return em.createQuery("SELECT t FROM TrabajadorDeLaSalud t", TrabajadorDeLaSalud.class).getResultList();
    }

    @Override
    public List<TrabajadorDeLaSalud> buscarPorNombre(String nombre) {
        return em.createQuery("SELECT t FROM TrabajadorDeLaSalud t WHERE t.nombre = :nombre", TrabajadorDeLaSalud.class)
                 .setParameter("nombre", nombre)
                 .getResultList();
    }

    @Override
    public TrabajadorDeLaSalud buscarPorMatricula(Integer matricula) {
        try {
            return em.createQuery("SELECT t FROM TrabajadorDeLaSalud t WHERE t.matricula = :matricula", TrabajadorDeLaSalud.class)
                     .setParameter("matricula", matricula)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void eliminarTodos() {
        em.createQuery("DELETE FROM TrabajadorDeLaSalud").executeUpdate();
    }
}
