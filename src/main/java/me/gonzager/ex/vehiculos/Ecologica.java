package me.gonzager.ex.vehiculos;

public class Ecologica implements TipoConduccion {

    public static final Ecologica INSTANCE = new Ecologica();

    private Ecologica() {}

    @Override
    public Double consumo() {
        return 16.0; // km/litro
    }

    @Override
    public Double velocidadMaxima() {
        return 120.0; // km/h
    }

    @Override
    public TipoConduccion siguiente() {
        return Normal.INSTANCE;
    }

    @Override
    public TipoConduccion anterior() {
        return null;
    }
}