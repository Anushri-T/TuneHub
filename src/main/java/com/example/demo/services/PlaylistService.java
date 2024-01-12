package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entities.Playlist;
import com.example.demo.repositories.PlaylistRepository;


public interface PlaylistService {
	public void addPlaylist(Playlist playlist);

	public List<Playlist> fetchAllPlaylists();

}
