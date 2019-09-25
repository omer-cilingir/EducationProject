package com.education.model.dto;

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
public class UserDto extends BaseDto {

    private String username;
    private String email;
    private String profileImageUrl;
    private int credit;
}
