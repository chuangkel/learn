package com.github.chuangkel.java_learn.base.java18.streamT;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-05-30 16:13
 **/
public class Album {
    public Track[] getTrackList() {

        return new Track[]{new Track("a",1)};
    }

    private List<Track> tracks = new ArrayList<>(16);

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
