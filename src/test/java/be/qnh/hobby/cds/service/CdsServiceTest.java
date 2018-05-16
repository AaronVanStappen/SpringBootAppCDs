package be.qnh.hobby.cds.service;

import be.qnh.hobby.cds.HobbyApplication;
import be.qnh.hobby.cds.domain.Artist;
import be.qnh.hobby.cds.domain.CompactDisc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HobbyApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CdsServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private CdsService cdsService;
    @Autowired
    private ArtistService artistService;

    @Test
    public void testCreateIT() {
        Artist testArtist = new Artist();
        testArtist.setBandName("Enbilulugugal");
        testArtist.setGenre("Noise/Black Metal");
        testArtist.setCountry("United States Of America");
        testArtist.setSince("1998");

        Artist newTestArtist = artistService.addArtist(testArtist);

        CompactDisc compactDiscTest = new CompactDisc();
        compactDiscTest.setAlbum("Noize Mongers For Warserpent");
        compactDiscTest.setYear("2005");
        compactDiscTest.setDuration("43:29");
        compactDiscTest.setArtist(testArtist);

        CompactDisc newCompactDiscTest = cdsService.addCD(compactDiscTest);

        assertThat(newTestArtist.getId()).isNotNull();
        assertThat(newCompactDiscTest.getId()).isNotNull();

        Long id = newCompactDiscTest.getId();

        CompactDisc compactDiscOpgehaald = this.cdsService.getOneDisc(id);

        assertThat(compactDiscOpgehaald).isNotNull();

        compactDiscOpgehaald.setAlbum("The Satanic Warmaster Is Here");

        CompactDisc compactDiscUpdated = cdsService.updateCD(compactDiscOpgehaald);

        assertThat(compactDiscUpdated.getAlbum()).isEqualToIgnoringCase("the satanic warmaster is here");

        this.cdsService.deleteCD(id);

        assertThat(this.cdsService.getOneDisc(id)).isNull();
    }
}
