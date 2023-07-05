package com.mm.libraryrestapi.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserCloudHistoryResponse {
    private List<UserCloudHistoryDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
