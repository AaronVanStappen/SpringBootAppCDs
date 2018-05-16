package be.qnh.hobby.cds.unit;

import be.qnh.hobby.cds.domain.Artist;
import be.qnh.hobby.cds.domain.CompactDisc;
import be.qnh.hobby.cds.repository.CdJpaRepository;
import be.qnh.hobby.cds.service.CdsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CdsServiceUnitTest {

    @InjectMocks
    private CdsServiceImpl cdsService;

    @Mock
    private CdJpaRepository repo;

    @Mock
    private Artist artist;

    @Test
    public void testGetOneDisc() {

        CompactDisc resultFromRepo = new CompactDisc();
        resultFromRepo.setAlbum("Terror Propaganda");
        resultFromRepo.setYear("2003");
        resultFromRepo.setArtist(artist);


        // instruct the mock of the repo what to do
        Mockito.when(this.repo.findOne(3L)).thenReturn(resultFromRepo);

        // mocking done
        CompactDisc resultFromService = this.cdsService.getOneDisc(3L);

        assertThat(resultFromService.getAlbum()).isEqualToIgnoringCase("Terror Propaganda");
        assertThat(resultFromService.getYear()).isEqualToIgnoringCase("2003");

        Mockito.verify(this.repo).findOne(3L);
        Mockito.verify(this.repo, Mockito.times(1)).findOne(3L);

    }

    @Test
    public void testAddDisc() {
        CompactDisc resultFromRepo = new CompactDisc();
        resultFromRepo.setAlbum("Terror Propaganda");
        resultFromRepo.setYear("2003");
        resultFromRepo.setArtist(artist);

        Mockito.when(this.repo.saveAndFlush(resultFromRepo)).thenReturn(resultFromRepo);

        CompactDisc resultFromService = this.cdsService.addCD(resultFromRepo);

        assertThat(resultFromService).isEqualTo(resultFromRepo);

    }

    @Test
    public void testUpdateCD() {
        CompactDisc resultFromRepo = new CompactDisc();
        resultFromRepo.setArtist(artist);
        resultFromRepo.setAlbum("Terror Propaganda");
        resultFromRepo.setYear("2003");
        resultFromRepo.setDuration("32:19");
        resultFromRepo.setId(1L);

        Mockito.when(this.repo.saveAndFlush(resultFromRepo)).thenReturn(resultFromRepo);

        CompactDisc resultFromService = this.cdsService.addCD(resultFromRepo);
        resultFromService.setYear("2005");

        Mockito.when(this.repo.save(resultFromService)).thenReturn(resultFromService);

        CompactDisc updateFromService = this.cdsService.updateCD(resultFromService);

        assertThat(updateFromService.getYear()).isEqualToIgnoringCase("2005");

    }
}
