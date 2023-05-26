//package ar.com.semillero.semillatronalfa.entities.seed;
//
//import ar.com.semillero.semillatronalfa.entities.Commission;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToOne;
//import lombok.AccessLevel;
//import lombok.Data;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.hibernate.annotations.GenericGenerator;
//
//@Entity
//@Data
//@NoArgsConstructor
//public class SeedData {
//
//    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
//    @Setter(value = AccessLevel.NONE)
//    @Column(nullable = false)
//    private String id;
//
//    @ManyToOne
//    private Commission commission;
//
//    @OneToOne
//    private SeedFollowUp followUp;
//
//    @OneToOne
//    private SeedPersonalData personalData;
//
//    @OneToOne
//    private SeedContactData contactData;
//
//    @OneToOne
//    private SeedPostulationData postulationData;
//    
//    @OneToOne
//    @JoinColumn(name = "seed_id")
//    @Getter(value= AccessLevel.NONE)
//    @JsonIgnore
//    private Seed seed;
//
//    public SeedData(Commission commission, SeedFollowUp followUp, SeedPersonalData personalData, SeedContactData contactData, SeedPostulationData postulationData) {
//        this.commission = commission;
//        this.followUp = followUp;
//        this.personalData = personalData;
//        this.contactData = contactData;
//        this.postulationData = postulationData;
//    }
//
//}
