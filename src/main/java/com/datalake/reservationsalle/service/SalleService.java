package com.datalake.reservationsalle.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datalake.reservationsalle.domain.Additionalequipement;
import com.datalake.reservationsalle.domain.Equipements;
import com.datalake.reservationsalle.domain.Equipementsreservation;
import com.datalake.reservationsalle.domain.Salle;
import com.datalake.reservationsalle.domain.Sallereservation;
import com.datalake.reservationsalle.repository.AdditionalequipementRepository;
import com.datalake.reservationsalle.repository.EquipementsreservationRepository;
import com.datalake.reservationsalle.repository.SalleRepository;
import com.sun.el.stream.Stream;

/**
 * Service Implementation for managing {@link salle}.
 */
@Service
@Transactional
public class SalleService {
	
	private final Logger log = LoggerFactory.getLogger(EquipementsreservationService.class);

    private final SalleRepository salleRepository;

    private AdditionalequipementRepository additionalequipementRepository;

    public SalleService(SalleRepository salleRepository) {
        this.salleRepository = salleRepository;
    }
    
    /**
     * Get empty salle.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Salle> findsalle(LocalTime datereservation,  int capacity) {
        return salleRepository.findSalle(datereservation, capacity);
    }
    
    @Transactional(readOnly = true)
    public Salle findfinalsalle(int capacity, String meetingType, LocalTime reservationDate) {
    	
    	//get t of salle
    	List<Salle> salles = findsalle(reservationDate, capacity);
    	List<Salle> sallesFiltered = new ArrayList<>();
    	
    	sallesFiltered = salles.stream().collect(Collectors.toList());
     
    	
    	List<String>equipements = new ArrayList<String>();
    	
    	//get all equipements
    	List<Additionalequipement> additionalequipement = additionalequipementRepository.findAll();
		
    
  
    	if(meetingType.equals("VC")) {
    		equipements = Arrays.asList("Ecran", "Pieuvre", "Webcam");
		}
		else
		if(meetingType.equals("SPEC")) {
			equipements = Arrays.asList("Tableau");
			}
		
		else
		if(meetingType.equals("RC")) {
			equipements = Arrays.asList("Tableau", "Ecran", "Pieuvre");
		}
		else
		
			{equipements = Arrays.asList("");}
    	
    	Long idequipementsupp = null;
    	for (String equipement: equipements){
    		
    		for(Salle salle: sallesFiltered) {
    			
    			boolean testequipement = false;
    			boolean testequipementsupp= false;
    			
    			for(Equipements equip: salle.getEquipements()) {
    				//verifier que la salle comporte les équipements nécessaires 
    				if(equip.getName().equals(equipement)) {
    					testequipement = true;
    				}
    				
    				// si la salle ne contient pas le matériel nécessaire il faut vérifier s'il ya un matériel disponible qu'on peut le réserver
    				else {
    					for (Additionalequipement eqipsup: additionalequipement) {
    						if(eqipsup.getName().equals(equip) && eqipsup.getNumber()>0)
    						{
    							idequipementsupp = eqipsup.getId();
    							testequipementsupp= true;
    						}
    						
    					}
    				}
    				
    				if(testequipement == true) {
    					//la salle comporte les équipements nécessaires alors on doit créer une réservation et retourner la salle
    					salleRepository.save(salle);
    					Sallereservation sallereservation = new Sallereservation(reservationDate, salle) ;
    					return salle;
    					
    				}
    				
    				if (testequipementsupp == true) {
    					//la salle ne contient pas les équipements nécessaires alors il faut créer une réservation du matériel et retourner la salle
    					Additionalequipement equipsupp = additionalequipementRepository.findById(idequipementsupp).get();
    					equipsupp.setNumber(equipsupp.getNumber()-1);
    					additionalequipementRepository.save(equipsupp);
    					salleRepository.save(salle);
    					Equipementsreservation equipementsreservation = new Equipementsreservation(reservationDate, equipsupp);
    					return salle;
    					
    				}
    			}
    		}
    		
    	}
    	return null;
    	
    	
    }
		
			
    	
      
 


}
