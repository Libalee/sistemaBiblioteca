package com.biblioteca.converter;

public class MyModelMapper {

	/*
	 * package br.com.banco.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import br.com.banco.data.model.Account;
import br.com.banco.data.vo.AccountVO;

public class MyModelMapper {
	
	private ModelMapper mapper = new ModelMapper();
	
	public MyModelMapper() {
		// Setting typeMap properties
		
		// TODO Clean up the code
		
		TypeMap<AccountVO, Account> typeMapAccountVOToAccount = mapper.emptyTypeMap(AccountVO.class, Account.class);
		// Setting Mapping properties.
		typeMapAccountVOToAccount.addMappings(mapper -> mapper.map(AccountVO::returnFirstName, Account::setFirstName));
		typeMapAccountVOToAccount.addMappings(mapper -> mapper.map(AccountVO::returnLastName, Account::setLastName));
		typeMapAccountVOToAccount.addMappings(mapper -> mapper.map(AccountVO::getEmailAdress, Account::setEmailAdress));
		typeMapAccountVOToAccount.addMappings(mapper -> mapper.map(AccountVO::getGender, Account::setGender));
		typeMapAccountVOToAccount.addMappings(mapper -> mapper.map(AccountVO::getHomeAdress, Account::setHomeAdress));
		typeMapAccountVOToAccount.addMappings(mapper -> mapper.map(AccountVO::getAccountNumber, Account::setAccountNumber));
		typeMapAccountVOToAccount.addMappings(mapper -> mapper.map(AccountVO::getAccountBalance, Account::setAccountBalance));
		
		TypeMap<Account, AccountVO> typeMapAccountToAccountVO = mapper.emptyTypeMap(Account.class, AccountVO.class);
		//Setting Mapping properties.
		typeMapAccountToAccountVO.addMappings(mapper -> mapper.map(Account::returnFullName, AccountVO::setFullName));
		typeMapAccountToAccountVO.addMappings(mapper -> mapper.map(Account::getEmailAdress, AccountVO::setEmailAdress));
		typeMapAccountToAccountVO.addMappings(mapper -> mapper.map(Account::getGender, AccountVO::setGender));
		typeMapAccountToAccountVO.addMappings(mapper -> mapper.map(Account::getHomeAdress, AccountVO::setHomeAdress));
		typeMapAccountToAccountVO.addMappings(mapper -> mapper.map(Account::getAccountNumber, AccountVO::setAccountNumber));
		typeMapAccountToAccountVO.addMappings(mapper -> mapper.map(Account::getAccountBalance, AccountVO::setAccountBalance));
		typeMapAccountToAccountVO.addMappings(mapper -> mapper.map(Account::getId, AccountVO::setKey));
		
	}

	public <O, D> D parseObject(O origin, Class<D> destination) {

		return mapper.map(origin, destination);
	}

	public Account parseAccount(AccountVO origin, Class<Account> destination) {
		Account account = mapper.map(origin, destination);
		return account;
	}
	
	public AccountVO parseAccountVO(Account origin, Class<AccountVO> destination) {
		AccountVO accountVO = mapper.map(origin, destination);
		return accountVO;
	}


	public <O, D> List<D> parseListObject(List<O> origin, Class<D> destination) {
		List<D> list = new ArrayList<D>();
		for (O o : origin) {
			list.add(parseObject(o, destination));
		}
		return list;
	}
	
	public List<Account> parseListAccount(List<AccountVO> origin, Class<Account> destination) {
		List<Account> list = new ArrayList<Account>();
		for (AccountVO o : origin) {
			list.add(parseAccount(o, destination));
		}
		return list;
	}
	
	public List<AccountVO> parseListAccountVO(List<Account> origin, Class<AccountVO> destination) {
		List<AccountVO> list = new ArrayList<AccountVO>();
		for (Account o : origin) {
			list.add(parseAccountVO(o, destination));
		}
		return list;
	}
}

	 */
	
}
