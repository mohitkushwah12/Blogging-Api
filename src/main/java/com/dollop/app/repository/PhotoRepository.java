package com.dollop.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.dollop.app.entity.Photos;

public interface PhotoRepository extends JpaRepository<Photos, Integer>
{

	@Query("SELECT p FROM Photos p WHERE p.albums.id = :albumsId")
	List<Photos> findByAlbumId(@Param("albumsId") Integer albumsId);

}
