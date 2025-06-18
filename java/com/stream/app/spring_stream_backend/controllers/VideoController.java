package com.stream.app.spring_stream_backend.controllers;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import com.stream.app.spring_stream_backend.entities.Video;
import com.stream.app.spring_stream_backend.services.VideoService;
import com.stream.app.spring_stream_backend.payload.CustomMessage;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/v1/videos")
public class VideoController {
    @Autowired
    private VideoService videoService;

    private VideoController(VideoService videoService) {
        this.videoService = videoService;
    }
    @PostMapping
    public ResponseEntity<?> create(
        @RequestParam("file") MultipartFile file,
        @RequestParam("title") String title,
        @RequestParam("description") String description,
        @RequestParam("genre") String genre){
        
        // Logic to handle video creation
        Video video = new Video();
        video.setTitle(title);
        video.setDescription(description); 
        video.setVideoId(UUID.randomUUID().toString()); // Generate a unique ID for the video
        video.setGenre(genre);
        Video savedVideo = videoService.save(video, file);
        if(savedVideo!=null){
            
            return ResponseEntity.status(HttpStatus.OK).body(video);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(CustomMessage.builder()
                .message("Video upload failed")
                .success(false)
                .build());
        }
        
        //return null;
    }
}
