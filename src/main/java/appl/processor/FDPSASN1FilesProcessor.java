package appl.processor;

import appl.model.AllFDPSASN1Files;
import appl.model.FDPSASN1Repository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class FDPSASN1FilesProcessor {

    public List<AllFDPSASN1Files> listFDPSASN1Files(String dir, FDPSASN1Repository repository) throws IOException {

        List<AllFDPSASN1Files> listFDPSASN1Files = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir))) {
            for (Path path : stream) {
                if (!Files.isDirectory(path)) {
                    AllFDPSASN1Files allMIDDFiles = new AllFDPSASN1Files();
                    allMIDDFiles.setFileName(path.toFile().getAbsolutePath());
                    allMIDDFiles.setStatus(true);
                    allMIDDFiles.setIssues("");
                    repository.save(allMIDDFiles);
                }
            }
        }

        return listFDPSASN1Files;
    }
}