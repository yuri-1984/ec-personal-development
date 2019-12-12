package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.domain.Topping;
import com.example.service.ShowItemDetailService;

/**
 * 商品詳細情報を操作するコントローラークラス.
 * 
 * @author hiraokayuri
 *
 */
@Controller
@RequestMapping("")
public class ShowItemDetailController {
	@Autowired
	private ShowItemDetailService showItemDetailService;
	
	/**
	 * 商品詳細情報を表示する.
	 * 
	 * @param id 
	 * @param model
	 * @return item_detail.html
	 */
	@RequestMapping("/showItemDetail")
	public String showItemDetail (Integer id, Model model) {
		Item item = showItemDetailService.showItemDetail(id);
		List<Topping> toppingList = showItemDetailService.seachToppingList();
		item.setToppingList(toppingList);
		model.addAttribute("item", item);
		return "item_detail";
		
		
		
	}
	

}
