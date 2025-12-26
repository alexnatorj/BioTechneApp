package com.biotechnercp.service;

import java.util.Objects;


import com.biotechnercp.exception.InvalidInputException;
import com.biotechnercp.exception.SampleRequiredException;
import com.biotechnercp.model.Sample;
import com.biotechnercp.repository.SampleRepository;

public class SampleService {

	private static final int MAX_LENGTH_DESCRIPTION = 150;
	
	private final SampleRepository sampleRepository = SampleRepository.getInstance();
	
	public SampleService() {
	}
	
	
	public void saveSample(Sample sample) {		
		validateSample(sample);
		sampleRepository.saveSample(sample);
	}
	
	public void updateSample(Sample sample) {		
		// just need the validation
		validateSample(sample);
	}
	
	
	public void deleteSample(Sample sample) {
		if (Objects.isNull(sample)) {
			throw new SampleRequiredException();
		}
		sampleRepository.deleteSample(sample);
		
	}
	
	
	private void validateSample(Sample sample) {
		if (Objects.isNull(sample)) {
			throw new SampleRequiredException();
		}
		if (sample.getName() == null || sample.getName().length() == 0) {
			throw new InvalidInputException("Name");
		}
		if (sample.getDescription() == null 
				|| sample.getDescription().length() == 0
				|| sample.getDescription().length() >  MAX_LENGTH_DESCRIPTION) {
			throw new InvalidInputException("Description");
		}
	}
	
}
