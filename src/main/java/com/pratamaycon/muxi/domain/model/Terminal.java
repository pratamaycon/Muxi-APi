package com.pratamaycon.muxi.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
public class Terminal {

	@Id
	@Column(updatable = false)
	@JsonProperty("logic")
	private Integer logic;

	@JsonProperty("serial")
	private String serial;

	@JsonProperty("model")
	private String model;

	@JsonProperty("sam")
	private Integer sam;

	@JsonProperty("ptid")
	private String ptid;

	@JsonProperty("plat")
	private Integer plat;

	@JsonProperty("version")
	private String version;

	@JsonProperty("mxr")
	private Integer mxr;

	@JsonProperty("mxf")
	private Integer mxf;

	@JsonProperty("VERFM")
	private String verfm;

}