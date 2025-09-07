package me.gonzager.ex.vehiculos;

public interface TipoConduccion {
    Double consumo();
    Double velocidadMaxima();
    TipoConduccion siguiente();
    TipoConduccion anterior();
}
