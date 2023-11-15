package ar.com.semillero.semillatronalfa.models.dtos;

import ar.com.semillero.semillatronalfa.models.project.Project;
import ar.com.semillero.semillatronalfa.models.seed.Seed;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectSeedDto implements Serializable {
    private String id;
    private Project project;
    private Seed seed;
}
