public class Comparendo {

    private Limites limitesCarro;
    private Limites limitesCamion;
    private Limites limitesMula;

    public enum TipoVehiculo {
        CARRO,
        CAMION,
        MULA
    }

    private static class Limites {
        int limiteInferior;
        int limiteInferiorIntermedio;
        int limiteSuperior;

        Limites(int limiteInferior, int limiteInferiorIntermedio, int limiteSuperior) {
            this.limiteInferior = limiteInferior;
            this.limiteInferiorIntermedio = limiteInferiorIntermedio;
            this.limiteSuperior = limiteSuperior;
        }
    }

    public Comparendo(Limites limitesCarro, Limites limitesCamion, Limites limitesMula) {
        this.limitesCarro = limitesCarro;
        this.limitesCamion = limitesCamion;
        this.limitesMula = limitesMula;
    }

    public Comparendo(int limiteInferiorCarro) {
        this.limitesCarro = new Limites(limiteInferiorCarro, limiteInferiorCarro + 1, 85);
        // Definir valores por defecto para los otros vehículos
        this.limitesCamion = new Limites(75, 76, 95);
        this.limitesMula = new Limites(95, 96, 110);
    }


    public Comparendo() {
        // Definir valores por defecto para todos los vehículos
        this.limitesCarro = new Limites(65, 66, 85);
        this.limitesCamion = new Limites(75, 76, 95);
        this.limitesMula = new Limites(95, 96, 110);
    }

    public void construirFotoMulta(int velocidad, TipoVehiculo tipoVehiculo) {
        int cpa = calcularComparendo(velocidad, tipoVehiculo);
        String txt = enviarCorreoFotomulta(tipoVehiculo);
        if (cpa == -1) {
            System.out.println("No hay cálculo para el tipo de vehículo " + tipoVehiculo + ". Correo: " + txt);
        } else {
            System.out.println("---- El tipo de comparendo es: " + cpa + " ---- Cuerpo del correo: " + txt);
        }
    }

    private int calcularComparendo(int velocidad, TipoVehiculo tipoVehiculo) {
        Limites limites = obtenerLimites(tipoVehiculo);
        if (limites == null) return -1;

        if (velocidad <= limites.limiteInferior) {
            return 0;
        } else if (velocidad >= limites.limiteInferiorIntermedio && velocidad <= limites.limiteSuperior) {
            return 1;
        } else {
            return 2;
        }
    }

    private Limites obtenerLimites(TipoVehiculo tipoVehiculo) {
        switch (tipoVehiculo) {
            case CARRO:
                return limitesCarro;
            case CAMION:
                return limitesCamion;
            case MULA:
                return limitesMula;
            default:
                return null;
        }
    }

    private String enviarCorreoFotomulta(TipoVehiculo tipoVehiculo) {
        String cuerpoMensaje;
        String asunto;
        switch (tipoVehiculo) {
            case CAMION:
                cuerpoMensaje = "// Enviando correo para el tipo camión.";
                asunto = "// Asunto: Comparendo camión";
                break;
            case CARRO:
                cuerpoMensaje = "// Enviando correo para el tipo carro.";
                asunto = "// Asunto: Comparendo carro";
                break;
            case MULA:
                cuerpoMensaje = "// Enviando correo para el tipo mula.";
                asunto = "// Asunto: Comparendo mula";
                break;
            default:
                return "Enviando correo con vehículo desconocido";
        }
        return asunto + cuerpoMensaje;
    }
}
