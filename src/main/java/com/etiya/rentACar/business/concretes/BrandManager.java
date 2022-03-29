package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.BrandService;
import com.etiya.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.etiya.rentACar.business.responses.brandResponses.ListBrandDto;
import com.etiya.rentACar.core.utilities.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.BrandDao;
import com.etiya.rentACar.entities.concretes.Brand;
import org.springframework.stereotype.Service;

import java.util.List;
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


        /*String colorName = ConvertLetter.convertLetter(createBrandRequest.getName().toUpperCase());
        createBrandRequest.setName(colorName);*/
        checkIfIsBrandName(createBrandRequest.getName());
        Brand brand = modelMapperService.forRequest()
                .map(createBrandRequest, Brand.class);
        this.brandDao.save(brand);
    }


    @Override
    public List<ListBrandDto> getAll() {

        List<Brand> brands = this.brandDao.findAll();
        List<ListBrandDto> response = brands.stream()
                .map(brand -> modelMapperService.forDto().map(brand, ListBrandDto.class))
                .collect(Collectors.toList());
        return response;
    }

    private void checkIfIsBrandName(String brandName) {

        if (this.brandDao.existsBrandByNameIgnoreCase(brandName)) {
            throw new RuntimeException("This brand already exist.");
        }

    }


}
