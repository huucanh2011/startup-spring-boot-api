package vn.luvina.startup.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.luvina.startup.dto.msize.SizeResponseDto;
import vn.luvina.startup.exception.ServiceRuntimeException;
import vn.luvina.startup.model.MSize;
import vn.luvina.startup.repository.SizeRepository;
import vn.luvina.startup.service.SizeService;
import vn.luvina.startup.util.StartupMessages;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {
  private final SizeRepository sizeRepository;
  private final ModelMapper modelMapper;

  @Override
  public List<SizeResponseDto> getAll() {
    List<MSize> listSize = sizeRepository.findAll();
    if(listSize.isEmpty()){
      throw new ServiceRuntimeException(HttpStatus.NO_CONTENT, StartupMessages.ERR_SIZE_002);
    }
    return listSize.stream().map(size -> modelMapper.map(size, SizeResponseDto.class))
            .collect(Collectors.toList());
  }

  @Override
  public SizeResponseDto getSize(String name) {
    MSize size = sizeRepository.getByName(name);
    if(size == null){
      throw new ServiceRuntimeException(HttpStatus.NOT_FOUND, StartupMessages.ERR_SIZE_001);
    }
    return modelMapper.map(size, SizeResponseDto.class);
  }
  
  
}
