package pl.miku.personallibrary.core.volume;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.miku.personallibrary.core.volume.converter.VolumeToVolumeViewConverter;
import pl.miku.personallibrary.core.volume.web.VolumeRequest;
import pl.miku.personallibrary.core.volume.web.VolumeView;
import pl.miku.personallibrary.error.EntityNotFoundException;
import pl.miku.personallibrary.util.MessageUtil;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class VolumeService {
    private final VolumeRepository volumeRepository;
    private final MessageUtil messageUtil;
    private final VolumeToVolumeViewConverter volumeToVolumeViewConverter;

    public VolumeService(VolumeRepository volumeRepository, MessageUtil messageUtil, VolumeToVolumeViewConverter volumeToVolumeViewConverter) {
        this.volumeRepository = volumeRepository;
        this.messageUtil = messageUtil;
        this.volumeToVolumeViewConverter = volumeToVolumeViewConverter;
    }

    public Volume findOrThrow(Long id) {
        return volumeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("volume.NotFound", id)));
    }

    public VolumeView getVolume(Long id) {
        var volume = findOrThrow(id);
        return volumeToVolumeViewConverter.convert(volume);
    }

    public VolumeView create(VolumeRequest request) {
        var volume = new Volume();
        prepare(volume, request);
        var save = volumeRepository.save(volume);
        return volumeToVolumeViewConverter.convert(save);
    }

    public Page<VolumeView> findAllVolumes(Pageable pageable) {
        var volumes = volumeRepository.findAll(pageable);
        var views = new ArrayList<VolumeView>();
        volumes.forEach(volume -> {
            var view = volumeToVolumeViewConverter.convert(volume);
            views.add(view);
        });
        return new PageImpl<>(views, pageable, volumes.getTotalElements());
    }

    @Transactional
    public void delete(Long id) {
        try {
            volumeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException(messageUtil.getMessage("volume.NotFound", id));
        }
    }

    public VolumeView update(Volume volume, VolumeRequest request) {
        var newVolume = prepare(volume, request);
        var saved = volumeRepository.save(newVolume);
        return volumeToVolumeViewConverter.convert(saved);
    }

    private Volume prepare(Volume volume, VolumeRequest request) {
        //TODO
        return volume;
    }
}
