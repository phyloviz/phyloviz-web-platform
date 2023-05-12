package org.phyloviz.pwp.shared_phylodb.repository.data.phylodb.typing_data;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.data.typing_data.repository.TypingDataDataRepository;
import org.phyloviz.pwp.shared.repository.data.typing_data.repository.specific_data.TypingDataDataRepositorySpecificData;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class TypingDataPhyloDBDataRepository implements TypingDataDataRepository {
    @Override
    public TypingDataDataRepositorySpecificData uploadTypingData(String projectId, String typingDataId, MultipartFile multipartFile) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public String getTypingData(TypingDataDataRepositorySpecificData typingDataDataRepositorySpecificData) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteTypingData(TypingDataDataRepositorySpecificData typingDataDataRepositorySpecificData) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
