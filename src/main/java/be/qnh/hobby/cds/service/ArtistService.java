package be.qnh.hobby.cds.service;

import be.qnh.hobby.cds.domain.Artist;
import org.springframework.stereotype.Repository;

public interface ArtistService {
    Artist addArtist(Artist artist);
    Artist updateArtist(Artist artist);
    Iterable<Artist> getArtistAlbum(String keyword);
    Artist deleteArtist(Long id);
}
