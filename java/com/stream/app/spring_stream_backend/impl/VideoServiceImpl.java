package com.stream.app.spring_stream_backend.impl;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.stream.app.spring_stream_backend.repositories.VideoRepository;
import com.stream.app.spring_stream_backend.entities.Video;
import com.stream.app.spring_stream_backend.services.VideoService;
import java.io.File;
import java.nio.file.Path;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;

@Service
public class VideoServiceImpl implements VideoService {
        @Value("${files.video}")
        private String DIR;
        @Autowired  
        private VideoRepository videoRepository; // Assuming you have a VideoRepository for database operations

        @PostConstruct
        public void init(){
            File file = new File(DIR);
            if(!file.exists()){
                file.mkdir(); // Create the directory if it does not exist
                System.out.println("Directory created: " + DIR);
            }else{
                System.out.println("Directory already exists: " + DIR);
            }
        }

    // Implement the methods from VideoService interface here
    @Override
    public Video save(Video video, MultipartFile file) {
        

        try{
            String filename = file.getOriginalFilename(); //original file name
        String contentType = file.getContentType(); //file content type
        InputStream inputStream = file.getInputStream(); // InputStream for the file content
        
        
        // file path
        String cleanFileName = StringUtils.cleanPath(filename);
        
        // folder path : create
        String cleanFolder = StringUtils.cleanPath(DIR);

        // folder path with filename
        Path path = Paths.get(cleanFolder, cleanFileName); // Create a Path object for the file
        System.out.println(contentType);
        System.out.println(path);

        

        // copy file to the folder
        Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING); // Copy the file to the specified path

        // video metadata
        video.setVideoUrl(path.toString()); // Set the video URL to the file path
        video.setContentType(contentType); // Set the content type of the video

        return videoRepository.save(video); // Save the video metadata to the database
        // Save video metadata to the database
        }catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions appropriately
            return null;
        }
        //return null; 
    }

    @Override
    public Video get(String videoId) {
        // Implementation logic for getting a video by ID
        return null; // Placeholder return statement
    }

    @Override
    public Video getByTitle(String title) {
        // Implementation logic for getting a video by title
        return null; // Placeholder return statement
    }

    @Override
    public List<Video> getAll() {
        // Implementation logic for getting all videos
        return null; // Placeholder return statement
    }
    
}