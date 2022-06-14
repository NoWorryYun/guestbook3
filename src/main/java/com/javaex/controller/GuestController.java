package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestbookVo;

@Controller
public class GuestController {

	//필드
	@Autowired
	private GuestService guestService;
	
	
	//생성자
	
	//GS
	
	//일반
	
	@RequestMapping(value="/addlist", method={RequestMethod.GET, RequestMethod.POST})
	public String addlist(Model model) {
		System.out.println("Controller > addlist");
		
		//GuestbookDao
//		GuestbookDao guestbookDao = new GuestbookDao();
		
		//List
		List<GuestbookVo> guestList = guestService.getGuestList();
		
		//확인
//		System.out.println(guestList);
		
		//attribute
		model.addAttribute("guestList", guestList);
		
		//forward
		return "addList";
	}
	
	@RequestMapping(value="/add", method= {RequestMethod.GET, RequestMethod.POST})
	public String add(@ModelAttribute GuestbookVo guestbookVo) {
		
		//guestbookDao
//		GuestbookDao guestbookDao = new GuestbookDao();
		
		//add
		int count = guestService.addGuest(guestbookVo);
		
		//등록확인
		System.out.println(count);
		
		
		return "redirect:/addlist";
	}
	
	@RequestMapping(value="/deleteForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm() {
		System.out.println("deleteForm");
		
		return "deleteForm";
	}
	
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("password") String password, @RequestParam("no") int no) {
		
//		GuestbookDao guestbookDao = new GuestbookDao();
		
		String oraclePassword = guestService.guestPassword(no);
		
		if(password.equals(oraclePassword)) {
			guestService.guestDelete(no);
		} else {
			System.out.println(no + "의" + password + "(입력패스워드) 와(과)" + oraclePassword + "(DB패스워드) 가 일치하지 않습니다.");
		}
		
		return "redirect:/addlist";
	}
	
}
