package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.abstracts.CityService;
import com.etiya.rentACar.business.constants.messages.BusinessMessages;
import com.etiya.rentACar.business.requests.cityRequests.CreateCityRequest;
import com.etiya.rentACar.business.requests.cityRequests.DeleteCityRequest;
import com.etiya.rentACar.business.requests.cityRequests.UpdateCityRequest;
import com.etiya.rentACar.business.responses.cityResponses.ListCityDto;
import com.etiya.rentACar.core.utilities.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.CityDao;
import com.etiya.rentACar.entities.concretes.City;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityManager implements CityService {

    private CityDao cityDao;
    private ModelMapperService modelMapperService;

    public CityManager(CityDao cityDao, ModelMapperService modelMapperService, CarService carService) {
        this.cityDao = cityDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateCityRequest createCityRequest) {
        City city = modelMapperService.forRequest().map(createCityRequest, City.class);
        cityDao.save(city);
        return new SuccessResult(BusinessMessages.CityMessages.CITY_ADD);
    }

    @Override
    public Result delete(DeleteCityRequest deleteCityRequest) {
        cityDao.deleteById(deleteCityRequest.getId());
        return new SuccessResult(BusinessMessages.CityMessages.CITY_DELETE);
    }

    @Override
    public Result update(UpdateCityRequest updateCityRequest) {

        City city = modelMapperService.forRequest().map(updateCityRequest, City.class);
        cityDao.save(city);
        return new SuccessResult(BusinessMessages.CityMessages.CITY_UPDATE);
    }

    @Override
    public DataResult<List<ListCityDto>> getAll() {
        List<City> result = cityDao.findAll();
        List<ListCityDto> response = result.stream()
                .map(city -> modelMapperService.forDto().map(city, ListCityDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListCityDto>>(response);
    }
}
