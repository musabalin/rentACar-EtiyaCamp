package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.BrandService;
import com.etiya.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.etiya.rentACar.business.responses.brandResponses.ListBrandDto;
import com.etiya.rentACar.core.utilities.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.BrandDao;
import com.etiya.rentACar.entities.concretes.Brand;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class BrandManager implements BrandService {

    private BrandDao brandDao;
    private ModelMapperService modelMapperService;


    public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {

        this.brandDao = brandDao;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public void add(CreateBrandRequest createBrandRequest) {


        createBrandRequest.setName(createBrandRequest.getName().toUpperCase());
        if (!this.brandDao.existsBrandByName(createBrandRequest.getName().toUpperCase())) {
            Brand brand = modelMapperService.forRequest()
                    .map(createBrandRequest, Brand.class);
            this.brandDao.save(brand);
        } else {
            throw new RuntimeException("Bu marka var");

        }
    }


    @Override
    public List<ListBrandDto> getAll() {

        List<Brand> brands = this.brandDao.findAll();
        List<ListBrandDto> response = brands.stream()
                .map(brand -> modelMapperService.forDto().map(brand, ListBrandDto.class))
                .collect(Collectors.toList());
        return response;
    }


}
