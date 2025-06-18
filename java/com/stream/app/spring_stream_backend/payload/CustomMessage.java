package com.stream.app.spring_stream_backend.payload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;    
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomMessage {
    private String message;
    private boolean success;
}
