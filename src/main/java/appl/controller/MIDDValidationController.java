package appl.controller;

import appl.model.AllFDPSASN1Files;
import appl.model.FDPSASN1Repository;
import appl.processor.FDPSASN1FilesProcessor;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
public class MIDDValidationController {

    private final FDPSASN1Repository repository;

    @Autowired
    private FDPSASN1FilesProcessor FDPSASN1FilesProcessor;

    MIDDValidationController(FDPSASN1Repository repository) {
        this.repository = repository;
    }

    /**
     * GET  /healthcheck : Healthcheck endpoint.
     */
    @ApiOperation(value = "API healthcheck", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Application is up and running", response = String.class),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping(name = "/healthcheck", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> healthcheck() {
        return new ResponseEntity<>("Hi!!! My healthcheck works.", HttpStatus.OK);
    }

    @ApiOperation(value = "API For populating all FDPS ASN.1 files ", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully populated"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping(value = "/populateAllFDPSASN1Files", consumes = "application/json", produces = "application/json")
    public String allFDPSASN1Files() {
        repository.deleteAll();
        try {
            List<AllFDPSASN1Files> listAllFDPSASN1Files =
                    FDPSASN1FilesProcessor.listFDPSASN1Files("/vobs/TE1_MADAP_DOC/Code/ASN.1/TFdps", repository);
            listAllFDPSASN1Files = FDPSASN1FilesProcessor.listFDPSASN1Files("/vobs/TE1_MADAP_DOC/Code/ASN.1/TAGDL", repository);
            listAllFDPSASN1Files = FDPSASN1FilesProcessor.listFDPSASN1Files("/vobs/TE1_MADAP_DOC/Code/ASN.1/TPFS", repository);
            listAllFDPSASN1Files = FDPSASN1FilesProcessor.listFDPSASN1Files("/vobs/TE1_MADAP_DOC/Code/ASN.1/TGeneral", repository);
            listAllFDPSASN1Files = FDPSASN1FilesProcessor.listFDPSASN1Files("/vobs/TE1_MADAP_DOC/Code/ASN.1/TCpsr", repository);
            listAllFDPSASN1Files = FDPSASN1FilesProcessor.listFDPSASN1Files("/vobs/TE1_MADAP_DOC/Code/ASN.1/TSimKernel", repository);
        } catch (IOException ex) {
            //To be changed to graceful handling of exception
        }

        return "" +  repository.count() + " FDPS ASN1 files are populated in repository";
    }
}
