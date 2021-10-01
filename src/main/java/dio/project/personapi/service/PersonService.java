package dio.project.personapi.service;

import dio.project.personapi.dto.request.PersonDTO;
import dio.project.personapi.dto.response.MessageResponseDTO;
import dio.project.personapi.entity.Person;
import dio.project.personapi.mapper.PersonMapper;
import dio.project.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person build = personMapper.toModel(personDTO);
        Person personSave = personRepository.save(build);

        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + personSave.getId())
                .build();
    }

    public List<PersonDTO> getAllPerson() {
        List<Person> all = personRepository.findAll();
        return all.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }
}
