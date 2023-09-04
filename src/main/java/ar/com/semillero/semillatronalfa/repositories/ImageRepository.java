package ar.com.semillero.semillatronalfa.repositories;

import ar.com.semillero.semillatronalfa.models.event.Image;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query(value = "INSERT INTO imagen (nombre, tipo, tamano, pixel) VALUES(:nombre, :tipo, :tamano, :pixel)", nativeQuery = true)
    void insertImage(@Param("nombre") String nombre, @Param("tipo") String tipo,@Param("tamano") Long tamano,@Param("pixel") byte[] pixel);

    @Query(value = "select image.* from image where image.id = :id", nativeQuery = true)
    Image findImageById(@Param("id") String id);
}
