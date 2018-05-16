package be.qnh.hobby.cds.repository;

import be.qnh.hobby.cds.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistJpaRepository extends JpaRepository<Artist, Long> {
    @Query(value = "SELECT a.id, a.bandName, a.genre, a.country, a.since from Artist a where a.bandName like :albumName")
    Iterable<Artist> getArtistAlbum(@Param("albumName") String albumName);
}
