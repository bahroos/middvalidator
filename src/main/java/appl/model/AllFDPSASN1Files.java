package appl.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "allMIDDFiles")
@ApiModel(description = "All details about the MIDD Files.")
public class AllFDPSASN1Files {

	@ApiModelProperty(notes = "The database generated ID")
	private @Id @GeneratedValue Long id;

	@ApiModelProperty(notes = "The fileName of the MIDD File")
	private String fileName;

	@ApiModelProperty(notes = "The dateTime of the MIDD File")
	private String  dateTime;

	@ApiModelProperty(notes = "The status of the MIDD File")
	private boolean status;

	@ApiModelProperty(notes = "The issues found with the MIDD file")
	private String issues;

	public AllFDPSASN1Files() {
	}
}
