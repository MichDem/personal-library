package pl.miku.personallibrary.core.volume.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.miku.personallibrary.core.volume.Volume;
import pl.miku.personallibrary.core.volume.web.VolumeView;

@Component
public class VolumeToVolumeViewConverter implements Converter<Volume, VolumeView> {

    @Override
    public VolumeView convert(Volume volume) {
        return new VolumeView()
                .setId(volume.getId())
                .setIsbn(volume.getIsbn())
                .setVolumeName(volume.getVolumeName())
                .setVolumeNumber(volume.getVolumeNumber());
    }
}
