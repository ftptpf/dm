package ru.ftptpf.mapper;

import org.springframework.stereotype.Component;
import ru.ftptpf.database.entity.Company;
import ru.ftptpf.dto.CompanyReadDto;

@Component
public class CompanyReadMapper implements Mapper<Company, CompanyReadDto> {

    @Override
    public CompanyReadDto map(Company object) {
        return new CompanyReadDto(
                object.getId(),
                object.getName()
        );
    }
}
