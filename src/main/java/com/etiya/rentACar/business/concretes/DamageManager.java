package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.DamageService;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.etiya.rentACar.business.requests.damageRequests.CreateCarDamageRequest;
import com.etiya.rentACar.business.requests.damageRequests.DeleteCarDamageRequest;
import com.etiya.rentACar.business.responses.damageReponses.CarDamageDto;
import com.etiya.rentACar.business.responses.damageReponses.ListDamageDto;
import com.etiya.rentACar.core.utilities.ModelMapperManager;
import com.etiya.rentACar.core.utilities.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.DamageDao;
import com.etiya.rentACar.entities.concretes.Damage;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DamageManager implements DamageService {

    DamageDao damageDao;
    ModelMapperService modelMapperService;

    public DamageManager(DamageDao damageDao, ModelMapperManager modelMapperService) {
        this.damageDao = damageDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public CarDamageDto getById(int id) {
        Damage result = damageDao.getById(id);
        CarDamageDto response = modelMapperService.forRequest().map(result, CarDamageDto.class);
        return response;
    }

    @Override
    public void add(CreateCarDamageRequest createDamageRequest) {
        Damage damage = modelMapperService.forRequest()
                .map(createDamageRequest, Damage.class);
        damageDao.save(damage);

    }

    @Override
    public void update(UpdateCarRequest updateCarRequest) {

    }

    @Override
    public void delete(DeleteCarDamageRequest deleteCarDamageRequest) {

    }

    @Override
    public List<ListDamageDto> getAll() {
        List<Damage> damages = damageDao.findAll();
        List<ListDamageDto> response = damages.stream()
                .map(damage -> modelMapperService.forDto().map(damage, ListDamageDto.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public List<ListDamageDto> getByCarId(int id) {
       /* List<Damage> damages = damageDao.findAll();
        List<ListDamageDto> response = damages.stream()
                .map(damage -> modelMapperService.forDto().map(damage, ListDamageDto.class))
                .filter(listDamageDto -> listDamageDto.getCarId()==id)
                .collect(Collectors.toList());
        */
        List<Damage> damages = this.damageDao.getByCarId(id);
        List<ListDamageDto> response = damages.stream().map(damage -> this.modelMapperService.forDto()
                        .map(damage, ListDamageDto.class))
                .collect(Collectors.toList());


        return response;
    }

    @Override
    public List<ListDamageDto> getAllPaged(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        List<Damage> damages = damageDao.findAll(pageable).getContent();
        List<ListDamageDto> response = damages.stream()
                .map(damage -> modelMapperService.forDto().map(damage, ListDamageDto.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public List<ListDamageDto> getAllSorted(String option, String field) {
        Sort sort = Sort.by(Sort.Direction.valueOf(option), field);
        List<Damage> damages = damageDao.findAll(sort);
        List<ListDamageDto> response = damages.stream()
                .map(damage -> modelMapperService.forDto().map(damage, ListDamageDto.class))
                .collect(Collectors.toList());
        return response;
    }
}
