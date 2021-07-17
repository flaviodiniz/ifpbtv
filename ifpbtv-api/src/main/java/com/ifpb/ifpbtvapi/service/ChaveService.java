package com.ifpb.ifpbtvapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifpb.ifpbtvapi.model.Chaves;
import com.ifpb.ifpbtvapi.repository.ChavesRepository;

@Service
public class ChaveService {
	
	@Autowired
	private ChavesRepository chavesRepository;
	
	public void salvarChaves(List<Chaves> chaves) {
		
		if(chaves != null && !chaves.isEmpty()) chavesRepository.saveAll(chaves);
	}
	
//	public List<Chaves> listarChaves(Long idMidia) {
//		
//		List<Chaves> chaves = chavesRepository.buscarChavesPorMidia(idMidia) != null ? chavesRepository.buscarChavesPorMidia(idMidia) : null;
//		return chaves;
//	}
}
