package com.git.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.git.domain.FtpEntity;
import com.git.mapper.FtpMapper;
import com.git.service.FtpService;

@Service
public class FtpServiceImpl implements FtpService {
	@Autowired
	private FtpMapper ftpMapper;
	
	@Override
	public void add(FtpEntity ftpEntity) {
		this.ftpMapper.insert(ftpEntity);
	}

	@Override
	public List<FtpEntity> getAll() {
		return this.ftpMapper.select(new FtpEntity());
	}

	@Override
	public void delete(FtpEntity ftpEntity) {
		this.ftpMapper.delete(ftpEntity);
	}
}
