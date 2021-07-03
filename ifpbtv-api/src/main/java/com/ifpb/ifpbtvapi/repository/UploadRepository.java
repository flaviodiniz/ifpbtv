package com.ifpb.ifpbtvapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifpb.ifpbtvapi.model.Upload;

@Repository
public interface UploadRepository extends JpaRepository<Upload, Long>{

}
