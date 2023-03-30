package com.berktas.blogApi.model.dto;



import java.time.LocalDateTime;


public record PostDto (Long id, String title, String description,  LocalDateTime createdDateTime, LocalDateTime updatedDateTime
         )  {
}
