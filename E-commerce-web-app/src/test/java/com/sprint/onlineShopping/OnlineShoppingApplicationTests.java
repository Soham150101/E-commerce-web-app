package com.sprint.onlineShopping;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.sprint.onlineShopping.controller.AddressController;
import com.sprint.onlineShopping.controller.BuyerController;
import com.sprint.onlineShopping.exception.EmptyInputException;
import com.sprint.onlineShopping.exception.RecordNotFoundException;
import com.sprint.onlineShopping.model.Address;
import com.sprint.onlineShopping.model.Buyer;
import com.sprint.onlineShopping.repository.AddressRepository;
import com.sprint.onlineShopping.repository.BuyerRepository;
import com.sprint.onlineShopping.repository.CartRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class OnlineShoppingApplicationTests {

	@MockBean
	private BuyerRepository buyerRepository;

	@MockBean
	private AddressRepository addressRepository;

	@MockBean
	private CartRepository cartRepository;

	@Autowired
	private BuyerController buyerController;

	@Autowired
	private AddressController addressController;

	@Test
	public void servicebuyerRepositoryTest() {
		assertThat(buyerRepository).isNotNull();
	}

	@Test
	public void serviceAddressRepositoryTest() {
		assertThat(addressRepository).isNotNull();
	}

	@Test
	public void getBuyersTest() {
		when(buyerRepository.findAll()).thenReturn(Stream
				.of(new Buyer(1000, "John", "John@example.com", "!J0hnDo3", 1, LocalDate.of(1990, 04, 12)),
						new Buyer(1004, "ramesh", "ramesh@gmail.com", "R@m3$h19", 2, LocalDate.of(1994, 02, 19)))
				.collect(Collectors.toList()));
		assertEquals(2, buyerController.list().size());
	}

	@Test
	public void getAddressTest() {
		when(addressRepository.findAll()).thenReturn(Stream.of(
				new Address(1, "street=S.V. road", "401/8, B-wing,Northen heights", "Mumbai", "Maharashtra", 400001,
						1000, Long.parseLong("9988776655"), null),
				new Address(2, "TR road", "314/8, B-wing,Southern heights", "Pune", "Maharashtra", 410014, 1004,
						Long.parseLong("8879966554"), null))
				.collect(Collectors.toList()));
		assertEquals(2, addressController.list().size());
	}

	@Test
	public void getBuyerByIdTest() throws Exception {
		Optional<Buyer> obuyer = Optional
				.of(new Buyer(1018, "manoj", "manoj@TheManCompany.com", "!J0hnDo3", 18, LocalDate.of(1990, 04, 12)));
		Buyer buyer = obuyer.get();
		when(buyerRepository.findById(1018)).thenReturn(obuyer);
		when(buyerRepository.getById(1018)).thenReturn(buyer);
		assertEquals(buyer, buyerController.get(1018));
		buyer = null;
		obuyer = null;
	}

	@Test
	public void getAddressByIdTest() throws Exception {
		Optional<Address> oAddress = Optional.of(new Address(19, "street=S.V. road", "401/8, B-wing,Northen heights",
				"Mumbai", "Maharashtra", 400001, 1000, Long.parseLong("9988776655"), null));
		Address address = oAddress.get();
		when(addressRepository.findById(19)).thenReturn(oAddress);
		when(addressRepository.getById(19)).thenReturn(address);
		assertEquals(address, addressController.get(19));
		address = null;
		oAddress = null;
	}

	@Test
	public void buyerNotFoundTest() throws RecordNotFoundException {
		Exception exception = assertThrows(RecordNotFoundException.class, () -> {
			buyerController.get(100000);
		});

		String expectedMessage = "Invalid BuyerId, \nThe entered BuyerId entered is not present in the database";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void addresssNotFoundTest() throws RecordNotFoundException {
		Exception exception = assertThrows(RecordNotFoundException.class, () -> {
			addressController.get(99);
		});

		String expectedMessage = "Invalid AddressId, \nThe entered AddressId entered is not present in the database";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void deletebuyerTest() throws RecordNotFoundException {
		Exception exception = assertThrows(RecordNotFoundException.class, () -> {
			buyerController.delete(100000);
		});
		String expectedMessage = "Invalid BuyerId Cannot Delete, \nThe entered BuyerId entered is not present in the database";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void deleteAddressTest() throws RecordNotFoundException {
		Exception exception = assertThrows(RecordNotFoundException.class, () -> {
			addressController.delete(98);
		});
		String expectedMessage = "Invalid AddressId Cannot Delete, \nThe entered AddressId entered is not present in the database";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void updateBuyerTest() throws EmptyInputException, RecordNotFoundException {
		Optional<Buyer> obuyer = Optional
				.of(new Buyer(1018, "manoj", "manoj@TheManCompany.com", "!J0hnDo3", 18, LocalDate.of(1990, 04, 12)));
		Buyer buyer = obuyer.get();
		when(buyerRepository.findById(1018)).thenReturn(obuyer);
		when(buyerRepository.getById(1018)).thenReturn(buyer);
		buyer.setName(null);
		Exception exception = assertThrows(EmptyInputException.class, () -> {
			buyerController.update(1018, buyer);
		});
		String expectedMessage = "No value was passed in buyer name parameter";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void updateAddressTest() throws EmptyInputException, RecordNotFoundException {
		Optional<Address> oAddress = Optional.of(new Address(19, "street=S.V. road", "401/8, B-wing,Northen heights",
				"Mumbai", "Maharashtra", 400001, 1000, Long.parseLong("9988776655"), null));
		Address address = oAddress.get();
		when(addressRepository.findById(19)).thenReturn(oAddress);
		when(addressRepository.getById(19)).thenReturn(address);
		address.setState(null);
		Exception exception = assertThrows(EmptyInputException.class, () -> {
			addressController.update(19, address);
		});
		String expectedMessage = "No value was passed in  State parameter";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

}
