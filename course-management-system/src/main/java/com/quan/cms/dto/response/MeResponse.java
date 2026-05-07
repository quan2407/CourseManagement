package com.quan.cms.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeResponse {

    private String username;

    private String role;
}