package com.pratamaycon.muxi.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Terminal {

	@Id
	private Integer logic;

	@Column(nullable = false)
	private String serial;

	@Column(nullable = false)
	private String model;

	private Integer sam;

	private String ptid;

	private Integer plat;

	@Column(nullable = false)
	private String version;

	private Integer mxr;

	private Integer mxf;

	private String verfm;

}