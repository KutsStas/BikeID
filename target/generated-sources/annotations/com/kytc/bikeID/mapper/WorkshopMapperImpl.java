package com.kytc.bikeID.mapper;

import com.kytc.bikeID.dto.WorkshopDto;
import com.kytc.bikeID.entity.Workshop;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-05T17:48:16+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class WorkshopMapperImpl implements WorkshopMapper {

    @Override
    public WorkshopDto toDto(Workshop workshop) {
        if ( workshop == null ) {
            return null;
        }

        WorkshopDto workshopDto = new WorkshopDto();

        workshopDto.setId( workshop.getId() );
        workshopDto.setWorkshopName( workshop.getWorkshopName() );
        workshopDto.setAddress( workshop.getAddress() );
        workshopDto.setWebsite( workshop.getWebsite() );
        workshopDto.setWorkshopPhoneNumber( workshop.getWorkshopPhoneNumber() );
        workshopDto.setSalesDate( workshop.getSalesDate() );
        workshopDto.setWorkshopRating( workshop.getWorkshopRating() );
        workshopDto.setWorkshopReviews( workshop.getWorkshopReviews() );

        return workshopDto;
    }

    @Override
    public Workshop toWorkshop(WorkshopDto workshopDto) {
        if ( workshopDto == null ) {
            return null;
        }

        Workshop workshop = new Workshop();

        workshop.setId( workshopDto.getId() );
        workshop.setWorkshopName( workshopDto.getWorkshopName() );
        workshop.setAddress( workshopDto.getAddress() );
        workshop.setWebsite( workshopDto.getWebsite() );
        workshop.setWorkshopPhoneNumber( workshopDto.getWorkshopPhoneNumber() );
        workshop.setSalesDate( workshopDto.getSalesDate() );
        workshop.setWorkshopRating( workshopDto.getWorkshopRating() );
        workshop.setWorkshopReviews( workshopDto.getWorkshopReviews() );

        return workshop;
    }
}
