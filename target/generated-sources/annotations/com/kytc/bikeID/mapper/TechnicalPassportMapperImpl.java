package com.kytc.bikeID.mapper;

import com.kytc.bikeID.dto.TechnicalPassportDto;
import com.kytc.bikeID.entity.TechnicalPassport;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-28T17:04:12+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class TechnicalPassportMapperImpl implements TechnicalPassportMapper {

    @Override
    public TechnicalPassportDto toDto(TechnicalPassport technicalPassport) {
        if ( technicalPassport == null ) {
            return null;
        }

        TechnicalPassportDto technicalPassportDto = new TechnicalPassportDto();

        technicalPassportDto.setId( technicalPassport.getId() );
        technicalPassportDto.setVisitDate( technicalPassport.getVisitDate() );
        technicalPassportDto.setWarrantyWork( technicalPassport.getWarrantyWork() );

        return technicalPassportDto;
    }

    @Override
    public TechnicalPassport toTechnicalPassport(TechnicalPassportDto dto) {
        if ( dto == null ) {
            return null;
        }

        TechnicalPassport technicalPassport = new TechnicalPassport();

        technicalPassport.setId( dto.getId() );
        technicalPassport.setVisitDate( dto.getVisitDate() );
        technicalPassport.setWarrantyWork( dto.getWarrantyWork() );

        return technicalPassport;
    }

    @Override
    public List<TechnicalPassportDto> toDosList(List<TechnicalPassport> passportList) {
        if ( passportList == null ) {
            return null;
        }

        List<TechnicalPassportDto> list = new ArrayList<TechnicalPassportDto>( passportList.size() );
        for ( TechnicalPassport technicalPassport : passportList ) {
            list.add( toDto( technicalPassport ) );
        }

        return list;
    }
}
