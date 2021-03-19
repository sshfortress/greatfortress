package com.sshfortress.service.system;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.sshfortress.dao.system.model.DabaseTable;



import com.sshfortress.common.beans.DabaseTable;
import com.sshfortress.common.util.FreemarkGeneratorCodeUtil;
import com.sshfortress.dao.system.mapper.DabaseTableMapper;

import freemarker.template.TemplateException;

@Service("generatorCodeService")
public class GeneratorCodeService {
    @Autowired
    DabaseTableMapper  dabaseTableMapper;
    
	public void generatorCode(List<DabaseTable> list) {
		for (DabaseTable dabaseTable : list) {
			try {
				getTableColumns(dabaseTable);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}
	}

	
	private List<Map<String, String>> getTableColumns(DabaseTable dabaseTable) throws IOException, TemplateException {
		
		List<Map<String, String>> queryColumns = dabaseTableMapper.queryColumns(dabaseTable.getTableName());
		FreemarkGeneratorCodeUtil.GeneratorEntity(queryColumns, dabaseTable);
		return queryColumns;
	}

}
