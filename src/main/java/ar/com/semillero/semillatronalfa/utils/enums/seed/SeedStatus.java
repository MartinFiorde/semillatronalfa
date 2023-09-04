package ar.com.semillero.semillatronalfa.utils.enums.seed;

public enum SeedStatus {

    STATUS_1("Postulada", null),
    STATUS_2_1("Activa", "En espera de proyecto"),
    STATUS_2_2("Activa", "Asignado a proyecto"),
    STATUS_2_3("Activa", "No disponible (temporal)"),
    STATUS_2_4("Activa", "No disponible (indefinido)"),
    STATUS_3_1("Inactiva", "Por trabajo"),
    STATUS_3_2("Inactiva", "Por abandono"),
    STATUS_4("Finalizó proyecto", null);

    private final String primary;
    private final String secondary;

    private SeedStatus(String primary, String secondary) {
        this.primary = primary;
        this.secondary = secondary;
    }

    public String getPrimary() {
        return primary;
    }

    public String getSecondary() {
        return secondary;
    }

}
