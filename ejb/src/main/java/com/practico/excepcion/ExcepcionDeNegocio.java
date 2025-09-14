package com.practico.excepcion;

import jakarta.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class ExcepcionDeNegocio extends Exception {
    private static final long serialVersionUID = 1L;

    public ExcepcionDeNegocio() {
        super();
    }

    public ExcepcionDeNegocio(String message) {
        super(message);
    }

}
