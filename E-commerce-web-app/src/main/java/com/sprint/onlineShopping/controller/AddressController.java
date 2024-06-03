package com.sprint.onlineShopping.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.onlineShopping.exception.EmptyInputException;
import com.sprint.onlineShopping.exception.RecordNotFoundException;
import com.sprint.onlineShopping.model.Address;
import com.sprint.onlineShopping.model.Buyer;
import com.sprint.onlineShopping.repository.AddressRepository;
import com.sprint.onlineShopping.repository.BuyerRepository;

@RestController
@RequestMapping("/addresses")
public class AddressController {	
	
	@Autowired
	private AddressRepository  addressRepository;
	
	@Autowired
	private BuyerRepository buyerRepository;


	@GetMapping("/getall")
	public List<Address> list() {
		System.out.println(addressRepository.findAll());
		return addressRepository.findAll();
	}

	@GetMapping("/get/{id}")
	public Address get(@PathVariable Integer id) throws RecordNotFoundException {
		Optional<Address> add = addressRepository.findById(id);
		if(!add.isPresent())
			throw new RecordNotFoundException("Invalid AddressId, \nThe entered AddressId entered is not present in the database");
		return addressRepository.getById(id);
	}

	@PostMapping("/add")
	public Address create(@RequestBody Address address) throws EmptyInputException, RecordNotFoundException {
		if (address.getAddressLine1() == null || address.getAddressLine1().isEmpty())
			throw new EmptyInputException("No value was passed in  AddressLine1 parameter");
		if (address.getStreet() == null || address.getStreet().isEmpty())
			throw new EmptyInputException("No value was passed in  Street parameter");
		if (address.getCity() == null || address.getCity().isEmpty())
			throw new EmptyInputException("No value was passed in  City parameter");
		if (address.getState() == null || address.getState().isEmpty())
			throw new EmptyInputException("No value was passed in  State parameter");
		if (address.getPincode() == null )
			throw new EmptyInputException("No value was passed in  pincode parameter");
		if (address.getBuyerId() == null )
			throw new EmptyInputException("No value was passed in  buyerId parameter");
		Optional<Buyer> b = buyerRepository.findById(address.getBuyerId());
		if(!b.isPresent())
			throw new RecordNotFoundException("Invalid BuyerId, \nThe entered BuyerId entered is not present in the database");
		if (address.getPrimaryPhone() == null )
			throw new EmptyInputException("No value was passed in  Primary phone number parameter");
		return addressRepository.saveAndFlush(address);
	}

	@DeleteMapping("/remove/{id}")
	public String delete(@PathVariable Integer id) throws RecordNotFoundException {
		Optional<Address> add = addressRepository.findById(id);
		if(!add.isPresent())
			throw new RecordNotFoundException("Invalid AddressId Cannot Delete, \nThe entered AddressId entered is not present in the database");
		addressRepository.deleteById(id);
		return "Address Deleted successfully";
	}
	
	@GetMapping("/getAddress/{id}")
	public List<Address> buyerAddress(@PathVariable Integer id) throws RecordNotFoundException {
		if(addressRepository.findByBuyerId(id).isEmpty())
			throw new RecordNotFoundException("Sorry no Address records available for the entered BuyerId");
		return addressRepository.findByBuyerId(id);
	}

	@PutMapping("/update/{id}")
	public Address update(@PathVariable Integer id, @RequestBody Address address) throws RecordNotFoundException, EmptyInputException {
		Optional<Address> add = addressRepository.findById(id);
		if(!add.isPresent())
			throw new RecordNotFoundException("Invalid AddressId Cannot Update, \nThe entered AddressId entered is not present in the database");
		Address existingAddress = addressRepository.getOne(id);
		if (address.getAddressLine1() == null || address.getAddressLine1().isEmpty())
			throw new EmptyInputException("No value was passed in  AddressLine1 parameter");
		if (address.getStreet() == null || address.getStreet().isEmpty())
			throw new EmptyInputException("No value was passed in  Street parameter");
		if (address.getCity() == null || address.getCity().isEmpty())
			throw new EmptyInputException("No value was passed in  City parameter");
		if (address.getState() == null || address.getState().isEmpty())
			throw new EmptyInputException("No value was passed in  State parameter");
		if (address.getPincode() == null )
			throw new EmptyInputException("No value was passed in  pincode parameter");
		if (address.getBuyerId() == null )
			throw new EmptyInputException("No value was passed in  buyerId parameter");
		Optional<Buyer> b = buyerRepository.findById(address.getBuyerId());
		if(!b.isPresent())
			throw new RecordNotFoundException("Invalid BuyerId, \nThe entered BuyerId entered is not present in the database");
		if (address.getPrimaryPhone() == null )
			throw new EmptyInputException("No value was passed in  Primary phone number parameter");
		BeanUtils.copyProperties(address, existingAddress, "addressId");
		return addressRepository.saveAndFlush(existingAddress);
	}
}
