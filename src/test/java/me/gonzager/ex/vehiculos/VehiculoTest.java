package me.gonzager.ex.vehiculos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class VehiculoTest {

    @Test
    void vehiculoRetrocediendoYAvanzandoTiposDeConduccionDeberiaRetornarCorrectamenteLaVelocidad() {
        // 1. Crear un vehículo con 3 Litros de combustible.
        Vehiculo vehiculo = new Vehiculo(3.0);

        // 2. Retroceder el tipo de conducción una vez.
        vehiculo.retrocederConduccion();
        // Verificamos que se mantiene en Ecológica
        assertEquals(120.0, vehiculo.getVelocidadMaxima());

        // 3. Avanzar el tipo de conduccion 3 veces.
        vehiculo.avanzarConduccion(); // -> Estándar
        vehiculo.avanzarConduccion(); // -> Deportiva
        vehiculo.avanzarConduccion(); // -> Se mantiene en Deportiva
        
        // 4. Retroceder el tipo de conducción una vez.
        vehiculo.retrocederConduccion(); // -> Vuelve a Estándar

        // 5. Verificar que la velocidad máxima del auto es de 150 km/h.
        assertEquals(150.0, vehiculo.getVelocidadMaxima());
    }

    
    @Test
    void vehiculoEnConduccionEstandarDeberiaAvanzarCorrectamenteYReducirElCombustible() {
        // 1. Crear un vehículo con 25 Litros de combustible.
        Vehiculo vehiculo = new Vehiculo(25.0);

        // 2. Cambiar una vez el tipo de conducción.
        vehiculo.avanzarConduccion(); // Ahora está en modo Estándar
        assertEquals(150.0, vehiculo.getVelocidadMaxima());

        // 3. Desplazar 200 kilómetros.
        // Consumo Estándar: 10 km/litro -> 200 km / 10 km/litro = 20 litros
        vehiculo.desplazar(200.0);

        // 4. Verificar que el auto queden con 200.0 kilometros y con 5.0 litros de combustible.
        assertEquals(200.0, vehiculo.getKilometraje());
        assertEquals(5.0, vehiculo.getCombustible());
    }

    
    @Test
    void vehiculoEnConduccionDeportivaDeberiaLanzarUnaExcepcionSiNoHaySuficienteCombustible() {
        // 1. Crear un vehículo con 13 Litros de combustible.
        Vehiculo vehiculo = new Vehiculo(13.0);

        // 2. Cambiar dos veces el tipo de conducción.
        vehiculo.avanzarConduccion(); // -> Estándar
        vehiculo.avanzarConduccion(); // -> Deportiva
        assertEquals(200.0, vehiculo.getVelocidadMaxima());

        // 3. Desplazar 75 kilómetros.
        // Consumo Deportiva: 5 km/litro -> 13 litros * 5 km/litro = 65 km de autonomía
        Double kmSolicitados = 75.0;
        Double kmRecorridosEsperados = 65.0;
        
        // 4. Verificar que se produjo la RuntimeException
        RuntimeException excepcion = assertThrows(RuntimeException.class, () -> {
            vehiculo.desplazar(kmSolicitados);
        });

        // Y que el mensaje de la excepción es el correcto.
        assertEquals("Combustible insuficiente, solo pude recorrer 65.0 de 75.0 kilometros.", excepcion.getMessage());
        
        // Y que el auto quedó con el kilometraje y combustible actualizados.
        assertEquals(kmRecorridosEsperados, vehiculo.getKilometraje());
        assertEquals(0.0, vehiculo.getCombustible());
    }

    @Test
    void vehiculoCreadoConCombustibleNegativoDeberiaLanzarUnaExcepcion() {
        // 1. Crear un vehículo con -10 Litros de combustible.
        // 2. Verificar que se produjo la RuntimeException.
        RuntimeException excepcion = assertThrows(RuntimeException.class, () -> {
            new Vehiculo(-10.0);
        });
        
        assertEquals("Cantidad de combustible no puede ser negativa.", excepcion.getMessage());
    }


    @Test
    void verificarQueCadaVezQueSeAvanceDeTipoDeConduccionLaVelocidadMaximaEsMayor() {
        // 1. Crear un vehículo con 10 Litros de combustible.
        Vehiculo vehiculo = new Vehiculo(10.0);

        // 2. Guardar en una variable el valor de la velocidad máxima ecológica.
        Double velocidadEcologica = vehiculo.getVelocidadMaxima(); // 120.0

        // 3. Avanzar el tipo de conducción.
        vehiculo.avanzarConduccion();

        // 4. Guardar en una variable el valor de la velocidad máxima estándar.
        Double velocidadEstandar = vehiculo.getVelocidadMaxima(); // 150.0

        // 5. Avanzar el tipo de conducción.
        vehiculo.avanzarConduccion();

        // 6. Guardar en una variable el valor de la velocidad máxima deportiva.
        Double velocidadDeportiva = vehiculo.getVelocidadMaxima(); // 200.0

        // 7. Verificar que la velocidad máxima ecológica es menor que la velocidad máxima estándar.
        assertTrue(velocidadEcologica < velocidadEstandar);

        // 8. Verificar que la velocidad máxima estándar no es mayor que la velocidad máxima deportiva.
        assertFalse(velocidadEstandar > velocidadDeportiva);
        
        // 9. Verificar que la velocidad máxima estándar no es igual que la velocidad máxima deportiva.
        assertNotEquals(velocidadEstandar, velocidadDeportiva);
    }
}