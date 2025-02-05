/*
* AMRIT – Accessible Medical Records via Integrated Technology 
* Integrated EHR (Electronic Health Records) Solution 
*
* Copyright (C) "Piramal Swasthya Management and Research Institute" 
*
* This file is part of AMRIT.
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see https://www.gnu.org/licenses/.
*/
package com.iemr.admin.repository.store;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iemr.admin.data.store.M_Facility;

@Repository
public interface MainStoreRepo extends CrudRepository<M_Facility, Integer>{
	
	List<M_Facility> findByProviderServiceMapIDOrderByFacilityName(Integer providerServiceMapID);
    
	@Query("SELECT u FROM M_Facility u WHERE u.providerServiceMapID=:providerServiceMapID AND u.isMainFacility=:isMainFacility AND deleted=false order by u.facilityName")
	ArrayList<M_Facility> getAllMainFacility(@Param("providerServiceMapID")Integer providerServiceMapID,@Param("isMainFacility") Boolean isMainFacility);
	
	
	@Query("SELECT u FROM M_Facility u WHERE u.providerServiceMapID=:providerServiceMapID AND u.isMainFacility=:isMainFacility AND u.mainFacilityID=:mainFacilityID AND deleted=false order by u.facilityName")
	ArrayList<M_Facility> getAllMainFacility(@Param("providerServiceMapID")Integer providerServiceMapID,@Param("isMainFacility") Boolean isMainFacility,
			@Param("mainFacilityID") Integer mainFacilityID);

	@Query("SELECT u FROM M_Facility u WHERE u.providerServiceMapID=:providerServiceMapID AND u.mainFacilityID=:mainFacilityID AND deleted=false order by u.facilityName")
	ArrayList<M_Facility> getChildFacility(@Param("providerServiceMapID")Integer providerServiceMapID,@Param("mainFacilityID") Integer mainFacilityID);
	
	
	ArrayList<M_Facility> findByMainFacilityIDAndDeletedOrderByFacilityName(Integer mainfacID,Boolean deleted);
	
	M_Facility findByFacilityIDAndDeleted(Integer mainfacID,Boolean deleted);

	List<M_Facility> findByFacilityCodeAndProviderServiceMapID(String facilityCode, Integer providerServiceMapID);
	
	M_Facility findByFacilityID(Integer facilityID);
	

}
