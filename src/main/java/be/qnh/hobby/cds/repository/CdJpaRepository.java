package be.qnh.hobby.cds.repository;

import be.qnh.hobby.cds.domain.CompactDisc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CdJpaRepository extends JpaRepository<CompactDisc, Long> {
    Iterable<CompactDisc> getCompactDiscByArtistId(Long id);
    Iterable<CompactDisc> getCompactDiscByAlbumContaining(String album);
    Iterable<CompactDisc> getCompactDiscByArtistBandName(String bandName);
    Iterable<CompactDisc> getCompactDiscByYearAfter(String year);
}
