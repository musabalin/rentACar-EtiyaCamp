package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.ColorService;
import com.etiya.rentACar.business.constants.messages.BusinessMessages;
import com.etiya.rentACar.business.requests.colorRequests.CreateColorRequest;
import com.etiya.rentACar.business.responses.colorResponse.ListColorDto;
import com.etiya.rentACar.core.crossCuttingConserns.exceptionHandling.BusinessException;
import com.etiya.rentACar.core.utilities.modelMapperService.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
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
    public Result add(CreateColorRequest createColorRequest) {

        checkIfIsColorName(createColorRequest.getName());
        Color color = modelMapperService.forRequest().map(createColorRequest, Color.class);
        colorDao.save(color);
        return new SuccessResult(BusinessMessages.ColorMessages.COLOR_ADD);


    }

    @Override
    public DataResult<List<ListColorDto>> getAll() {
        List<Color> colors = colorDao.findAll();
        List<ListColorDto> response = colors.stream()
                .map(color -> modelMapperService.forDto().map(color, ListColorDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListColorDto>>(response);
    }

    private void checkIfIsColorName(String colorName) {

        if (this.colorDao.existsByNameIgnoreCase(colorName)) {
            throw new BusinessException("This color already exist.");
        }

    }

}
