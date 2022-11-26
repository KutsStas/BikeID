package com.kytc.bikeID.mapper;

import com.kytc.bikeID.dto.TechnicalPassportDto;
import com.kytc.bikeID.entity.TechnicalPassport;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-26T17:30:14+0200",
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
        technicalPassportDto.setBikeName( technicalPassport.getBikeName() );
        technicalPassportDto.setServiceWork( technicalPassport.getServiceWork() );
        technicalPassportDto.setWarrantyWork( technicalPassport.getWarrantyWork() );
        technicalPassportDto.setWorkShop( technicalPassport.getWorkShop() );
        technicalPassportDto.setClientName( technicalPassport.getClientName() );
        technicalPassportDto.setServicemenName( technicalPassport.getServicemenName() );
        technicalPassportDto.setReplacementParts( technicalPassport.getReplacementParts() );
        technicalPassportDto.setWorkPrise( technicalPassport.getWorkPrise() );
        technicalPassportDto.setTechnicalStatus( technicalPassport.getTechnicalStatus() );
        technicalPassportDto.setServiceMenComments( technicalPassport.getServiceMenComments() );

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
        technicalPassport.setBikeName( dto.getBikeName() );
        technicalPassport.setServiceWork( dto.getServiceWork() );
        technicalPassport.setWarrantyWork( dto.getWarrantyWork() );
        technicalPassport.setWorkShop( dto.getWorkShop() );
        technicalPassport.setClientName( dto.getClientName() );
        technicalPassport.setServicemenName( dto.getServicemenName() );
        technicalPassport.setReplacementParts( dto.getReplacementParts() );
        technicalPassport.setWorkPrise( dto.getWorkPrise() );
        technicalPassport.setTechnicalStatus( dto.getTechnicalStatus() );
        technicalPassport.setServiceMenComments( dto.getServiceMenComments() );

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
