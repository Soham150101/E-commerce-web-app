package com.sprint.onlineShopping.controller;

import java.util.ArrayList;
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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sprint.onlineShopping.exception.EmptyInputException;
import com.sprint.onlineShopping.exception.OnlineShoppingException;
import com.sprint.onlineShopping.exception.RecordNotFoundException;
import com.sprint.onlineShopping.model.Buyer;
import com.sprint.onlineShopping.model.Cart;
import com.sprint.onlineShopping.model.CartDetail;
import com.sprint.onlineShopping.model.Order;
import com.sprint.onlineShopping.model.Product;
import com.sprint.onlineShopping.repository.BuyerRepository;
import com.sprint.onlineShopping.repository.CartDetailRepository;
import com.sprint.onlineShopping.repository.CartRepository;
import com.sprint.onlineShopping.repository.OrderRepository;
import com.sprint.onlineShopping.repository.ProductRepository;

@RestController
@RequestMapping("/buyers")
public class BuyerController {

	@Autowired
	private BuyerRepository buyerRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartDetailRepository cartDetailRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderRepository orderRepository;

	// Get All buyers
	@GetMapping("/getall")
	public List<Buyer> list() {
		System.out.println(buyerRepository.findAll());
		return buyerRepository.findAll();
	}

	// Get Buyer by ID
	@GetMapping("/get/{id}")
	public Buyer get(@PathVariable Integer id) throws RecordNotFoundException {
		Optional<Buyer> b = buyerRepository.findById(id);
		if (!b.isPresent())
			throw new RecordNotFoundException(
					"Invalid BuyerId, \nThe entered BuyerId entered is not present in the database");
		Buyer buyer = buyerRepository.getById(id);
//		if(b.getClass()!=Buyer.class)
		return buyer;
	}

	// Register Buyer
	// first it creates a cart and aasign the cart value to buyer
	@PostMapping("/register")
	public Buyer create(@RequestBody Buyer buyer) throws Exception {
		Cart cart = new Cart();
		cart.setQuantity(0);
		cart.setTotalValue(0);
		if (buyer.getName() == null || buyer.getName().isEmpty())
			throw new EmptyInputException("No value was passed in buyer name parameter");
		if (buyer.getDob() == null)
			throw new EmptyInputException("No value was passed in buyer dob parameter");
		if (buyer.getEmail() == null || buyer.getEmail().isEmpty())
			throw new EmptyInputException("No value was passed in buyer email parameter");
		Buyer ebuyer = buyerRepository.findByEmail(buyer.getEmail());
		if (ebuyer != null)
			throw new OnlineShoppingException(
					ebuyer.getEmail() + " is alredy in use with another account please use another email");
		if (buyer.getPassword() == null || buyer.getPassword().isEmpty())
			throw new EmptyInputException("No value was passed in buyer password parameter");
		cart = cartRepository.saveAndFlush(cart);
		buyer.setCartId(cart.getCartId());
		return buyerRepository.saveAndFlush(buyer);
	}

	@GetMapping("/getOrders/{id}")
	public List<Order> getOrders(@PathVariable Integer id) {
		List<Order> orders = orderRepository.findAllByBuyerId(id);
		return orders;
	}

	// User Login
	@PostMapping("/login")
	public String authenticate(@RequestBody ObjectNode json) {
		String email = json.get("email").asText();
		String password = json.get("password").asText();
		Buyer buyer = buyerRepository.findByEmailEqualsAndPasswordEquals(email, password);

		if (buyer == null) {
//			throw new OnlineShoppingException("Invalid Login credentials");
			buyer = buyerRepository.findByEmail(email);
			if (buyer != null && buyer.getEmail().equalsIgnoreCase(email)) {
				return "Invalid Passwod";
			}
			return "Buyer does not exists";
		}
		if (buyer.getEmail().equals(email) && buyer.getPassword().equals(password)) {
			return "Logged in successfully";
		}
		return "Invalid Login credentials";
	}

	// Get User Cart
	@GetMapping("/getcart/{id}")
	public Cart getUserCart(@PathVariable Integer id) throws RecordNotFoundException {
		Buyer buyer;
		buyer = buyerRepository.getOne(id);
		return cartRepository.getById(buyer.getCartId());
	}

	// Get User CartDetails
	@GetMapping("/getcartDetails/{id}")
	public ObjectNode getBuyerCartDetails(@PathVariable Integer id) throws RecordNotFoundException {
		Optional<Buyer> b = buyerRepository.findById(id);
		if (!b.isPresent())
			throw new RecordNotFoundException(
					"Invalid BuyerId, \nThe entered BuyerId entered is not present in the database");
		Buyer buyer = buyerRepository.getOne(id);
		List<CartDetail> cartDetails = cartDetailRepository.findAllByCartId(buyer.getCartId());
		System.out.println(cartDetails);
		List<Product> products = new ArrayList<Product>();
		for (CartDetail cd : cartDetails) {
			products.add(productRepository.getById(cd.getProductId()));
		}
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode root = mapper.createObjectNode();
		Cart cart = cartRepository.getById(buyer.getCartId());
		root.set("Cart Details", mapper.convertValue(cart, JsonNode.class));
		root.set("products", mapper.convertValue(products, JsonNode.class));
		return root;
	}

	@PutMapping("/addToCart/{id}")
	public ObjectNode addToCart(@PathVariable Integer id, @RequestBody ObjectNode json) throws RecordNotFoundException {
		Optional<Buyer> b = buyerRepository.findById(id);
		if (!b.isPresent())
			throw new RecordNotFoundException(
					"Invalid BuyerId, \nThe entered BuyerId entered is not present in the database");
		Buyer buyer = buyerRepository.getById(id);
		System.out.println(json.get("productId").asInt());
		Product product;
		Cart existingCart;
		Optional<Product> prod = productRepository.findById(id);
		if (!prod.isPresent())
			throw new RecordNotFoundException(
					"Invalid ProductID, \nThe entered ProductID entered is not present in the database");
		product = productRepository.getById(json.get("productId").asInt());

		if (product == null)
			throw new RecordNotFoundException("The Product record does not exists");
		existingCart = cartRepository.getById(buyer.getCartId());
		int count = (int) cartDetailRepository.count();
		CartDetail cartDetail = new CartDetail(++count, existingCart.getCartId(), product.getProductId());
		cartDetailRepository.saveAndFlush(cartDetail);
		Cart newCart = new Cart();
		newCart.setQuantity(existingCart.getQuantity() + 1);
		newCart.setTotalValue(existingCart.getTotalValue() + product.getPrice());
		System.out.println("New Cart\n" + newCart);
		System.out.println("Old Cart\n" + existingCart);
		BeanUtils.copyProperties(newCart, existingCart, "cartId");
		cartRepository.saveAndFlush(existingCart);
		return getBuyerCartDetails(id);
	}

	// Delete User By ID
	@DeleteMapping("/remove/{id}")
	public String delete(@PathVariable Integer id) throws RecordNotFoundException {
		Optional<Buyer> b = buyerRepository.findById(id);
		if (!b.isPresent())
			throw new RecordNotFoundException(
					"Invalid BuyerId Cannot Delete, \nThe entered BuyerId entered is not present in the database");
		Buyer buyer = buyerRepository.getById(id);
		if (buyer != null)
			buyerRepository.deleteById(id);
		return "Buyer Deleted successfuly";
	}

	// Update User by ID
	@PutMapping("/update/{id}")
	public Buyer update(@PathVariable Integer id, @RequestBody Buyer buyer)
			throws EmptyInputException, RecordNotFoundException {
		Optional<Buyer> b = buyerRepository.findById(id);
		if (!b.isPresent())
			throw new RecordNotFoundException(
					"Invalid BuyerId Cannot Update, \nThe entered BuyerId entered is not present in the database");
		Buyer existingBuyer = buyerRepository.getOne(id);
		if (buyer.getName() == null || buyer.getName().isEmpty())
			throw new EmptyInputException("No value was passed in buyer name parameter");
		if (buyer.getDob() == null)
			throw new EmptyInputException("No value was passed in buyer dob parameter");
		if (buyer.getEmail() == null || buyer.getEmail().isEmpty())
			throw new EmptyInputException("No value was passed in buyer email parameter");
		Buyer ebuyer = buyerRepository.findByEmail(buyer.getEmail());
		if (ebuyer != null)
			throw new OnlineShoppingException(
					ebuyer.getEmail() + " is alredy in use with another account please use another email");
		if (buyer.getPassword() == null || buyer.getPassword().isEmpty())
			throw new EmptyInputException("No value was passed in buyer password parameter");
		BeanUtils.copyProperties(buyer, existingBuyer, "buyerId", "cartId");
		return buyerRepository.saveAndFlush(existingBuyer);
	}
}
