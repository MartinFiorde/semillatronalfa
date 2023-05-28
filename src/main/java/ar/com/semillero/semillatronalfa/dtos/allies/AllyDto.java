package ar.com.semillero.semillatronalfa.dtos.allies;

import ar.com.semillero.semillatronalfa.entities.ally.Ally;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class AllyDto {
    private String companyName;
    private String commission;
    private Date timestamp;
    private String objective;
    private String companyRepresentative;
    private String email;
    private String phoneNumber;
    private String address;


    public AllyDto(Ally ally) {
        this.companyName = ally.getCompanyName();
        this.commission = ally.getCommission();
        this.timestamp = ally.getTimestamp();
        this.objective = ally.getObjective();
        this.companyRepresentative = ally.getCompanyRepresentative();
        this.email = ally.getContactData().getEmail();
        this.phoneNumber = ally.getContactData().getPhoneNumber();
        this.address = ally.getContactData().getAddress();
    }
}
