package org.phyloviz.pwp.shared_phylodb.repository.data.phylodb.typing_data;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.data.typing_data.repository.TypingDataDataRepository;
import org.phyloviz.pwp.shared.repository.data.typing_data.repository.specific_data.TypingDataDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.typing_data.repository.specific_data.TypingDataPhyloDBDataRepositorySpecificData;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.GetTypingDataProfilesOutput;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.GetTypingDataSchemaOutput;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.TypingDataProfile;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import pt.ist.meic.phylodb.typing.dataset.model.Dataset;
import pt.ist.meic.phylodb.typing.profile.ProfileRepository;
import pt.ist.meic.phylodb.typing.profile.model.Profile;
import pt.ist.meic.phylodb.typing.schema.SchemaRepository;
import pt.ist.meic.phylodb.typing.schema.model.Schema;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TypingDataPhyloDBDataRepository implements TypingDataDataRepository {
    private final ProfileRepository profileRepository;
    private final SchemaRepository schemaRepository;

    @Override
    public TypingDataDataRepositorySpecificData uploadTypingData(String projectId, String typingDataId, MultipartFile multipartFile) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public String downloadTypingData(TypingDataDataRepositorySpecificData typingDataDataRepositorySpecificData) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public GetTypingDataSchemaOutput getTypingDataSchema(TypingDataDataRepositorySpecificData typingDataDataRepositorySpecificData) {
        TypingDataPhyloDBDataRepositorySpecificData repositorySpecificData =
                (TypingDataPhyloDBDataRepositorySpecificData) typingDataDataRepositorySpecificData;

        Schema schema = schemaRepository.find(new Dataset.PrimaryKey(
                repositorySpecificData.getProjectId(),
                repositorySpecificData.getDatasetIds().get(0))
        ).orElseThrow(() -> new RuntimeException("Schema not found in PhyloDB"));

        return new GetTypingDataSchemaOutput(
                schema.getType().getName(),
                schema.getLociIds()
        );
    }

    @Override
    public GetTypingDataProfilesOutput getTypingDataProfiles(TypingDataDataRepositorySpecificData typingDataDataRepositorySpecificData, int limit, int offset) {
        TypingDataPhyloDBDataRepositorySpecificData repositorySpecificData =
                (TypingDataPhyloDBDataRepositorySpecificData) typingDataDataRepositorySpecificData;

        List<Profile> profiles = profileRepository.findAll(
                offset,
                limit,
                repositorySpecificData.getProjectId(),
                repositorySpecificData.getDatasetIds().get(0)
        ).orElseThrow(() -> new RuntimeException("Profiles not found in PhyloDB"));

        return new GetTypingDataProfilesOutput(
                profiles.stream().map(profile -> new TypingDataProfile(
                                profile.getPrimaryKey().getId(),
                                profile.getAllelesReferences().stream().map(alleleReference ->
                                        alleleReference.getPrimaryKey().getId()
                                ).toList()
                        )
                ).toList(),
                profiles.size()
        );
    }

    @Override
    public void deleteTypingData(TypingDataDataRepositorySpecificData typingDataDataRepositorySpecificData) {
        //        throw new UnsupportedOperationException("Not implemented yet."); TODO: implement
    }
}
