package be.qnh.hobby.cds.service;

import be.qnh.hobby.cds.domain.CompactDisc;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CdsService {
    List<CompactDisc> allCDs();

    CompactDisc addCD(CompactDisc disc);

    CompactDisc getOneDisc(Long id);

    int deleteAll();

    CompactDisc deleteCD(Long id);

    CompactDisc updateCD(CompactDisc disc);

    Iterable<CompactDisc> getCompactDiscByArtist(Long id);

    Iterable<CompactDisc> getCompactDiscByAlbumContaining(String album);

    Iterable<CompactDisc> getCompactDiscByArtistBandName(String bandName);

    Iterable<CompactDisc> getCompactDiscByYearAfter(String year);
}
