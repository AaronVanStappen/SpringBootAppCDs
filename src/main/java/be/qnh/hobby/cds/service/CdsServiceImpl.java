package be.qnh.hobby.cds.service;

import be.qnh.hobby.cds.domain.Artist;
import be.qnh.hobby.cds.domain.CompactDisc;
import be.qnh.hobby.cds.repository.CdJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CdsServiceImpl implements CdsService {
    private final CdJpaRepository repo;
    private int counter = 1;
    private int i = 0;

    @Autowired
    public CdsServiceImpl(CdJpaRepository repository) {
        this.repo = repository;
    }

    @Override
    public List<CompactDisc> allCDs() {
        return repo.findAll();
    }

    @Override
    public CompactDisc addCD(CompactDisc disc) {
        return repo.saveAndFlush(disc);
    }

    @Override
    public CompactDisc getOneDisc(Long id) {
        return repo.findOne(id);
    }

    @Override
    public int deleteAll() {
        repo.deleteAll();
        return 0;
    }

    @Override
    public CompactDisc deleteCD(Long id) {
        CompactDisc disc = repo.findOne(id);
        repo.delete(id);
        return disc;
    }

    @Override
    @Modifying
    public CompactDisc updateCD(CompactDisc disc) {
        return repo.save(disc);
    }

    @Override
    public Iterable<CompactDisc> getCompactDiscByArtist(Long id) {
        return repo.getCompactDiscByArtistId(id);
    }

    @Override
    public Iterable<CompactDisc> getCompactDiscByAlbumContaining(String album) {
        return repo.getCompactDiscByAlbumContaining(album);
    }

    @Override
    public Iterable<CompactDisc> getCompactDiscByArtistBandName(String bandName) {
        return repo.getCompactDiscByArtistBandName(bandName);
    }

    @Override
    public Iterable<CompactDisc> getCompactDiscByYearAfter(String year) {
        return repo.getCompactDiscByYearAfter(year);
    }

    //@Scheduled(cron="*/1 * * * * *")
    //public void print() {
        //for (; i < counter; i++ ) {
            //System.out.print("*");
        //}
        //System.out.println("");
        //counter++;
        //i = 0;
    //}
}
