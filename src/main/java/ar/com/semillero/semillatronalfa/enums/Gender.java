package ar.com.semillero.semillatronalfa.enums;

// Como usar Enums en el front para generar listas desplegables: https://www.baeldung.com/thymeleaf-enums
// Otro tutorial al respecto: https://frontbackend.com/thymeleaf/using-enums-in-thymeleaf
public enum Gender {
    MALE("Masculino"), 
    FEMALE("Femenino"), 
    NON_BINARY("No binario"), 
    NO_DATA("Sin información");

    private final String displayValue;
    
    private Gender(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }

}
