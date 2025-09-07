package me.gonzager.ex.vehiculos;

public class Normal implements TipoConduccion {

    public static final Normal INSTANCE = new Normal();

    private Normal() {}

    @Override
    public Double consumo() {
        return 10.0;
    }

    @Override
    public Double velocidadMaxima() {
        return 150.0;
    }

    @Override
    public TipoConduccion siguiente() {
        return Deportiva.INSTANCE;
    }

    @Override
    public TipoConduccion anterior() {
        return Ecologica.INSTANCE;
    }
}
