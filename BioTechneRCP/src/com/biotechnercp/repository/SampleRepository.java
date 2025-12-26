package com.biotechnercp.repository;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import com.biotechnercp.model.Sample;


public class SampleRepository {

	
	private static SampleRepository sampleRepositoryInstance;
	
	private final Set<Sample> sampleTable;
	private Long sequence;
	
	private SampleRepository() {
		sampleTable = new HashSet<>();
		sequence = 0L;
	}
	
	
	public static SampleRepository getInstance() {
		if (Objects.isNull(sampleRepositoryInstance)) {
			sampleRepositoryInstance = new SampleRepository();
		}
		
		return sampleRepositoryInstance;
	}


	public boolean saveSample(Sample s) {
		if (Objects.isNull(s.getId())) {
			s.setId(sequence++);
		} 
		return sampleTable.add(s);
		
	}
	

	public boolean deleteSample(Sample s) {
		return sampleTable.remove(s);
	}


	public Optional<Sample> getById(Long id) {
		return sampleTable.stream().filter(f -> f.getId().equals(id)).findFirst();
	}

	public Set<Sample> findAll() {
		return sampleTable;
	}
	
	
	
	
}
