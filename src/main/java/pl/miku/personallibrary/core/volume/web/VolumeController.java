package pl.miku.personallibrary.core.volume.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.miku.personallibrary.core.volume.VolumeService;

import javax.validation.Valid;

@RestController
@RequestMapping("/volume")
public class VolumeController {
    private final VolumeService service;

    public VolumeController(VolumeService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public VolumeView getVolume(@PathVariable Long id) {
        return service.getVolume(id);
    }

    @GetMapping
    @ResponseBody
    public Page<VolumeView> getAllVolumes(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC)Pageable pageable
    ) {
        return service.findAllVolumes(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public VolumeView create(@RequestBody @Valid VolumeRequest request) {
        return service.create(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public VolumeView updateVolume(
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid VolumeRequest request
    ) {
        var volume = service.findOrThrow(id);
        return service.update(volume, request);
    }
}
