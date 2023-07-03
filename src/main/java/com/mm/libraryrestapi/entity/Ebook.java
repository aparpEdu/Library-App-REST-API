package com.mm.libraryrestapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ebook extends Book{
    String downloadLink;
    String purchaseLink;

}
