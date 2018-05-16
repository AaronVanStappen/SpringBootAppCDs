package be.qnh.hobby.cds.rest;

import be.qnh.hobby.cds.domain.Artist;
import be.qnh.hobby.cds.domain.CompactDisc;
import be.qnh.hobby.cds.domain.User;
import be.qnh.hobby.cds.service.ArtistService;
import be.qnh.hobby.cds.service.CdsService;
import be.qnh.hobby.cds.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cds")
public class HobbyEndpoint {
    private final static Logger LOGGER = LoggerFactory.getLogger(HobbyEndpoint.class);
    private final CdsService service;
    private final ArtistService artistService;
    private final UserService userService;

    @Autowired
    public HobbyEndpoint(CdsService service, ArtistService artistService, UserService userService) {
        this.service = service;
        this.artistService = artistService;
        this.userService = userService;
    }


    @RequestMapping(value="list", method = RequestMethod.GET)
    public ResponseEntity<Iterable<CompactDisc>> getAll() {
        List<CompactDisc> cds = service.allCDs();

        LOGGER.info(cds.toString());

        if (cds.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(cds, HttpStatus.OK);
        }
    }

    @RequestMapping(value="list/add", method = RequestMethod.POST)
    public void addCD(@RequestBody CompactDisc disc) {
        artistService.addArtist(disc.getArtist());
        service.addCD(disc);
    }

    @RequestMapping(value="getone/{id}", method = RequestMethod.GET)
    public ResponseEntity<CompactDisc> getOne(@PathVariable long id) {
        CompactDisc disc = service.getOneDisc(id);
        if(disc == null) {
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        } else {
            return new ResponseEntity<>(disc, HttpStatus.OK);
        }
    }

    @RequestMapping(value="delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CompactDisc> deleteOne(@PathVariable Long id) {
        CompactDisc disc = service.getOneDisc(id);
        if (disc != null) {
            service.deleteCD(id);
            return new ResponseEntity<>(disc, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value="delete/all", method = RequestMethod.DELETE)
    public void deleteAll() {
        service.deleteAll();
    }

    @RequestMapping(value="update", method = RequestMethod.POST)
    public void updateCD(@RequestBody CompactDisc disc) {
        //artistService.updateArtist(disc.getArtist());
        service.updateCD(disc);
    }

    @RequestMapping(value="byartist/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<CompactDisc>> getCompactDiscByArtist(@PathVariable long id) {
        Iterable<CompactDisc> cds = service.getCompactDiscByArtist(id);

        LOGGER.info(cds.toString());

        if (cds.iterator().hasNext()) {
            return new ResponseEntity<>(cds, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="byalbum/{name}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<CompactDisc>> getCompactDiscByAlbumContaining(@PathVariable String name) {
        Iterable<CompactDisc> cds = service.getCompactDiscByAlbumContaining(name);

        LOGGER.info(cds.toString());

        if (cds.iterator().hasNext()) {
            return new ResponseEntity<>(cds, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="byartist/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<CompactDisc>> getCompactDiscByArtistBandName(@PathVariable String name) {
        Iterable<CompactDisc> cds = service.getCompactDiscByArtistBandName(name);

        LOGGER.info(cds.toString());

        if (cds.iterator().hasNext()) {
            return new ResponseEntity<>(cds, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="byalbum/year/{year}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<CompactDisc>> getCompactDiscByYearAfter(@PathVariable String year) {
        Iterable<CompactDisc> cds = service.getCompactDiscByYearAfter(year);

        LOGGER.info(cds.toString());

        if (cds.iterator().hasNext()) {
            return new ResponseEntity<>(cds, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="artist/album/{keyword}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Artist>> getArtistAlbum(@PathVariable String keyword) {
        Iterable<Artist> artists = artistService.getArtistAlbum(keyword);

        LOGGER.info(artists.toString());

        if (artists.iterator().hasNext()) {
            return new ResponseEntity<>(artists, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="usr/add", method = RequestMethod.POST)
    public void addUser(@RequestBody User user) {
        LOGGER.info(user.toString());
        userService.save(user);
    }
}
