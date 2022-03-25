package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.ColorService;
import com.etiya.rentACar.business.requests.colorRequests.CreateColorRequest;
import com.etiya.rentACar.business.responses.colorResponse.ListColorDto;
import com.etiya.rentACar.core.utilities.ConvertLetter;
import com.etiya.rentACar.core.utilities.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.ColorDao;
import com.etiya.rentACar.entities.concretes.Color;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColorManager implements ColorService {
    private ColorDao colorDao;
    private ModelMapperService modelMapperService;

    public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService) {
        this.colorDao = colorDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public void add(CreateColorRequest createColorRequest) {


        createColorRequest.setName(ConvertLetter.convertLetter(createColorRequest.getName()).toUpperCase());
        if (!colorDao.existsByName(createColorRequest.getName())) {
            Color color = modelMapperService.forRequest().map(createColorRequest, Color.class);
            colorDao.save(color);
        } else {
            throw new RuntimeException("This color already exists.");
        }

    }

    @Override
    public List<ListColorDto> getAll() {
        List<Color> colors = colorDao.findAll();
        List<ListColorDto> response = colors.stream()
                .map(color -> modelMapperService.forDto().map(color, ListColorDto.class))
                .collect(Collectors.toList());
        return response;
    }
}
