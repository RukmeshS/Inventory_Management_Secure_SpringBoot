package com.ty.ims.inventory_prject_boot.dto;

import com.ty.ims.inventory_prject_boot.dto.EmailDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {

	private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
    
   private Supplier supplier;
}
