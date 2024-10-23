package com.dollop.app.entity;

import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "photo_table")
public class Photos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer photoId;
	@NotBlank(message = "Photo Name is required")
    private String photoName;
	@NotBlank(message = "Photo Type is required")
    private String photoType;
	@NotBlank(message = "Photo Thumbnail is required")
    private String photoThumbNail;
	
    private String imagePath;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
    private Integer createdBy;
    private Integer updatedBy;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "albumsId")
    private Albums albums;
    
    private boolean isActive = true;
}
