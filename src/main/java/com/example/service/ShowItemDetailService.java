package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.domain.Topping;
import com.example.repository.ItemRepository;
import com.example.repository.ToppingRepository;

/**
 * 商品情報を操作するサービスクラス.
 * 
 * @author hiraokayuri
 *
 */
@Service
@Transactional
public class ShowItemDetailService {

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private ToppingRepository toppingRepository;

	/**
	 * 一件の商品情報を取得するメソッド.
	 * 
	 * @param id
	 * @return item 商品情報
	 */
	public Item showItemDetail(Integer id) {
		Item item = itemRepository.load(id);
		return item;

	}
	
	public List<Topping> seachToppingList(){
		List<Topping> toppingList = toppingRepository.findAll();
		return toppingList;
		
	}
	
	
	

}
