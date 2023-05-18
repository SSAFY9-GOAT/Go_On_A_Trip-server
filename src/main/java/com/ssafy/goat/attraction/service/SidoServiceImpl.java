package com.ssafy.goat.attraction.service;

import com.ssafy.goat.attraction.Sido;
import com.ssafy.goat.attraction.dto.SidoDto;
import com.ssafy.goat.attraction.repository.SidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SidoServiceImpl implements SidoService{

    private final SidoRepository sidoRepository;
    @Override
    public List<SidoDto> searchSidos() {
        List<Sido> sidos = sidoRepository.findAll();
        return sidos.stream()
                .map(sido -> new SidoDto(sido.getCode(), sido.getName()))
                .collect(Collectors.toList());
    }
}
