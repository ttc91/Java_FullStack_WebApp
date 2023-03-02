package fa.training.mock.models.mapper.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fa.training.mock.models.dto.FacultyDto;
import fa.training.mock.models.dto.LocationDto;
import fa.training.mock.models.dto.ProfileDto;
import fa.training.mock.models.dto.StatusDto;
import fa.training.mock.models.dto.UniversityDto;
import fa.training.mock.remotes.entities.Faculty;
import fa.training.mock.models.dto.BudgetDto;
import fa.training.mock.models.dto.DeliveryTypeDto;
import fa.training.mock.remotes.entities.Budget;
import fa.training.mock.remotes.entities.DeliveryType;
import fa.training.mock.remotes.entities.Location;
import fa.training.mock.remotes.entities.Status;
import fa.training.mock.remotes.entities.TraineeCandidateProfile;
import fa.training.mock.remotes.entities.University;
import fa.training.mock.models.dto.SupplierPartnerDto;
import fa.training.mock.remotes.entities.SupplierPartner;

@Configuration
public class ModelMapperConfig {
    
    @Bean
    ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STANDARD);
        
        
        modelMapper.createTypeMap(Location.class, LocationDto.class);
        modelMapper.createTypeMap(TraineeCandidateProfile.class, ProfileDto.class);
        modelMapper.createTypeMap(Status.class, StatusDto.class);
        modelMapper.createTypeMap(University.class, UniversityDto.class);
        modelMapper.createTypeMap(Faculty.class, FacultyDto.class);
        modelMapper.createTypeMap(Budget.class, BudgetDto.class);
        modelMapper.createTypeMap(DeliveryType.class, DeliveryTypeDto.class);
        modelMapper.createTypeMap(SupplierPartner.class, SupplierPartnerDto.class);
    
        


        return modelMapper;
    }

}
    