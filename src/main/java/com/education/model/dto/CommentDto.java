package com.education.model.dto;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.education.model.dto.base.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto extends BaseDto {

    @Size(max = 50)
    private String content;

    @Size(max = 50)
    private String fileUrl;

    @ManyToOne
    private QuestionDto targetQuestion;

    @ManyToOne
    private UserDto sourceUser;
}
