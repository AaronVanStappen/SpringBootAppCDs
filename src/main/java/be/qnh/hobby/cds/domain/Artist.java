package be.qnh.hobby.cds.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity
@Table(name="Artist")
@Transactional
public class Artist implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    private long id;

    @Column(name="BandName")
    private String bandName;

    @Column(name="Since")
    private String since;

    @Column(name="Genre")
    private String genre;

    @Column(name="Country")
    private String country;

    @JsonIgnore
    @XmlTransient
    @OneToMany(mappedBy="artist", cascade=CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CompactDisc> albums = new ArrayList<>();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<CompactDisc> getAlbums() {
        return albums;
    }

    public void setAlbums(List<CompactDisc> albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id='" + id + '\'' +
                "bandName='" + bandName + '\'' +
                ", since='" + since + '\'' +
                ", genre='" + genre + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
