package be.qnh.hobby.cds.service;

import be.qnh.hobby.cds.domain.Artist;
import be.qnh.hobby.cds.repository.ArtistJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArtistServiceImpl implements ArtistService {
    private final ArtistJpaRepository repo;

    @Autowired
    public ArtistServiceImpl(ArtistJpaRepository repository) {
        this.repo = repository;
    }

    @Override
    public Artist addArtist(Artist artist) {
        return repo.saveAndFlush(artist);
    }

    @Modifying
    public Artist updateArtist(Artist artist){
        return repo.save(artist);
    }

    @Override
    public Iterable<Artist> getArtistAlbum(String keyword) {
        return repo.getArtistAlbum(keyword);
    }

    @Override
    public Artist deleteArtist(Long id) {
        Artist artist = repo.findOne(id);
        repo.delete(id);
        return artist;
    }
}
