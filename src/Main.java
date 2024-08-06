public class Main {

    public static void main(String[] args) {
        Comparendo comparendo = new Comparendo();

        comparendo.construirFotoMulta(80, Comparendo.TipoVehiculo.CARRO);
        comparendo.construirFotoMulta(200, Comparendo.TipoVehiculo.MULA);
        comparendo.construirFotoMulta(40, Comparendo.TipoVehiculo.CAMION);
    }
}

