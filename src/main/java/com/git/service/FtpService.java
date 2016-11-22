package com.git.service;

import java.util.List;

import com.git.domain.FtpEntity;

public interface FtpService {

	void add(FtpEntity ftpEntity);

	List<FtpEntity> getAll();

	void delete(FtpEntity ftpEntity);

}
