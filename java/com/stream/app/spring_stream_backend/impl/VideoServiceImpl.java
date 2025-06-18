package com.stream.app.spring_stream_backend.impl;
import com.stream.app.spring_stream_backend.entities.Video;
import com.stream.app.spring_stream_backend.repositories.VideoRepository;
import com.stream.app.spring_stream_backend.services.VideoService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;

    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public Video save(Video video, MultipartFile file) {
        // You can add logic to handle the file if needed
        return videoRepository.save(video);
    }

    @Override
    public Video get(String videoId) {
        Optional<Video> video = videoRepository.findById(videoId);
        return video.orElse(null);
    }

    @Override
    public Video getByTitle(String title) {
        Optional<Video> video = videoRepository.findByTitle(title);
        return video.orElse(null);
    }

    @Override
    public List<Video> getAll() {
        return videoRepository.findAll();
    }
}
