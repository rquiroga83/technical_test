/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asd.api.ejb.activos.impl;

import com.asd.api.common.activos.dto.ActivosResponseDto;
import com.asd.api.ejb.activos.ActivosBeanLocal;
import com.asd.api.persistencia.entidades.ActivoFijo;
import com.asd.api.persistencia.entidades.EstadoActual;
import com.asd.api.persistencia.entidades.Tipo;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.persistence.NoResultException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andres
 */
public class ActivosBeanTest {
    
    public static EJBContainer container;
    public static Context ctx;
    
    public ActivosBeanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(EJBContainer.MODULES, new File("target/classes"));
        properties.put("org.glassfish.ejb.embedded.glassfish.installation.root", "./src/test/glassfish");
        properties.put("org.glassfish.ejb.embedded.glassfish.configuration.file", "./src/test/glassfish/domains/domain/config/domain.xml");
        container = EJBContainer.createEJBContainer(properties);
        ctx = container.getContext();
    }
    
    @AfterClass
    public static void tearDownClass() {
        container.close();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Funcion que prueba la creacion de un activo 
     */

    @Test
    public void testCrearActivoFijo() throws Exception {
        System.out.println("crearActivoFijo");
        
        // Hace lokup del ejb de sesion
        ActivosBeanLocal instance = (ActivosBeanLocal)ctx.lookup("java:global/classes/ActivosBean");
        
        // Crea fecha
        String inputString = "2017-05-14";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date inputDate = dateFormat.parse(inputString);
        
        // Obtiene tipo y estado
        Tipo tipo = null;
        EstadoActual estadoActual = null;
       
        tipo = instance.getTipoActivoById(1);
        estadoActual = instance.getEstadoActualActivoById(1);
            

        
        //System.out.println(tipo.toString());
        
        if(tipo == null){
            tipo = new Tipo(1, "maquinaria");
            instance.crearTipo(tipo);
            //fail("El tipo de activo con ID 1 no existe en la base de datos por favor insertarlo.");
        }
        if(estadoActual == null) {
            //fail("El estado de activo con ID 1 no existe en la base de datos por favor insertarlo.");
            estadoActual = new EstadoActual(1, "Activo");
            instance.crearEstadoActual(estadoActual);
        }
        
        // Obtiene Crea entidad
        ActivoFijo activoFijo = new ActivoFijo();
            activoFijo.setNombre("Prueba");
            activoFijo.setDescripcion("Activo para casos de prueba");
            activoFijo.setNumeroInternoInventario("123456");
            activoFijo.setSerial("836955425");
            activoFijo.setPeso(1.2);
            activoFijo.setAlto(1.3);
            activoFijo.setAncho(1.4);
            activoFijo.setLargo(1.5);
            activoFijo.setValorCompra(50000);
            activoFijo.setFechaBaja(inputDate);
            activoFijo.setFechaCompra(inputDate);
            activoFijo.setTipoId(tipo);
            activoFijo.setEstadoActualId(estadoActual);
        
        try {
            // Invova funcion
            instance.crearActivoFijo(activoFijo);
        }
        catch (Exception e) {
            fail("Error en el almacenamiento del activo revise configuracion de conecion base de datos archivo domain.xml linea 241.");
        }
        
    }


    /**
     * Test of obtenerActivos method, of class ActivosBean.
     */
    @Test
    public void testObtenerActivos() throws Exception {
        System.out.println("obtenerActivos");
        // Obtiene EJB
        ActivosBeanLocal instance = (ActivosBeanLocal)ctx.lookup("java:global/classes/ActivosBean");
        // Invoca Funcion
        ActivosResponseDto result = instance.obtenerActivos();
        assertEquals(200, result.getResult().getResultCode());

    }
    
    /**
     * Funcion que prueba la obtencion de un activo por serial
     * @throws Exception 
     */
    @Test
    public void testObtenerActivosSerial() throws Exception {
        System.out.println("obtenerActivosSerial");
        String serial = "836955425";
        // Obtiene EJB
        ActivosBeanLocal instance = (ActivosBeanLocal)ctx.lookup("java:global/classes/ActivosBean");
        // Invoca Funcion
        ActivosResponseDto result = instance.obtenerActivosSerial(serial);
        // Valida respuesta
        assertEquals(200, result.getResult().getResultCode());
        assertEquals(serial,result.getActivosFijos().get(0).getSerial());
        
        
    }


    
}
