package ar.com.semillero.semillatronalfa.enums.seed;

public enum SeedRol {
    
    SCRUM("Desarrollador BackEnd"), 
    BACKEND("Desarrollador BackEnd"), 
    FRONTEND("Desarrollador FrontEnd"), 
    QA("QA"),
    INFRASTRUCTURE("Infraestructura"),
    UX_UI("UX/UI/IxD"),
    ANALYST("Analista");

    private final String displayValue;
    
    private SeedRol(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }

}

