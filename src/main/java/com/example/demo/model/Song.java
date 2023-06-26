package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "songs")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private String avatar;
    @NotNull
    private String lyrics;
    @NotNull
    private String mp3Url;

    @ManyToOne
    private Category category;
    @ManyToOne
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "song_singer",
               joinColumns = @JoinColumn(name = "song_id"),
               inverseJoinColumns = @JoinColumn(name = "singer_id"))
    private List<Singer> singers = new ArrayList<>();

    public Song(String name, String avatar, String lyrics, String mp3Url, Category category, List<Singer> singers) {
        this.name = name;
        this.avatar = avatar;
        this.lyrics = lyrics;
        this.mp3Url = mp3Url;
        this.category = category;
        this.singers = singers;
    }
}
