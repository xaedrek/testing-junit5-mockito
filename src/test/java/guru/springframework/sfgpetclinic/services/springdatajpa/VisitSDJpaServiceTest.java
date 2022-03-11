package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;
    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    VisitSDJpaService service;

    @Test
    void findAll() {
        //given
        Set<Visit> visits = new HashSet<>(Arrays.asList(new Visit(1L),new Visit(2L)));
        given(visitRepository.findAll()).willReturn(visits);
        //when(visitRepository.findAll()).thenReturn(visits);  no BDD version
        //when
        Set<Visit> returnedVisits = service.findAll();
        //then
        then(visitRepository).should().findAll();
//        verify(visitRepository).findAll();
//        assertTrue(returnedVisits.size()==2);
    }

    @DisplayName("find by id with mocked response")
    @Test
    void findById() {
        //given
        Visit visit = new Visit();
        given(visitRepository.findById(1L)).willReturn(Optional.of(visit));
        //when(visitRepository.findById(1L)).thenReturn(Optional.of(visit)); no BDD version
        //when
        Visit foundVisit = service.findById(1L);
        //then
        then(visitRepository).should().findById(anyLong());
        assertNotNull(foundVisit);
//        verify(visitRepository).findById(anyLong()); no BDD version
    }

    @Test
    void save() {
        //given
        Visit visit = new Visit();
        given(visitRepository.save(any(Visit.class))).willReturn(visit);
        //when(visitRepository.save(any(Visit.class))).thenReturn(visit);  no BDD version
        //when
        Visit returnedVisit = service.save(visit);
        //then
        //verify(visitRepository).save(any(Visit.class)); no BDD version
        then(visitRepository).should().save(any(Visit.class));
        assertNotNull(returnedVisit);

    }

    @Test
    void delete() {
        //given
        Visit visit = new Visit();
        //when
        service.delete(visit);
        //then
        then(visitRepository).should().delete(any(Visit.class));
        //verify(visitRepository).delete(any(Visit.class));

    }

    @Test
    void deleteById() {
        //give
        Long l = 1L;
        //when
        service.deleteById(l);
        //then
        then(visitRepository).should().deleteById(anyLong());
//        verify(visitRepository).deleteById(anyLong());
    }

    @DisplayName("standard mockito test do throw")
    @Test
    void testDoThrow(){
        doThrow(new RuntimeException("boom")).when(specialtyRepository).delete(any());
        assertThrows(RuntimeException.class, () -> specialtyRepository.delete(new Speciality()));
        verify(specialtyRepository).delete(any());
    }

    @DisplayName("bdd test find by id throws")
    @Test
    void testFindByIdThrows(){
        given(specialtyRepository.findById(1L)).willThrow(new RuntimeException("kaboom"));
        assertThrows(RuntimeException.class, () -> specialtyRepository.findById(1L));
        //then
        then(specialtyRepository).should().findById(1L);
    }

    @Test
    void testDeleteBDD(){
        willThrow(new RuntimeException("boom")).given(specialtyRepository).delete(any());
        assertThrows(RuntimeException.class,()-> specialtyRepository.delete(new Speciality()));
        then(specialtyRepository).should().delete(any());
    }
}