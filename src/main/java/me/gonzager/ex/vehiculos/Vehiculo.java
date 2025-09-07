package me.gonzager.ex.vehiculos;

public class Vehiculo {
    private Double combustible;
    private Double kilometraje;
    private TipoConduccion tipoConduccion;

    public Vehiculo(Double combustibleInicial) {
        if (combustibleInicial < 0) {
            throw new RuntimeException("Cantidad de combustible no puede ser negativa.");
        }
        this.combustible = combustibleInicial;
        this.kilometraje = 0.0;
        this.tipoConduccion = Ecologica.INSTANCE;
    }

    public Double getCombustible() {
        return combustible;
    }

    public Double getKilometraje() {
        return kilometraje;
    }

    public Double getVelocidadMaxima() {
        return tipoConduccion.velocidadMaxima();
    }

    public void avanzarConduccion() {
        TipoConduccion siguiente = tipoConduccion.siguiente();
        if (siguiente != null) {
            tipoConduccion = siguiente;
        }
    }

    public void retrocederConduccion() {
        TipoConduccion anterior = tipoConduccion.anterior();
        if (anterior != null) {
            tipoConduccion = anterior;
        }
    }

    public void desplazar(Double kilometros) {
        if (kilometros < 0) {
            throw new RuntimeException("Distancia a desplazar negativa.");
        }
        
        
        Double kmPorLitro = tipoConduccion.consumo();
        Double maxDistancia = combustible * kmPorLitro;

        if (kilometros <= maxDistancia) {
            // Caso 1 todo ok
            kilometraje += kilometros;
            combustible -= (kilometros / kmPorLitro);
        } else {
            // Caso 2 comb insuf
            Double kmRecorridos = maxDistancia;
            Double totalKmSolicitados = kilometros;
            
            kilometraje += kmRecorridos;
            combustible = 0.0;
            
            throw new RuntimeException("Combustible insuficiente, solo pude recorrer " + kmRecorridos + " de " + totalKmSolicitados + " kilometros.");
        }
    }
}