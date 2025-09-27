package com.practico.mdb;

import java.time.LocalDate;

import com.practico.entidad.TrabajadorDeLaSalud;
import com.practico.excepcion.ExcepcionDeNegocio;
import com.practico.servicios.TrabajadorDeLaSaludServiceLocal;

import jakarta.ejb.MessageDriven;
import jakarta.inject.Inject;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

@MessageDriven(
    activationConfig={
        @jakarta.ejb.ActivationConfigProperty(propertyName="destinationLookup", propertyValue="java:/jms/queue/queue_alta_trabajador"),
        @jakarta.ejb.ActivationConfigProperty(propertyName="destinationType", propertyValue="jakarta.jms.Queue"),
    }
)
public class TrabajadorMDB implements MessageListener {

    @Inject
    private TrabajadorDeLaSaludServiceLocal service;

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage textMessage) {
                String texto = textMessage.getText();
                // formato: nombre|matricula
                String[] partes = texto.split("\\|");

                TrabajadorDeLaSalud t = new TrabajadorDeLaSalud();
                t.setNombre(partes[0]);
                t.setMatricula(Integer.parseInt(partes[1]));
                t.setFechaRegistro(LocalDate.now());

                service.agregarTrabajador(t);
                System.out.println("Trabajador agregado por MDB: " + t.getNombre());
            }
        } catch (ExcepcionDeNegocio | JMSException | NumberFormatException e) {
        }
    }

}
