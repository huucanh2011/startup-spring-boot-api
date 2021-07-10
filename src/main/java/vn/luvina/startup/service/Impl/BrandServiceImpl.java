package vn.luvina.startup.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.luvina.startup.dto.mbrand.BrandResponseDto;
import vn.luvina.startup.exception.ServiceRuntimeException;
import vn.luvina.startup.model.MBrand;
import vn.luvina.startup.repository.BrandRepository;
import vn.luvina.startup.service.BrandService;
import vn.luvina.startup.util.StartupMessages;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService{
  private final BrandRepository brandRepository;
  private final ModelMapper modelMapper;
  @Override
  public List<BrandResponseDto> getAll() {
    List<MBrand> listBrand = brandRepository.findAll();
    if(listBrand.isEmpty()){
      throw new ServiceRuntimeException(HttpStatus.NO_CONTENT, StartupMessages.ERR_BRAND_002);
    }
    return listBrand.stream().map(brand -> modelMapper.map(brand, BrandResponseDto.class))
            .collect(Collectors.toList());
  }
  @Override
  public BrandResponseDto getBrand(String name) {
    MBrand brand = brandRepository.getByName(name);
    if(brand == null){
      throw new ServiceRuntimeException(HttpStatus.NOT_FOUND, StartupMessages.ERR_BRAND_001);
    }
    return modelMapper.map(brand, BrandResponseDto.class);
  }

  
}
