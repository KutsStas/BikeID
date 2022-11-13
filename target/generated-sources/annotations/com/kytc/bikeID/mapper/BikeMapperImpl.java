package com.kytc.bikeID.mapper;

import com.kytc.bikeID.dto.BikeDto;
import com.kytc.bikeID.entity.Bike;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-13T20:26:59+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class BikeMapperImpl implements BikeMapper {

    @Override
    public BikeDto toDto(Bike bike) {
        if ( bike == null ) {
            return null;
        }

        BikeDto bikeDto = new BikeDto();

        bikeDto.setId( bike.getId() );
        bikeDto.setBikeType( bike.getBikeType() );
        bikeDto.setBikeBrand( bike.getBikeBrand() );
        bikeDto.setBikeModel( bike.getBikeModel() );
        bikeDto.setBikeColor( bike.getBikeColor() );
        bikeDto.setFrameSize( bike.getFrameSize() );
        bikeDto.setWheelSize( bike.getWheelSize() );
        bikeDto.setFrameNumber( bike.getFrameNumber() );
        bikeDto.setWarranty( bike.getWarranty() );
        bikeDto.setLegalStatus( bike.getLegalStatus() );

        return bikeDto;
    }

    @Override
    public Bike toEntity(BikeDto dto) {
        if ( dto == null ) {
            return null;
        }

        Bike bike = new Bike();

        bike.setId( dto.getId() );
        bike.setBikeType( dto.getBikeType() );
        bike.setBikeBrand( dto.getBikeBrand() );
        bike.setBikeModel( dto.getBikeModel() );
        bike.setBikeColor( dto.getBikeColor() );
        bike.setFrameSize( dto.getFrameSize() );
        bike.setWheelSize( dto.getWheelSize() );
        bike.setFrameNumber( dto.getFrameNumber() );
        bike.setWarranty( dto.getWarranty() );
        bike.setLegalStatus( dto.getLegalStatus() );

        return bike;
    }

    @Override
    public List<BikeDto> toDtoList(List<Bike> bikes) {
        if ( bikes == null ) {
            return null;
        }

        List<BikeDto> list = new ArrayList<BikeDto>( bikes.size() );
        for ( Bike bike : bikes ) {
            list.add( toDto( bike ) );
        }

        return list;
    }
}
