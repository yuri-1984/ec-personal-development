package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.ShowItemService;

/**
 * 商品情報の処理を行うコントローラークラス.
 * 
 * @author hiraokayuri
 *
 */
@Controller
@RequestMapping("")
public class ShowItemListController {
	@Autowired
	private ShowItemService showItemService;

//	@RequestMapping("/showItemList")
//	public String showItemList(Model model) {
//		List<Item> itemList = showItemService.showItemList();
//		model.addAttribute("itemList", itemList);
//
//		return "item_list.html";
//
//	}

	// 1ページに表示する商品数は10名
	private static final int VIEW_SIZE = 9;

	/////////////////////////////////////////////////////
	// ユースケース：商品一覧を表示する
	/////////////////////////////////////////////////////
	/**
	 * 商品一覧画面を出力します.
	 *
	 * @param model            モデル
	 * @param 出力したいページ数
	 * @param 検索文字列
	 * @param ログイン情報をコントローラで取得
	 * @return 従業員一覧画面
	 */
	@RequestMapping("/")
	public String showItemList(Model model, Integer page, String name) {

//			// ログイン情報をコントローラで取得するサンプル
//			System.out.println(loginAdministrator.getAdministrator().getName() + "さんがログイン中");

		// ページング機能追加
		if (page == null) {
			// ページ数の指定が無い場合は1ページ目を表示させる
			page = 1;
		}
		List<Item> itemList = null;
		if (name == null) {
			// 検索文字列が空なら全件検索
			itemList = showItemService.showItemList();
		} else if (name.equals("")) {
			// 入力フィールドが空文字の場合、全商品を表示します。
			itemList = showItemService.showItemList();
			
//				// 検索文字列があれば曖昧検索
//				employeeList = employeeService.searchByNameContaining(searchName);
//				// ページングの数字からも検索できるように検索文字列をスコープに格納しておく
//				model.addAttribute("searchName", searchName);
		}else {
			itemList = showItemService.findByName(name);
			model.addAttribute("name", name);
			
		}

		if(itemList.size() == 0) {
			model.addAttribute("message", "該当する商品がありません");
			itemList = showItemService.showItemList();
		}
		   
		// ページング機能追加のためコメントアウト
		// model.addAttribute("employeeList", employeeList);

		// 表示させたいページ数、ページサイズ、従業員リストを渡し１ページに表示させる従業員リストを絞り込み
		Page<Item> itemPage = showItemService.showListPaging(page, VIEW_SIZE, itemList);
		model.addAttribute("itemPage", itemPage);

		// ページングのリンクに使うページ数をスコープに格納 (例)28件あり1ページにつき10件表示させる場合→1,2,3がpageNumbersに入る
		List<Integer> pageNumbers = calcPageNumbers(model, itemPage);
		model.addAttribute("pageNumbers", pageNumbers);

		// オートコンプリート用にJavaScriptの配列の中身を文字列で作ってスコープへ格納
		List<Item> itemListAll = showItemService.showItemList();
		StringBuilder itemListForAutocomplete = showItemService.getItemListForAutocomplete(itemListAll);
		model.addAttribute("itemListForAutocomplete", itemListForAutocomplete);

		return "item_list";
	}
	
	/**
	 * ページングのリンクに使うページ数をスコープに格納 (例)28件あり1ページにつき10件表示させる場合→1,2,3がpageNumbersに入る
	 *
	 * @param model        モデル
	 * @param employeePage ページング情報
	 */
	private List<Integer> calcPageNumbers(Model model, Page<Item> itemPage) {
		int totalPages = itemPage.getTotalPages();
		List<Integer> pageNumbers = null;
		if (totalPages > 0) {
			pageNumbers = new ArrayList<Integer>();
			for (int i = 1; i <= totalPages; i++) {
				pageNumbers.add(i);
			}
		}
		return pageNumbers;
	}

}
