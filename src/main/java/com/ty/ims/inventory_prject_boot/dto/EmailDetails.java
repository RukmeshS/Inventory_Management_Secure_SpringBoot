package com.ty.ims.inventory_prject_boot.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {
	@Email
	private String recipient;
    private String msgBody;
    @NotNull
    private String subject;
    private String attachment;
    
   private Supplier supplier;
}
