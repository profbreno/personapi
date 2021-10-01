package dio.project.personapi.service;

import dio.project.personapi.dto.response.MessageResponseDTO;
import dio.project.personapi.entity.Person;
import dio.project.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(Person person){
        Person personSave = personRepository.save(person);

        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + personSave.getId())
                .build();
    }

    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }
}
