package ar.com.semillero.semillatronalfa.utils.enums.seed;

public enum SeedTurn {
    
    MORNING("Mañana : : 9hs a 13hs"), 
    EVENING("Tarde : : 13hs a 17hs"), 
    NIGHT("Noche : : 17hs a 21hs"), 
    ANY_TURN("Cualquiera de los 3 turnos");

    private final String displayValue;
    
    private SeedTurn(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }

}

