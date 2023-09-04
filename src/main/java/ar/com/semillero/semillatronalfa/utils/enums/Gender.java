package ar.com.semillero.semillatronalfa.utils.enums;

// Como usar Enums en el front para generar listas desplegables: https://www.baeldung.com/thymeleaf-enums
// Otro tutorial del tema: https://frontbackend.com/thymeleaf/using-enums-in-thymeleaf
public enum Gender {
    MALE("Masculino"), 
    FEMALE("Femenino"), 
    NON_BINARY("No binarie"), 
    NO_DATA("Sin información");

    private final String displayValue;
    
    private Gender(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }

}
