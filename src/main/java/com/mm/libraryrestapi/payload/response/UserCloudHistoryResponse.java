package com.mm.libraryrestapi.payload.response;

import com.mm.libraryrestapi.payload.dtos.UserCloudHistoryDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserCloudHistoryResponse extends GeneralResponse {
    private List<UserCloudHistoryDto> content;
}
